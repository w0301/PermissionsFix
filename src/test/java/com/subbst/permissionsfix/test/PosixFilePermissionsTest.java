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
package com.subbst.permissionsfix.test;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subbst.permissionsfix.core.PosixFilePermission;
import com.subbst.permissionsfix.core.PosixFilePermissions;
import com.subbst.permissionsfix.core.PosixFilePermissionsException;

/**
 * Testing class for PosixFilePermissions class.
 */
public class PosixFilePermissionsTest {

    private File testFile = null;

    private static boolean commonTest(File f, Set<PosixFilePermission> perms) {
        Set<PosixFilePermission> gotPerms = null;
        try {
            PosixFilePermissions.setPermissions(f, perms);
            gotPerms = PosixFilePermissions.getPermissions(f);
        }
        catch(PosixFilePermissionsException e) {
            return false;
        }
        return gotPerms.containsAll(perms);
    }

    @Before
    public void setUp() throws IOException {
        testFile = new File("testFile");
        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test
    public void testGetSetPermissions1() {
        Set<PosixFilePermission> newPerms = EnumSet.allOf(PosixFilePermission.class);
        Assert.assertTrue(commonTest(testFile, newPerms));
    }

    @Test
    public void testGetSetPermissions2() {
        Set<PosixFilePermission> newPerms = EnumSet.noneOf(PosixFilePermission.class);
        Assert.assertTrue(commonTest(testFile, newPerms));
    }

    @Test
    public void testGetSetPermissions3() {
        Set<PosixFilePermission> newPerms = EnumSet.noneOf(PosixFilePermission.class);
        newPerms.add(PosixFilePermission.OTHERS_READ);
        newPerms.add(PosixFilePermission.OWNER_READ);
        newPerms.add(PosixFilePermission.GROUP_READ);
        Assert.assertTrue(commonTest(testFile, newPerms));
    }

    @Test
    public void testGetSetPermissions4() {
        Set<PosixFilePermission> newPerms = EnumSet.noneOf(PosixFilePermission.class);
        newPerms.add(PosixFilePermission.OTHERS_READ);
        newPerms.add(PosixFilePermission.OWNER_EXECUTE);
        newPerms.add(PosixFilePermission.GROUP_WRITE);
        Assert.assertTrue(commonTest(testFile, newPerms));
    }

    @Test(expected=PosixFilePermissionsException.class)
    public void testExceptionsThrowing1() throws PosixFilePermissionsException {
        File notExistingFile = new File("notExistingFile");
        if (notExistingFile.exists()) notExistingFile.delete();
        PosixFilePermissions.getPermissions(notExistingFile);
    }

    @Test(expected=PosixFilePermissionsException.class)
    public void testExceptionsThrowing2() throws PosixFilePermissionsException {
        File notExistingFile = new File("notExistingFile");
        if (notExistingFile.exists()) notExistingFile.delete();
        PosixFilePermissions.setPermissions(notExistingFile, EnumSet.noneOf(PosixFilePermission.class));
    }

}
