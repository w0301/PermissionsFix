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
 * has one or more of these permissions.
 */
public enum PosixFilePermission {
    GROUP_EXECUTE(11, 5, 'x'),
    GROUP_READ(12, 3, 'r'),
    GROUP_WRITE(13, 4, 'w'),
    OTHERS_EXECUTE(21, 8, 'x'),
    OTHERS_READ(22, 6, 'r'),
    OTHERS_WRITE(23, 7, 'w'),
    OWNER_EXECUTE(31, 2, 'x'),
    OWNER_READ(32, 0, 'r'),
    OWNER_WRITE(33, 1, 'w');

    private final int code;
    private final int pos;
    private final char sym;

    PosixFilePermission(int code, int pos, char sym) {
        this.code = code;
        this.pos = pos;
        this.sym = sym;
    }

    public final int getCode() {
        return this.code;
    }

    public final int getPosition() {
        return this.pos;
    }

    public final char getSymbol() {
        return this.sym;
    }

    public static PosixFilePermission getByCode(int code) {
        for (PosixFilePermission perm : values()) {
            if (perm.getCode() == code) return perm;
        }
        return OWNER_READ;
    }

}
