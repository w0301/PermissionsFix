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
import java.util.regex.Pattern;

/**
 * Simple and default filter for FileLister.
 *
 * This filter is used in GUI by default to filter files
 * that will be altered. It allows using regex expressions
 * to define files.
 */
public class FileListerFilter implements FileFilter {
    private FileLister lister = null;
    private boolean acceptingDirectory = true;
    private boolean acceptingHidden = true;
    private Pattern includePattern = null;
    private Pattern excludePattern = null;

    private static boolean isFileOrParentHidden(File file, File baseFile) {
        if (file == null || file.equals(baseFile)) return false;
        return file.isHidden() || isFileOrParentHidden(file.getParentFile(), baseFile);
    }

    /**
     * Creates new filter.
     *
     * Creates new filter with specific FileLister as operative lister.
     *
     * @param lister FileLister object that holds files suitable for this fitler
     */
    public FileListerFilter(FileLister lister) {
        super();
        this.lister = lister;
    }

    /**
     * Check if filter accepts directories.
     *
     * @return true if this filter accepts directories
     */
    public boolean isAcceptingDirectory() {
        return this.acceptingDirectory;
    }

    /**
     * Sets if filter should accept directories.
     *
     * @param val pass true if you want this filter to accept directories,
     * false otherwise
     */
    public void setAcceptingDirectory(boolean val) {
        this.acceptingDirectory = val;
    }

    /**
     * Check if filter accepts hidden files.
     *
     * @return true if this filter accepts hidden files
     */
    public boolean isAcceptingHidden() {
        return this.acceptingHidden;
    }

    /**
     * Sets if filter should accept hidden files.
     *
     * @param val pass true if you want this filter to accept hidden files,
     * false otherwise
     */
    public void setAcceptingHidden(boolean val) {
        this.acceptingHidden = val;
    }

    /**
     * Returns pattern object for include rules.
     *
     * @return Pattern object with include rules
     */
    public Pattern getIncludePattern() {
        return this.includePattern;
    }

    /**
     * Sets new Pattern object for include rules.
     *
     * @param includePattern new Pattern object
     */
    public void setIncludePattern(Pattern includePattern) {
        this.includePattern = includePattern;
    }

    /**
     * Returns pattern object for exclude rules.
     *
     * @return Pattern object with exclude rules
     */
    public Pattern getExcludePattern() {
        return this.excludePattern;
    }

    /**
     * Sets new Pattern object for exclude rules.
     *
     * @param excludePattern new Pattern object
     */
    public void setExcludePattern(Pattern excludePattern) {
        this.excludePattern = excludePattern;
    }

    /**
     * Checks if this filter accepts specific file.
     *
     * @param file file object to check
     * @return true if filter accepts file, false otherwise
     */
    @Override
    public boolean accept(File file) {
        CharSequence filePath = file.getAbsolutePath();
        return file.isDirectory() == isAcceptingDirectory() &&
               isFileOrParentHidden(file, lister.getBaseFile()) == isAcceptingHidden() &&
               (getIncludePattern() == null || getIncludePattern().matcher(filePath).find()) &&
               (getExcludePattern() == null || !getExcludePattern().matcher(filePath).find());
    }

}
