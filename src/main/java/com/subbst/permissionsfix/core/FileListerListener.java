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
 * Listener interaface that has to be implemented by class
 * to listen to events sent by FileLister class instance.
 */
public interface FileListerListener extends EventListener {

    public void prelistingAction(String fileName, boolean recursive);

    public void preloadAction(int filesCount);

    public void afterloadAction();

    public void fileLoadingAction(String fileName);

    public void fileLoadingFailedAction(String fileName);

    public void presaveAction(int filesCount);

    public void aftersaveAction();

    public void fileSavingAction(String fileName);

    public void fileSavingFailedAction(String fileName);

}
