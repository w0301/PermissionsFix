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

import java.io.File;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.subbst.permissionsfix.core.FileListerAdapter;
import com.subbst.permissionsfix.core.FileListerListener;

/**
 * Class of main frame of application.
 */
public class MainFrame extends javax.swing.JFrame {

    private void showNotLoadedMsg() {
        JOptionPane.showMessageDialog(this, "You have to load files first.", "Info message", JOptionPane.INFORMATION_MESSAGE);
    }

    public MainFrame(String title) {
        super(title);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        filesLoaderPanel = new javax.swing.JPanel();
        showChooserButton = new javax.swing.JButton();
        fileNameField = new javax.swing.JTextField();
        loadFilesButton = new javax.swing.JButton();
        recursiveLoadBox = new javax.swing.JCheckBox();
        filesTablePanel = new javax.swing.JPanel();
        filesTableScroll = new javax.swing.JScrollPane();
        filesTable = new javax.swing.JTable();
        buttonsPanel = new javax.swing.JPanel();
        alterFilterButton = new javax.swing.JButton();
        saveFilesButton = new javax.swing.JButton();
        alterSelectedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        filesLoaderPanel.setLayout(new java.awt.GridBagLayout());

        showChooserButton.setText("...");
        showChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChooserButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -5;
        gridBagConstraints.ipady = -10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 0, 0);
        filesLoaderPanel.add(showChooserButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        filesLoaderPanel.add(fileNameField, gridBagConstraints);

        loadFilesButton.setText("Load");
        loadFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFilesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
        filesLoaderPanel.add(loadFilesButton, gridBagConstraints);

        recursiveLoadBox.setText("Recursive");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        filesLoaderPanel.add(recursiveLoadBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(filesLoaderPanel, gridBagConstraints);

        filesTablePanel.setPreferredSize(new java.awt.Dimension(450, 200));
        filesTablePanel.setLayout(new java.awt.GridBagLayout());

        filesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        filesTable.setColumnSelectionAllowed(true);
        filesTable.getTableHeader().setReorderingAllowed(false);
        filesTableScroll.setViewportView(filesTable);
        filesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        filesTablePanel.add(filesTableScroll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(filesTablePanel, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        alterFilterButton.setText("Alter by filter...");
        alterFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterFilterButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        buttonsPanel.add(alterFilterButton, gridBagConstraints);

        saveFilesButton.setText("Save permissions");
        saveFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFilesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        buttonsPanel.add(saveFilesButton, gridBagConstraints);

        alterSelectedButton.setText("Alter selected...");
        alterSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterSelectedButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        buttonsPanel.add(alterSelectedButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void showChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showChooserButtonActionPerformed
    // showing file chooser dialog
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int retVal = chooser.showOpenDialog(this);

    // handling output from chooser dialog
    if (retVal == JFileChooser.APPROVE_OPTION) {
        fileNameField.setText(chooser.getSelectedFile().getAbsolutePath());
    }
}//GEN-LAST:event_showChooserButtonActionPerformed

private void loadFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFilesButtonActionPerformed
    // creating file lister (actually a model for table)
    String fileName = fileNameField.getText();
    final FileListerTableModel newModel;
    try {
        newModel = new FileListerTableModel(new File(fileName));
    }
    catch (IOException ex) {
        // this is thrown only on some platform
        JOptionPane.showMessageDialog(this, "File does not exist.", "Error message", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // setting up progress dialog
    final ProgressDialog dlg = new ProgressDialog(this, "Loading files");
    final FileListerListener newListener = new FileListerAdapter() {
        private int lastProgress = 0;

        @Override
        public void prelistingAction(String fileName, boolean recursive) {
            dlg.enableOkButton(false);
            dlg.setProgressBusy(true);
            dlg.setProgressMsgTitle("Currently loading:");
            dlg.setProgressMsg("Listing directory " + fileName);
        }

        @Override
        public void preloadAction(int filesCount) {
            dlg.enableOkButton(false);
            dlg.setProgressBusy(false);
            dlg.setProgress(0);
            dlg.setProgressMaximum(filesCount);
            dlg.setProgressMsg("");
        }

        @Override
        public void fileLoadingAction(String fileName) {
            dlg.setProgressMsg("Loading: " + fileName);
            dlg.setProgress(++this.lastProgress);
        }

        @Override
        public void fileLoadingFailedAction(String fileName) {
            dlg.addErrorMsg("Failed to load: " + fileName);
        }

        @Override
        public void afterloadAction() {
            dlg.enableOkButton(true);
            dlg.setFinished(true);
            dlg.setProgressMsg("Files have been loaded");
        }
    };
    newModel.addFileListerListener(newListener);

    // loading files
    Thread dialogThread = new Thread(new Runnable() {
        @Override
        public void run() {
            newModel.loadFiles(recursiveLoadBox.isSelected());
        }
    });
    dialogThread.start();

    // showing dialog and handling its output
    if (dlg.showDialog() == ProgressDialog.CANCEL_EXIT && !dlg.isFinished()) {
        newModel.stopFilesLoading();
    }
    else {
        // setting new model for table
        newModel.removeFileListerListener(newListener);
        filesTable.setModel(newModel);
        filesTableModel = newModel;
    }
}//GEN-LAST:event_loadFilesButtonActionPerformed

private void alterSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterSelectedButtonActionPerformed
    if (filesTableModel == null) {
        showNotLoadedMsg();
        return;
    }
    if (filesTable.getSelectedRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "You have to select rows first.", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // showing dialog adn getting return code
    final PermissionsAlterDialog dlg = new PermissionsAlterDialog(this, "Alter permissions");
    dlg.usePatternSelection(false);
    if (dlg.showDialog() == PermissionsAlterDialog.OK_EXIT) {
        this.filesTableModel.alterPermissions(filesTable.getSelectedRows(), dlg.getDialogPermissions(), dlg.getDialogAlterType());
    }
}//GEN-LAST:event_alterSelectedButtonActionPerformed

private void saveFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFilesButtonActionPerformed
    if (filesTableModel == null) {
        showNotLoadedMsg();
        return;
    }

    // firstly ask if user really wants to save parmissions
    int ret = JOptionPane.showOptionDialog(this, "Do you really want to save permissions?", "Save permissions?",
                                           JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    if(ret != JOptionPane.YES_OPTION) return;

    // setup progress dialog
    final ProgressDialog dlg = new ProgressDialog(this, "Saving files");
    final FileListerListener newListener = new FileListerAdapter() {
        private int lastProgress = 0;

        @Override
        public void presaveAction(int filesCount) {
            dlg.enableOkButton(false);
            dlg.setProgressBusy(false);
            dlg.setProgress(0);
            dlg.setProgressMaximum(filesCount);
            dlg.setProgressMsg("");
            dlg.setProgressMsgTitle("Currently saving:");
        }

        @Override
        public void fileSavingAction(String fileName) {
            dlg.setProgressMsg("Saving: " + fileName);
            dlg.setProgress(++this.lastProgress);
        }

        @Override
        public void fileSavingFailedAction(String fileName) {
            dlg.addErrorMsg("Failed to save: " + fileName);
        }

        @Override
        public void aftersaveAction() {
            dlg.enableOkButton(true);
            dlg.setFinished(true);
            dlg.setProgressMsg("Files have been saved");
        }
    };
    filesTableModel.addFileListerListener(newListener);

    // saving permissions (in separate thread)
    Thread dialogThread = new Thread(new Runnable() {
        @Override
        public void run() {
            filesTableModel.saveFiles();
        }
    });
    dialogThread.start();

    // showing progress dialog and handling its output
    if (dlg.showDialog() == ProgressDialog.CANCEL_EXIT && !dlg.isFinished()) {
        filesTableModel.stopFilesSaving();
    }
    filesTableModel.removeFileListerListener(newListener);
}//GEN-LAST:event_saveFilesButtonActionPerformed

private void alterFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterFilterButtonActionPerformed
    if (filesTableModel == null) {
        showNotLoadedMsg();
        return;
    }

    // showing dialog adn getting return code
    final PermissionsAlterDialog dlg = new PermissionsAlterDialog(this, "Alter permissions");
    dlg.usePatternSelection(true);
    if (dlg.showDialog() == PermissionsAlterDialog.OK_EXIT) {
        try {
            this.filesTableModel.alterPermissions(dlg.getDialogFileFilter(filesTableModel), dlg.getDialogPermissions(), dlg.getDialogAlterType());
        }
        catch (PatternSyntaxException ex) {
            JOptionPane.showMessageDialog(this, "Bad include/exclude pattern: " + ex.getMessage(), "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
        }
    }
}//GEN-LAST:event_alterFilterButtonActionPerformed

    private FileListerTableModel filesTableModel = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterFilterButton;
    private javax.swing.JButton alterSelectedButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JPanel filesLoaderPanel;
    private javax.swing.JTable filesTable;
    private javax.swing.JPanel filesTablePanel;
    private javax.swing.JScrollPane filesTableScroll;
    private javax.swing.JButton loadFilesButton;
    private javax.swing.JCheckBox recursiveLoadBox;
    private javax.swing.JButton saveFilesButton;
    private javax.swing.JButton showChooserButton;
    // End of variables declaration//GEN-END:variables
}
