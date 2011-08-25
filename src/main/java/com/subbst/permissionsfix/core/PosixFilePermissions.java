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
package com.subbst.permissionsfix.core;

import java.io.File;
import java.util.EnumSet;
import java.util.Set;

/**
 * Class that loads file permissions.
 *
 * This class can load and save permissions of specific file.
 * It consists of static methods that can be called to get/set
 * file's permissions.
 */
public class PosixFilePermissions {
    private native static int[] get(String file);
    private native static int set(String file, int[] perms);

    static {
        // loading library from default library paths
        // adjust LD_LIBRARY_PATH in app launcher script!
        System.loadLibrary("PermissionsFix");
    }

    private PosixFilePermissions() {
    }

    /**
     * Gets file's permissions.
     *
     * @param f file whichs permissions will be returned
     * @return set of file's permissions
     * @throws PosixFilePermissionsException when getting of permissions failed
     */
    public static Set<PosixFilePermission> getPermissions(File f) throws PosixFilePermissionsException {
        String fileName = f.getAbsolutePath();
        int[] lowLevelPerms = get(fileName);
        if (lowLevelPerms == null)
            throw new PosixFilePermissionsException("Unable to get permissions.", fileName);

        Set<PosixFilePermission> retObj = EnumSet.noneOf(PosixFilePermission.class);

        for (int permCode : lowLevelPerms) {
            retObj.add(PosixFilePermission.getByCode(permCode));
        }

        return retObj;
    }

    /**
     * Sets permissions for specific file.
     *
     * @param f file whichs permissions will be set
     * @param perms set of new permissions
     * @throws PosixFilePermissionsException when setting of permissions failed
     */
    public static void setPermissions(File f, Set<PosixFilePermission> perms) throws PosixFilePermissionsException {
        int[] lowLevelPerms = new int[PosixFilePermission.values().length];
        int lastI = 0;
        for (PosixFilePermission perm : perms) {
            lowLevelPerms[lastI++] = perm.getCode();
        }

        String fileName = f.getAbsolutePath();
        if (set(fileName, lowLevelPerms) == -1)
            throw new PosixFilePermissionsException("Unable to set permissions.", fileName);
    }

}
