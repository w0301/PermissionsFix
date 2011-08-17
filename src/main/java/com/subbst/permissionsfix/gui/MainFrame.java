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

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.subbst.permissionsfix.core.FileListerAdapter;
import com.subbst.permissionsfix.core.FileListerListener;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame(String title) {
        super(title);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileNameField = new javax.swing.JTextField();
        recursiveLoadBox = new javax.swing.JCheckBox();
        showChooserButton = new javax.swing.JButton();
        loadFilesButton = new javax.swing.JButton();
        alterSelectedButton = new javax.swing.JButton();
        saveFilesButton = new javax.swing.JButton();
        alterFilterButton = new javax.swing.JButton();
        filesTableScroll = new javax.swing.JScrollPane();
        filesTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        recursiveLoadBox.setText("Recursive");

        showChooserButton.setText("...");
        showChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChooserButtonActionPerformed(evt);
            }
        });

        loadFilesButton.setText("Load");
        loadFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFilesButtonActionPerformed(evt);
            }
        });

        alterSelectedButton.setText("Alter selected...");
        alterSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterSelectedButtonActionPerformed(evt);
            }
        });

        saveFilesButton.setText("Save permissions");
        saveFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFilesButtonActionPerformed(evt);
            }
        });

        alterFilterButton.setText("Alter by filter...");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filesTableScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(fileNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadFilesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recursiveLoadBox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alterSelectedButton)
                        .addGap(18, 18, 18)
                        .addComponent(alterFilterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                        .addComponent(saveFilesButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadFilesButton)
                    .addComponent(recursiveLoadBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filesTableScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterSelectedButton)
                    .addComponent(alterFilterButton)
                    .addComponent(saveFilesButton))
                .addContainerGap())
        );

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
    final FileListerTableModel newModel = new FileListerTableModel(new File(fileName));

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
            dlg.setProgressMsg("All files are loaded");
        }
    };
    newModel.addListener(newListener);

    // loading files
    Thread dialogThread = new Thread(new Runnable() {
        @Override
        public void run() {
            newModel.loadFiles(recursiveLoadBox.isSelected());
        }
    });
    dialogThread.start();

    // showing dialog and handling its output
    dlg.setVisible(true);
    if (dlg.getExitCode() == ProgressDialog.CANCEL_EXIT && !dlg.isFinished()) {
        newModel.stopFilesLoading();
    }
    else {
        // setting new model for table
        newModel.removeListener(newListener);
        filesTable.setModel(newModel);
        filesTableModel = newModel;
    }
}//GEN-LAST:event_loadFilesButtonActionPerformed

private void alterSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterSelectedButtonActionPerformed
    if (filesTableModel == null) {
        JOptionPane.showMessageDialog(this, "You have to load files first.", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    if (filesTable.getSelectedRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "You have to select rows first.", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // showing dialog
    final PermissionsAlterDialog dlg = new PermissionsAlterDialog(this, "Alter permissions");
    dlg.setVisible(true);

    // getting return code of dialog and altering permissions
    if (dlg.getExitCode() == PermissionsAlterDialog.OK_EXIT) {
        this.filesTableModel.alterPermissions(filesTable.getSelectedRows(), dlg.getDialogPermissions());
    }
}//GEN-LAST:event_alterSelectedButtonActionPerformed

private void saveFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFilesButtonActionPerformed
    if (filesTableModel == null) {
        JOptionPane.showMessageDialog(this, "You have to load files and alter them first.", "Info message", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // firstly ask if user really wants to save parmissions
    int ret = JOptionPane.showOptionDialog(this, "Do you reall want to save permissions?", "Save permissions?",
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
            dlg.setProgressMsg("All permissions are saved");
        }
    };
    filesTableModel.addListener(newListener);

    // saving permissions (in separate thread)
    Thread dialogThread = new Thread(new Runnable() {
        @Override
        public void run() {
            filesTableModel.saveAllPermissions();
        }
    });
    dialogThread.start();

    // showing progress dialog and handling its output
    dlg.setVisible(true);
    if (dlg.getExitCode() == ProgressDialog.CANCEL_EXIT && !dlg.isFinished()) {
        filesTableModel.stopFilesSaving();
    }
    filesTableModel.removeListener(newListener);
}//GEN-LAST:event_saveFilesButtonActionPerformed

    private FileListerTableModel filesTableModel = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterFilterButton;
    private javax.swing.JButton alterSelectedButton;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JTable filesTable;
    private javax.swing.JScrollPane filesTableScroll;
    private javax.swing.JButton loadFilesButton;
    private javax.swing.JCheckBox recursiveLoadBox;
    private javax.swing.JButton saveFilesButton;
    private javax.swing.JButton showChooserButton;
    // End of variables declaration//GEN-END:variables
}
