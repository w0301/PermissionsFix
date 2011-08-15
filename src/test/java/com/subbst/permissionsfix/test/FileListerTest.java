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
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.subbst.permissionsfix.core.FileLister;

/**
 * Test class for FileLister class.
 */
public class FileListerTest {

    @Test
    public void testFileListing1() throws IOException {
        // init
        List<File> dirStruct = new ArrayList<File>();
        dirStruct.add(new File("dirStruct"));
        dirStruct.add(new File("dirStruct/dir1"));
        dirStruct.add(new File("dirStruct/dir1/dir11"));
        dirStruct.add(new File("dirStruct/dir1/dir12"));
        dirStruct.add(new File("dirStruct/dir2"));
        dirStruct.add(new File("dirStruct/dir2/dir22"));
        dirStruct.add(new File("dirStruct/dir3"));
        for (File f : dirStruct) f.mkdir();

        List<File> fileStruct = new ArrayList<File>();
        fileStruct.add(new File("dirStruct/file1"));
        fileStruct.add(new File("dirStruct/file2"));
        fileStruct.add(new File("dirStruct/dir1/file3"));
        fileStruct.add(new File("dirStruct/dir1/dir11/file4"));
        fileStruct.add(new File("dirStruct/dir2/dir22/file5"));
        fileStruct.add(new File("dirStruct/dir3/file6"));
        for (File f : fileStruct) f.createNewFile();

        // testing
        FileLister tester = new FileLister(dirStruct.get(0), true);
        List<File> testerList = new ArrayList<File>();
        for (FileLister.ListEntry le : tester.getFileList()) {
            testerList.add(le.getFile());
        }
        Assert.assertTrue(testerList.containsAll(dirStruct) && testerList.containsAll(fileStruct));

        // clean up
        for (File f : fileStruct) f.delete();
        for (int i = dirStruct.size() - 1; i >= 0; i--) dirStruct.get(i).delete();
    }

    @Test
    public void testFileListing2() throws IOException {
        // init
        File testDir = new File("testDir");
        testDir.mkdir();

        // testing
        FileLister tester = new FileLister(testDir, true);
        Assert.assertTrue(tester.getFileList().get(0).getFile().equals(testDir));

        // clean up
        testDir.delete();
    }

    @Test
    public void testFileListing3() throws IOException {
        // init
        File testFile = new File("testFile");
        testFile.mkdir();

        // testing
        FileLister tester = new FileLister(testFile);
        Assert.assertTrue(tester.getFileList().get(0).getFile().equals(testFile));

        // clean up
        testFile.delete();
    }

}
