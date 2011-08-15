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
import java.util.Set;

/**
 * Class that loads file permissions.
 *
 * This class can load a save permissions of specific file.
 * It achieve it by calling the native code of POSIX like systems
 * by JNI.
 */
public class PosixFilePermissions {

    /**
     * Get file's permissions.
     *
     * @param f File whichs permissions will be returned.
     * @return Set of file's permissions.
     */
    public native static Set<PosixFilePermission> get(File f);

    /**
     * Set permissions for specific file.
     *
     * @param f File whichs permissions will be set.
     * @param perms Permissions to set.
     */
    public native static void set(File f, Set<PosixFilePermission> perms);

    private PosixFilePermissions() {
        super();
    }

}
