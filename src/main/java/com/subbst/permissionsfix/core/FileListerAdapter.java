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
 * Adapter class for FileListerListener interface.
 *
 * Extend this class to implement listener if you do not want to
 * implement all functions. This class just implement functions
 * from FileListerListener interface with empty body.
 */
public class FileListerAdapter implements FileListerListener {

    @Override
    public void prelistingAction(String fileName, boolean recursive) {
    }

    @Override
    public void preloadAction(int filesCount) {
    }

    @Override
    public void afterloadAction() {
    }

    @Override
    public void fileLoadingFailedAction(String fileName) {
    }

    @Override
    public void fileLoadingAction(String fileName) {

    }

    @Override
    public void presaveAction(int filesCount) {
    }

    @Override
    public void aftersaveAction() {
    }

    @Override
    public void fileSavingAction(String fileName) {
    }

    @Override
    public void fileSavingFailedAction(String fileName) {
    }

}
