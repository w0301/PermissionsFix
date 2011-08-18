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

public class FileListerFilter implements FileFilter {
    private boolean acceptingDirectory = true;
    private boolean acceptingHidden = true;
    private Pattern includePattern = null;
    private Pattern excludePattern = null;

    private static boolean isFileOrParentHidden(File file) {
        if (file == null) return false;
        return file.isHidden() || isFileOrParentHidden(file.getParentFile());
    }

    public FileListerFilter() {
        super();
    }

    public boolean isAcceptingDirectory() {
        return this.acceptingDirectory;
    }

    public void setAcceptingDirectory(boolean val) {
        this.acceptingDirectory = val;
    }

    public boolean isAcceptingHidden() {
        return this.acceptingHidden;
    }

    public void setAcceptingHidden(boolean val) {
        this.acceptingHidden = val;
    }

    public Pattern getIncludePattern() {
        return this.includePattern;
    }

    public void setIncludePattern(Pattern includePattern) {
        this.includePattern = includePattern;
    }

    public Pattern getExcludePattern() {
        return this.excludePattern;
    }

    public void setExcludePattern(Pattern excludePattern) {
        this.excludePattern = excludePattern;
    }

    @Override
    public boolean accept(File file) {
        CharSequence filePath = file.getAbsolutePath();
        return file.isDirectory() == isAcceptingDirectory() &&
               isFileOrParentHidden(file) == isAcceptingHidden() &&
               (getIncludePattern() == null || getIncludePattern().matcher(filePath).find()) &&
               (getExcludePattern() == null || !getExcludePattern().matcher(filePath).find());
    }

}
