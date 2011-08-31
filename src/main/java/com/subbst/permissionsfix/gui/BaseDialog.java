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

/**
 * Base class for all dialogs.
 *
 * This abstract class provide some common methods that
 * are suitable for all dialogs classes in PermissionsFix.
 */
public abstract class BaseDialog extends JDialog {
    /**
     * Return code when cancel button is pressed.
     */
    public static final int CANCEL_EXIT = 2;

    /**
     * Return code when ok button is pressed.
     */
    public static final int OK_EXIT = 1;

    /**
     * Return code when dialog is ended by undefined event.
     */
    public static final int OTHER_EXIT = 3;

    /**
     * Variable that holds exit code of dialog.
     */
    protected int exitCode = OTHER_EXIT;

    /**
     * Creates new dialog.
     *
     * @param parent parent frame of dialog
     * @param title title string for new dialog
     */
    public BaseDialog(Frame parent, String title) {
        super(parent, title, true);
    }

    /**
     * Shows dialog to user.
     *
     * @return exit code of dialog
     */
    public int showDialog() {
        setLocationRelativeTo(getParent());
        setVisible(true);
        return this.exitCode;
    }

}
