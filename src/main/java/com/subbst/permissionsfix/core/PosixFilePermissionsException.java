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

/**
 * Exception that indicates error in PosixFilePermissions class.
 */
public class PosixFilePermissionsException extends Exception {

    private final String fileName;

    /**
     * Creates a new instance of exception.
     *
     * @param fileName name of file that cause this exception.
     */
    public PosixFilePermissionsException(String fileName) {
        super();
        this.fileName = fileName;
    }

    /**
     * Creates a new instance of exception.
     *
     * @param msg the detail message.
     * @param fileName name of file that cause this exception.
     */
    public PosixFilePermissionsException(String msg, String fileName) {
        super(msg);
        this.fileName = fileName;
    }

    /**
     * Return file name associated with this exception.
     *
     * @return name of file which processing causes this exception
     */
    public String getFileName() {
        return this.fileName;
    }

}
