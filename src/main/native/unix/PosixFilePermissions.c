/*
 * Copyright (C) 2011
 * Richard Kakaš <richard.kakas@gmail.com>
 *
 * This file is part of PermissionsFix.
 *
 * PermissionsFix is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PermissionsFix is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PermissionsFix. If not, see <http://www.gnu.org/licenses/>.
 */
#include "com_subbst_permissionsfix_core_PosixFilePermissions.h"

#include <sys/types.h>
#include <sys/stat.h>

// this values have to reflect enum values from PosixFilePermissio.java file
#define GROUP_EXECUTE (11)
#define GROUP_READ (12)
#define GROUP_WRITE (13)
#define OTHERS_EXECUTE (21)
#define OTHERS_READ (22)
#define OTHERS_WRITE (23)
#define OWNER_EXECUTE (31)
#define OWNER_READ (32)
#define OWNER_WRITE (33)

#define PERMS_COUNT 9

JNIEXPORT jintArray JNICALL Java_com_subbst_permissionsfix_core_PosixFilePermissions_get
        (JNIEnv *env, jclass cls, jstring javaFileName) {
    // getting native fileName in string
    const char *fileName = (*env)->GetStringUTFChars(env, javaFileName, NULL);

    // calling kernel function to get permissions
    struct stat fileStats;
    if(stat(fileName, &fileStats) == -1) {
        (*env)->ReleaseStringUTFChars(env, javaFileName, fileName);
        return NULL;
    }
    mode_t nativePerms = fileStats.st_mode;

    // releasing native string
    (*env)->ReleaseStringUTFChars(env, javaFileName, fileName);

    // creating new java array
    jintArray retArr = (*env)->NewIntArray(env, PERMS_COUNT);
    if(retArr == NULL) return NULL;

    // filling buffer
    static jint buff[PERMS_COUNT];
    for(int i = 0; i < PERMS_COUNT; i++) buff[i] = 0;

    if(nativePerms & S_IRUSR) buff[0] = OWNER_READ;
    if(nativePerms & S_IWUSR) buff[1] = OWNER_WRITE;
    if(nativePerms & S_IXUSR) buff[2] = OWNER_EXECUTE;
    if(nativePerms & S_IRGRP) buff[3] = GROUP_READ;
    if(nativePerms & S_IWGRP) buff[4] = GROUP_WRITE;
    if(nativePerms & S_IXGRP) buff[5] = GROUP_EXECUTE;
    if(nativePerms & S_IROTH) buff[6] = OTHERS_READ;
    if(nativePerms & S_IWOTH) buff[7] = OTHERS_WRITE;
    if(nativePerms & S_IXOTH) buff[8] = OTHERS_EXECUTE;

    // filling java array and returning it
    (*env)->SetIntArrayRegion(env, retArr, 0, PERMS_COUNT, buff);
    return retArr;
}

JNIEXPORT jint JNICALL Java_com_subbst_permissionsfix_core_PosixFilePermissions_set
        (JNIEnv *env, jclass cls, jstring javaFileName, jintArray perms) {
    // getting java array
    jint *buff = (*env)->GetIntArrayElements(env, perms, NULL);

    // building native file permissions value
    mode_t nativePerms = 0;
    for(int i = 0; i < PERMS_COUNT; i++) {
        if(buff[i] == OWNER_READ) nativePerms |= S_IRUSR;
        else if(buff[i] == OWNER_WRITE) nativePerms |= S_IWUSR;
        else if(buff[i] == OWNER_EXECUTE) nativePerms |= S_IXUSR;
        else if(buff[i] == GROUP_READ) nativePerms |= S_IRGRP;
        else if(buff[i] == GROUP_WRITE) nativePerms |= S_IWGRP;
        else if(buff[i] == GROUP_EXECUTE) nativePerms |= S_IXGRP;
        else if(buff[i] == OTHERS_READ) nativePerms |= S_IROTH;
        else if(buff[i] == OTHERS_WRITE) nativePerms |= S_IWOTH;
        else if(buff[i] == OTHERS_EXECUTE) nativePerms |= S_IXOTH;
    }

    // releasing java array
    (*env)->ReleaseIntArrayElements(env, perms, buff, 0);

    // getting native fileName in string
    const char *fileName = (*env)->GetStringUTFChars(env, javaFileName, NULL);

    // updating file permissions
    if(chmod(fileName, nativePerms) == -1) {
        (*env)->ReleaseStringUTFChars(env, javaFileName, fileName);
        return -1;
    }

    // releasing native string
    (*env)->ReleaseStringUTFChars(env, javaFileName, fileName);
}
