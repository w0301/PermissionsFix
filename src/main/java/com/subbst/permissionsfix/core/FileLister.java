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
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class holds files' list and their permissions.
 *
 * This is core class of PermissionsFix application. It is used
 * for listing files, loading its permissions, altering permissions
 * and saving permissions.
 */
public class FileLister {
    /**
     * Indicates replace policy of permissions altering.
     */
    public static final int REPLACE_ALTER = 1;

    /**
     * Indicates add policy of permissions altering.
     */
    public static final int ADD_ALTER = 2;

    /**
     * Indicates remove policy of permissions altering.
     */
    public static final int REMOVE_ALTER = 3;

    /**
     * Class for storing information of one file.
     *
     * Instances of this class load file's permission.
     * It can alter file's permission and save them.
     * Instaces of this class are saved in list.
     */
    public static final class ListEntry {
        private final File file;
        private Set<PosixFilePermission> filePerms = null;
        private Set<PosixFilePermission> alteredPerms = null;
        private boolean permsAltered = false;

        /**
         * Creates new object.
         *
         * Creates new object and loads file's permissions.
         *
         * @param file file upon which will this object operate
         * @throws PosixFilePermissionsException if getting file's permissions failed
         */
        public ListEntry(File file) throws PosixFilePermissionsException {
            this.file = file;
            this.filePerms = PosixFilePermissions.getPermissions(file);
        }

        /**
         * Returns this object's file.
         *
         * @return file upon which this object operate
         */
        public File getFile() {
            return this.file;
        }

        /**
         * Returns full path to object's file.
         *
         * @return full absoluth path to this object's file
         */
        public String getFileFullPath() {
            return file.getAbsolutePath();
        }

        /**
         * Returns object's file permissions.
         *
         * @return set of permissions loaded by constructor
         */
        public Set<PosixFilePermission> getPermissions() {
            return this.filePerms;
        }

        /**
         * Checks if permissions were altered.
         *
         * @return true if this file's permissions were altered by
         * alterPermissions() method and this altering wasn't saved yet.
         */
        public boolean arePermissionsAltered() {
            return this.permsAltered;
        }

        /**
         * Returns new permissions for object's file.
         *
         * @return set of permissions that will be saved by next call of
         * saveFile() method. If permissions weren't altered returns null.
         */
        public Set<PosixFilePermission> getAlteredPermissions() {
            return this.alteredPerms;
        }

        /**
         * Change permissions for object's file.
         *
         * Set permissions of this object's file to given set of permissions.
         * Note that this method does not change file on your filesystem (call saveFile() to do that).
         *
         * @param newPerms new set of permissions for this object's file
         */
        public void alterPermissions(Set<PosixFilePermission> newPerms) {
            this.alteredPerms = newPerms;
            this.permsAltered = true;
        }

        /**
         * Reverts permissions altering.
         *
         * This method revert previously called alterPermissions() method.
         * After this method invocation saveFile() method does nothing.
         */
        public void revertPermissions() {
            this.alteredPerms = null;
            this.permsAltered = false;
        }

        /**
         * Saves file's permissions.
         *
         * Save files permissions to filesystem. New permissions were
         * set by alterPermissions() method.
         *
         * @throws PosixFilePermissionsException if saving of permissions failed
         */
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

    private static Set<PosixFilePermission> mergePermissionsLists(Set<PosixFilePermission> actual, Set<PosixFilePermission> changer, int alterType) {
        if (alterType != REPLACE_ALTER) {
            Set<PosixFilePermission> retSet = EnumSet.copyOf(actual);
            for (PosixFilePermission perm : changer) {
                if (alterType == ADD_ALTER) retSet.add(perm);
                else if (alterType == REMOVE_ALTER) retSet.remove(perm);
            }
            return retSet;
        }
        return changer;
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

    /**
     * Creates new file lister for given file.
     *
     * New file lister can list only given file. And optionaly
     * all sub files if file is directory. This constructor does not
     * load any files call loadFiles() method to do that.
     *
     * @param file file upon which will this lister work
     * @throws IOException if getting canonical path for file failed
     */
    public FileLister(File file) throws IOException {
        super();
        this.baseFile = file.getCanonicalFile();
    }

    /**
     * Adds listener that will listen lister's events.
     *
     * File lister sends several events during its method
     * performation. This function adds new listener that
     * will receive these events.
     *
     * @param listener new listener to add to internal listeners list
     */
    public void addFileListerListener(FileListerListener listener) {
        this.fileListerListeners.add(listener);
    }

    /**
     * Removes listener from internal list.
     *
     * This method removes previosly added listener (by addFileListerListener())
     * from internal list. This listener will not receive event anymore.
     *
     * @param listener listener object to be removed from internal list
     */
    public void removeFileListerListener(FileListerListener listener) {
        this.fileListerListeners.remove(listener);
    }

    /**
     * Invokes prelistingAction() methods.
     *
     * This methods is called in loadFiles() methods before
     * actual loading and listing of files begin.
     *
     * @param fileName name of base file
     * @param recursive true only if listing is recursive
     */
    protected void firePrelisting(String fileName, boolean recursive) {
        for (FileListerListener l : this.fileListerListeners) {
            l.prelistingAction(fileName, recursive);
        }
    }

    /**
     * Invokes preloadAction() methods.
     *
     * This method is called after listing and before loading of files
     * in loadFiles() method.
     *
     * @param filesCount count of files to load
     */
    protected void firePreload(int filesCount) {
        for (FileListerListener l : this.fileListerListeners) {
            l.preloadAction(filesCount);
        }
    }

    /**
     * Invokes afterloadAction() methods.
     *
     * This method is called at the end of loadFiles() method.
     */
    protected void fireAfterload() {
        for (FileListerListener l : this.fileListerListeners) {
            l.afterloadAction();
        }
    }

    /**
     * Invokes fileLoadingAction() methods.
     *
     * Called before loading of specific file begin in loadFiles() method.
     *
     * @param fileName absoluth path to file which will be loaded
     */
    protected void fireFileLoading(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileLoadingAction(fileName);
        }
    }

