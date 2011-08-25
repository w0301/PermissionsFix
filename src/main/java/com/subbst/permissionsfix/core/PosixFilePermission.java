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
 * Enum type for file permissions.
 *
 * This enum contains all possible permissions. Each file
 * has one or more of these permissions. It also provide some
 * basic information about permissions which are used by other
 * core and gui classes.
 */
public enum PosixFilePermission {
    /**
     * Execute permission for file's group.
     */
    GROUP_EXECUTE(11, 5, 'x'),

    /**
     * Read permission for file's group.
     */
    GROUP_READ(12, 3, 'r'),

    /**
     * Write permission for file's group.
     */
    GROUP_WRITE(13, 4, 'w'),

    /**
     * Execute permission for others.
     */
    OTHERS_EXECUTE(21, 8, 'x'),

    /**
     * Read permission for others.
     */
    OTHERS_READ(22, 6, 'r'),


    /**
     * Write permission for others.
     */
    OTHERS_WRITE(23, 7, 'w'),

    /**
     * Execute permission for file's owner.
     */
    OWNER_EXECUTE(31, 2, 'x'),

    /**
     * Read permission for file's owner.
     */
    OWNER_READ(32, 0, 'r'),


    /**
     * Write permission for file's owner.
     */
    OWNER_WRITE(33, 1, 'w');

    private final int code;
    private final int position;
    private final char symbol;

    private PosixFilePermission(int code, int pos, char sym) {
        this.code = code;
        this.position = pos;
        this.symbol = sym;
    }

    /**
     * Returns unique code for permission.
     *
     * Eeach permissions has unique code that is used internally
     * by PermissionsFix. These code will never change.
     *
     * @return code for this posix permission enum type
     */
    public final int getCode() {
        return this.code;
    }

    /**
     * Returns position of permission in permissions string.
     *
     * Returned integer is position in string like "rwxr-xr-x".
     * First position is 0. This position is unique.
     *
     * @return index in permissions string
     */
    public final int getPosition() {
        return this.position;
    }

    /**
     * Returns symbol for permission.
     *
     * Returned character is symbol for permission. This
     * symbol is used in permissions strings. For read
     * permissions this is 'r', for write 'w' and for execute
     * it is 'x'.
     *
     * @return symbol for permission
     */
    public final char getSymbol() {
        return this.symbol;
    }

    /**
     * Returns permission for given code.
     *
     * Looks for permission with given unique code.
     *
     * @param code integer used for look up by code
     * @return permission object for given code
     */
    public static PosixFilePermission getByCode(int code) {
        for (PosixFilePermission perm : values()) {
            if (perm.getCode() == code) return perm;
        }
        return OWNER_READ;
    }

    /**
     * Returns permission for given position.
     *
     * Looks for permission that has given position.
     *
     * @param pos index of permission
     * @return permission object with given position
     */
    public static PosixFilePermission getByPosition(int pos) {
        for (PosixFilePermission perm : values()) {
            if (perm.getPosition() == pos) return perm;
        }
        return OWNER_READ;
    }

}
