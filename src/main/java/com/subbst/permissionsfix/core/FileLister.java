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
import java.util.concurrent.atomic.AtomicBoolean;

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

        public File getFile() {
            return this.file;
        }

        public Set<PosixFilePermission> getPermissions() {
            return this.filePerms;
        }

        public boolean arePermissionsAltered() {
            return this.permsAltered;
        }

        public Set<PosixFilePermission> getAlteredPermissions() {
            return this.alteredPerms;
        }

        public void alterPermissions(Set<PosixFilePermission> newPerms) {
            this.alteredPerms = newPerms;
            this.permsAltered = true;
        }

        public void revertPermissions() {
            this.alteredPerms = null;
            this.permsAltered = false;
        }

        public void saveFile() throws PosixFilePermissionsException {
            if (arePermissionsAltered()) {
                PosixFilePermissions.setPermissions(this.file, this.alteredPerms);
                this.filePerms = this.alteredPerms;
                this.alteredPerms = null;
                this.permsAltered = false;
            }
        }

    }

    private final File baseFile;
    private AtomicBoolean stopLoading = new AtomicBoolean(false);
    private AtomicBoolean stopSaving = new AtomicBoolean(false);

    private List<ListEntry> fileList = new ArrayList<ListEntry>();
    private List<FileListerListener> fileListerListeners = new ArrayList<FileListerListener>();


    private List<File> listFiles(File file, boolean recursive) {
        if (shouldStopLoading()) return null;

        List<File> retList = new ArrayList<File>();
        retList.add(file);

        // getting files in directory if possible and necessary
        if (recursive && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return retList;
            for (File f : files) {
                List<File> toAdd = listFiles(f, true);
                if (shouldStopLoading() || toAdd == null) return null;
                retList.addAll(toAdd);
            }
        }
        return retList;
    }

    private boolean shouldStopLoading() {
        return this.stopLoading.get();
    }

    private void resetLoadingStopper() {
        this.stopLoading.set(false);
    }

    private boolean shouldStopSaving() {
        return this.stopSaving.get();
    }

    private void resetSavingStopper() {
        this.stopSaving.set(false);
    }

    public FileLister(File file) {
        super();
        this.baseFile = file;
    }

    public void addListener(FileListerListener listener) {
        this.fileListerListeners.add(listener);
    }

    public void removeListener(FileListerListener listener) {
        this.fileListerListeners.remove(listener);
    }

    public FileListerListener[] getListeners() {
        return this.fileListerListeners.toArray(new FileListerListener[0]);
    }

    public void firePrelisting(String fileName, boolean recursive) {
        for (FileListerListener l : this.fileListerListeners) {
            l.prelistingAction(fileName, recursive);
        }
    }

    public void firePreload(int filesCount) {
        for (FileListerListener l : this.fileListerListeners) {
            l.preloadAction(filesCount);
        }
    }

    public void fireAfterload() {
        for (FileListerListener l : this.fileListerListeners) {
            l.afterloadAction();
        }
    }

    public void fireFileLoading(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileLoadingAction(fileName);
        }
    }

    public void fireFileLoadingFailed(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileLoadingFailedAction(fileName);
        }
    }

    public void firePresave(int filesCount) {
        for (FileListerListener l : this.fileListerListeners) {
            l.presaveAction(filesCount);
        }
    }

    public void fireAftersave() {
        for (FileListerListener l : this.fileListerListeners) {
            l.aftersaveAction();
        }
    }

    public void fireFileSaving(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileSavingAction(fileName);
        }
    }

    public void fireFileSavingFailed(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileSavingFailedAction(fileName);
        }
    }

    public void loadFiles(boolean recursive) {
        // firstly clear file list and reset stopper
        this.fileList.clear();
        resetLoadingStopper();

        // inform about listing files
        firePrelisting(this.baseFile.getAbsolutePath(), recursive);

        // create teporaly list with all files to by loaded
        List<File> filesToLoad = listFiles(this.baseFile, recursive);
        if (shouldStopLoading() || filesToLoad == null) return;

        // inform listeners that loading will start soon
        firePreload(filesToLoad.size());

        // fill persistant list and loading permissions to it
        for (File f : filesToLoad) {
            if (shouldStopLoading()) break;
            try {
                // inform about loading
                fireFileLoading(f.getAbsolutePath());

                // loading file
                this.fileList.add(new ListEntry(f));
            }
            catch (PosixFilePermissionsException ex) {
                fireFileLoadingFailed(ex.getFileName());
            }
        }

        // inform listeners that loading ended
        fireAfterload();
    }

    public void stopFilesLoading() {
        this.stopLoading.set(true);
    }

    public void alterPermissions(FileFilter filter, Set<PosixFilePermission> perms) {
        // check all file entries and if it is desired change permissions
        for (ListEntry le : this.fileList) {
            if(filter.accept(le.getFile())) {
                le.alterPermissions(perms);
            }
        }
    }

    public void alterPermissions(int[] indexes, Set<PosixFilePermission> perms) {
        // change permissions of files with given indexes
        for (int i : indexes) {
            this.fileList.get(i).alterPermissions(perms);
        }
    }

    public void saveFiles() {
        // reseting loading stoper
        resetSavingStopper();

        // inform that saving started
        firePresave(this.fileList.size());

        for (ListEntry le : this.fileList) {
            if(shouldStopSaving()) break;
            try {
                // inform about saving
                fireFileSaving(le.getFile().getAbsolutePath());

                // save file
                le.saveFile();
            }
            catch (PosixFilePermissionsException ex) {
                fireFileLoadingFailed(ex.getFileName());
            }
        }

        // inform that saving ended (maybe with error)
        fireAftersave();
    }

    public void stopFilesSaving() {
        this.stopSaving.set(true);
    }

    public final List<ListEntry> getFileList() {
        return this.fileList;
    }

}
