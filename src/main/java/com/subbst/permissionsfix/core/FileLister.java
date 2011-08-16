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
import java.io.FileFilter;
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
    public static final class ListEntry {
        private final File file;
        private Set<PosixFilePermission> filePerms = null;
        private Set<PosixFilePermission> alteredPerms = null;
        private boolean permsAltered = false;

        public ListEntry(File file) throws PosixFilePermissionsException {
            this.file = file;
            this.filePerms = PosixFilePermissions.getPermissions(file);
        }

        public boolean isAltered() {
            return this.permsAltered;
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
            if (isAltered()) {
                PosixFilePermissions.setPermissions(this.file, this.alteredPerms);
                this.filePerms = this.alteredPerms;
                this.alteredPerms = null;
                this.permsAltered = false;
            }
        }

    }

    private final File baseFile;

    private List<FileListerListener> listenersList = new ArrayList<FileListerListener>();
    private List<ListEntry> fileList = new ArrayList<ListEntry>();
    private List<PosixFilePermissionsException> loadingExceptions = new ArrayList<PosixFilePermissionsException>();
    private List<PosixFilePermissionsException> savingExceptions = new ArrayList<PosixFilePermissionsException>();

    private static List<File> listFiles(File file, boolean recursive) {
        List<File> retList = new ArrayList<File>();
        retList.add(file);

        // getting files in directory if possible and necessary
        if (recursive && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                retList.addAll(listFiles(f, true));
            }
        }
        return retList;
    }

    public FileLister(File file) {
        super();
        this.baseFile = file;
    }

    public void addListener(FileListerListener listener) {
        this.listenersList.add(listener);
    }

    public void removeListener(FileListerListener listener) {
        this.listenersList.remove(listener);
    }

    public FileListerListener[] getListeners() {
        return this.listenersList.toArray(new FileListerListener[0]);
    }

    public void firePreload(int filesCount) {
        for (FileListerListener l : this.listenersList) {
            l.preloadAction(filesCount);
        }
    }

    public void fireAfterload() {
        for (FileListerListener l : this.listenersList) {
            l.afterloadAction();
        }
    }

    public void fireFileLoaded(ListEntry le) {
        for (FileListerListener l : this.listenersList) {
            l.fileLoadedAction(le);
        }
    }

    public void loadFiles(boolean recursive) {
        // firstly clear exception list and file list
        this.fileList.clear();
        this.loadingExceptions.clear();

        // create teporaly list with all files to by loaded
        List<File> filesToLoad = listFiles(this.baseFile, recursive);

        // inform listeners that loading will start soon
        firePreload(filesToLoad.size());

        // fill persistant list and loading permissions to it
        for (File f : filesToLoad) {
            ListEntry newEntry = null;
            try {
                // loading file
                newEntry = new ListEntry(f);
                this.fileList.add(newEntry);
            }
            catch (PosixFilePermissionsException ex) {
                this.loadingExceptions.add(ex);
            }
            finally {
                // do this even if loading failed, in which case newEntry == null
                fireFileLoaded(newEntry);
            }
        }

        // inform listeners that loading ended
        fireAfterload();
    }

    public void alterPermissions(FileFilter filter, Set<PosixFilePermission> perms) {
        // check all file entries and if it is desired change permissions
        for (ListEntry le : this.fileList) {
            if(filter.accept(le.getFile())) {
                le.alterPermissions(perms);
            }
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

    public final List<ListEntry> getFileList() {
        return this.fileList;
    }

    public final List<PosixFilePermissionsException> getLoadingExceptions() {
        return this.loadingExceptions;
    }

    public final List<PosixFilePermissionsException> getSavingExceptions() {
        return this.savingExceptions;
    }

}
