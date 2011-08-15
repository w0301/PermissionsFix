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
    GROUP_EXECUTE(11),
    GROUP_READ(12),
    GROUP_WRITE(13),
    OTHERS_EXECUTE(21),
    OTHERS_READ(22),
    OTHERS_WRITE(23),
    OWNER_EXECUTE(31),
    OWNER_READ(32),
    OWNER_WRITE(33);

    private final int code;

    PosixFilePermission(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PosixFilePermission getByCode(int code) {
        for (PosixFilePermission perm : values()) {
            if (perm.getCode() == code) return perm;
        }
        return OWNER_READ;
    }

}
