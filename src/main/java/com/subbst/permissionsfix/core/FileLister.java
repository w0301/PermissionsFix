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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class that is used for listing files and their permissions.
 */
public class FileLister {

    static public class ListEntry {
        private final File file;
        private final Set<PosixFilePermission> filePerms;

        public ListEntry(File file) throws PosixFilePermissionsException {
            this.file = file;
            this.filePerms = PosixFilePermissions.getPermissions(file);
        }

        public File getFile() {
            return this.file;
        }

        public Set<PosixFilePermission> getPermissions() {
            return this.filePerms;
        }

    }

    private List<ListEntry> fileList;
    private List<PosixFilePermissionsException> exceptionsList = new ArrayList<PosixFilePermissionsException>();

    private static List<ListEntry> createFileList(File f, List<PosixFilePermissionsException> exList) {
        File[] files = f.listFiles();
        List<ListEntry> retList = new ArrayList<ListEntry>();
        try {
            retList.add(new ListEntry(f));
        }
        catch (PosixFilePermissionsException ex) {
            if (exList != null) exList.add(ex);
        }

        for (File file : files) {
            try {
                if (file.isDirectory()) {
                    retList.addAll(createFileList(file, exList));
                }
                else retList.add(new ListEntry(file));
            }
            catch (PosixFilePermissionsException ex) {
                if (exList != null) exList.add(ex);
            }
        }

        return retList;
    }

    public FileLister(File f, boolean recursive) {
        super();
        try {
            if (recursive && f.isDirectory()) {
                fileList = createFileList(f, exceptionsList);
            }
            else {
                fileList = new ArrayList<ListEntry>();
                fileList.add(new ListEntry(f));
            }
        }
        catch (PosixFilePermissionsException ex) {
            exceptionsList.add(ex);
        }
    }

    public FileLister(File f) {
        this(f, false);
    }

    public List<ListEntry> getFileList() {
        return this.fileList;
    }

    public List<PosixFilePermissionsException> getExceptionsList() {
        return this.exceptionsList;
    }

}
