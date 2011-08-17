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
package com.subbst.permissionsfix;

import java.awt.EventQueue;

import com.subbst.permissionsfix.gui.MainFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Entry point to a program.
 *
 * This class provide main method of application.
 * Run this method to run application's user interface.
 */
public class MainClass {

    /**
     * Application's main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // setting platform look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
           System.err.println("Unable to load platform look and feel. Using default one.");
        }

        // running swing frame
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame("PermissionsFix").setVisible(true);
            }
        });
    }

}
