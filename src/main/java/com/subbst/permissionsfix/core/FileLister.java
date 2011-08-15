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

    /**
     * Class that is used to store file info in list.
     */
    static public class ListEntry {
        private final File file;
        private Set<PosixFilePermission> filePerms = null;
        private Set<PosixFilePermission> alteredPerms = null;
        private boolean permsAltered = false;

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

        public Set<PosixFilePermission> getAlteredPermissions() {
            return this.alteredPerms;
        }

        public void alterPermissions(Set<PosixFilePermission> newPerms) {
            this.alteredPerms = newPerms;
            this.permsAltered = true;
        }

        public void savePermissions() throws PosixFilePermissionsException {
            if (this.permsAltered) {
                PosixFilePermissions.setPermissions(this.file, this.alteredPerms);
                this.filePerms = this.alteredPerms;
                this.alteredPerms = null;
                this.permsAltered = false;
            }
        }

    }

    private final File baseFile;
    private List<ListEntry> fileList = null;
    private List<PosixFilePermissionsException> loadingExceptions = new ArrayList<PosixFilePermissionsException>();
    private List<PosixFilePermissionsException> savingExceptions = new ArrayList<PosixFilePermissionsException>();

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

    public FileLister(File file) {
        super();
        this.baseFile = file;
    }

    public void loadFiles(boolean recursive) {
        // firstly clear exception list and file list
        this.loadingExceptions.clear();
        if(this.fileList != null) this.fileList.clear();
        try {
            if (recursive && this.baseFile.isDirectory()) {
                fileList = createFileList(this.baseFile, this.loadingExceptions);
            }
            else {
                fileList = new ArrayList<ListEntry>();
                fileList.add(new ListEntry(this.baseFile));
            }
        }
        catch (PosixFilePermissionsException ex) {
            this.loadingExceptions.add(ex);
        }
    }

    public void saveAllPermissions() {
        // firstly clear exception list
        this.savingExceptions.clear();
        for (ListEntry le : this.fileList) {
            try {
                le.savePermissions();
            }
            catch (PosixFilePermissionsException ex) {
                this.savingExceptions.add(ex);
            }
        }
    }

    public List<ListEntry> getFileList() {
        return this.fileList;
    }

    public List<PosixFilePermissionsException> getLoadingExceptions() {
        return this.loadingExceptions;
    }

    public List<PosixFilePermissionsException> getSavingExceptions() {
        return this.savingExceptions;
    }

}
