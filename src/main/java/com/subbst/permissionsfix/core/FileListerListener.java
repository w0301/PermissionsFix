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

import java.util.EventListener;

/**
 * Listener interface for FileLister events.
 *
 * This is interface that has to be implemented by your own listener
 * classes. If you do so you can add instance of your class to FileLister
 * object's listeners list.
 */
public interface FileListerListener extends EventListener {

    /**
     * Called before listing files.
     *
     * This method is called in FileLister.loadFiles() method
     * before all files are listed (this is even before loading).
     *
     * @param fileName name of base file
     * @param recursive true if listing will be recursive
     */
    public void prelistingAction(String fileName, boolean recursive);

    /**
     * Called before files loading.
     *
     * This method is called right before files are being load.
     *
     * @param filesCount count of files to be load
     */
    public void preloadAction(int filesCount);

    /**
     * Called after loading of files.
     *
     * This method is called when loading of files is finished.
     */
    public void afterloadAction();

    /**
     * Called before file is load.
     *
     * @param fileName name of file that will be load
     */
    public void fileLoadingAction(String fileName);

    /**
     * Called when loading of file fail.
     *
     * @param fileName name of file that failed to load
     */
    public void fileLoadingFailedAction(String fileName);

    /**
     * Called before files saving.
     *
     * This method is called right before files are being save.
     *
     * @param filesCount count of files to save
     */
    public void presaveAction(int filesCount);

    /**
     * Called after saving of files.
     *
     * This method is called when savinf of files is finished.
     */
    public void aftersaveAction();

    /**
     * Called before file is saved.
     *
     * @param fileName name of file that will be saved
     */
    public void fileSavingAction(String fileName);

    /**
     * Called when saving of file failed.
     *
     * @param fileName name of file that saving failed
     */
    public void fileSavingFailedAction(String fileName);

}