    /**
     * Invokes fileLoadingFailedAction() methods.
     *
     * Called when loading of specific failed in loadFiles() method.
     *
     * @param fileName absoluth path to file which failed to load
     */
    protected void fireFileLoadingFailed(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileLoadingFailedAction(fileName);
        }
    }

    /**
     * Invokes presaveAction() methods.
     *
     * Called in saveFiles() method before actual saving begins.
     *
     * @param filesCount count of files to be saved
     */
    protected void firePresave(int filesCount) {
        for (FileListerListener l : this.fileListerListeners) {
            l.presaveAction(filesCount);
        }
    }

    /**
     * Invoked fireAftersave() methods.
     *
     * Called after saving of files in saveFiles() method.
     */
    protected void fireAftersave() {
        for (FileListerListener l : this.fileListerListeners) {
            l.aftersaveAction();
        }
    }

    /**
     * Invokes fileSavingAction() method.
     *
     * Called before saving specific file in saveFiles() method.
     *
     * @param fileName absoluth path to file that will be saved
     */
    protected void fireFileSaving(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileSavingAction(fileName);
        }
    }

    /**
     * Invoked fileSavingFailedAction() methods.
     *
     * Called when saving of specific file failed in saveFiles() method.
     *
     * @param fileName absoluth path to file which saving failed
     */
    protected void fireFileSavingFailed(String fileName) {
        for (FileListerListener l : this.fileListerListeners) {
            l.fileSavingFailedAction(fileName);
        }
    }

    /**
     * Loads files.
     *
     * This method loads files. Actually it loads base file
     * that was specified in constructor of this object. However
     * if recursive argument is true and base file is directory
     * all files in base directory will be loaded recursively.
     *
     * @param recursive if true files will be loaded recursively
     */
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
                this.fileList.add(new ListEntry(f.getCanonicalFile()));
            }
            catch (PosixFilePermissionsException ex) {
                fireFileLoadingFailed(ex.getFileName());
            }
            catch (IOException ex) {
                fireFileLoadingFailed(ex.getMessage());
            }
        }

        // inform listeners that loading ended
        fireAfterload();
    }

    /**
     * Stops file loading.
     *
     * This method stops any concurrent loadFiles() method after
     * actual file will be loaded. This has effect only if loadFiles()
     * method was invoked in different thread than this method.
     */
    public void stopFilesLoading() {
        this.stopLoading.set(true);
    }

    /**
     * Change files' permissions.
     *
     * Change permissions of files previously loaded by loadFiles() method.
     * Each file is firstly tested by given file filter and if filter accepts
     * the file permissions are altered. Permissions are alterd according to
     * altering rule given by alterType.
     * Note that this method does not change files on your filesystem (use saveFiles() to save changes).
     *
     * @param filter filter that has to accept files
     * @param perms permissions set that is used for altering
     * @param alterType altering rule, this is FileLister.ADD_ALTER, FileLister.REMOVE_ALTER
     * or FileLister.REPLACE_ALTER.
     */
    public void alterPermissions(FileFilter filter, Set<PosixFilePermission> perms, int alterType) {
        // check all file entries and if it is desired change permissions
        for (ListEntry le : this.fileList) {
            if(filter.accept(le.getFile())) {
                Set<PosixFilePermission> newPerms = mergePermissionsLists(le.getPermissions(), perms, alterType);
                le.alterPermissions(newPerms);
            }
        }
    }


    /**
     * Change files' permissions.
     *
     * Change permissions of files previously loaded by loadFiles() method.
     * Permissions are altered only for files which indexes are in given array.
     * Permissions are alterd according to altering rule given by alterType.
     * Note that this method does not change files on your filesystem (use saveFiles() to save changes).
     *
     * @param indexes array of indexes of files that will be altered
     * @param perms permissions set that is used for altering
     * @param alterType altering rule, this is FileLister.ADD_ALTER, FileLister.REMOVE_ALTER
     * or FileLister.REPLACE_ALTER.
     */
    public void alterPermissions(int[] indexes, Set<PosixFilePermission> perms, int alterType) {
        // change permissions of files with given indexes
        for (int i : indexes) {
            ListEntry le = this.fileList.get(i);
            Set<PosixFilePermission> newPerms = mergePermissionsLists(le.getPermissions(), perms, alterType);
            le.alterPermissions(newPerms);
        }
    }

    /**
     * Saves files that were altered.
     *
     * This method saves files that were previously altered
     * by one of alter methods. Note that this method change
     * files on your filesystem.
     */
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

    /**
     * Stops file saving.
     *
     * This method stops any concurrent saveFiles() method after
     * actual file will be saved. This has effect only if saveFiles()
     * method was invoked in different thread than this method.
     */
    public void stopFilesSaving() {
        this.stopSaving.set(true);
    }

    /**
     * Returns base file.
     *
     * @return base file of this object
     */
    public final File getBaseFile() {
        return this.baseFile;
    }

    /**
     * Returns list of files.
     *
     * @return list of all files loaded by loadFiles() method.
     */
    public final List<ListEntry> getFileList() {
        return this.fileList;
    }

}
