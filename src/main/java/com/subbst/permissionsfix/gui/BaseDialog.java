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
package com.subbst.permissionsfix.gui;

import java.awt.Frame;

import javax.swing.JDialog;

public abstract class BaseDialog extends JDialog {
    public static final int CANCEL_EXIT = 2;
    public static final int OK_EXIT = 1;
    public static final int OTHER_EXIT = 3;

    protected int exitCode = OTHER_EXIT;

    public BaseDialog(Frame parent, String title) {
        super(parent, title, true);
    }

    public int showDialog() {
        setLocationRelativeTo(getParent());
        setVisible(true);
        return this.exitCode;
    }

}
