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

import javax.swing.DefaultListModel;

public class ProgressDialog extends javax.swing.JDialog {
    public static final int OK_EXIT = 1;
    public static final int CANCEL_EXIT = 2;
    public static final int OTHER_EXIT = 3;

    private int exitCode = OTHER_EXIT;
    private boolean progressFinished = false;

    public ProgressDialog(java.awt.Frame parent, String title) {
        super(parent, title, true);
        initComponents();
    }

    public void useOkButton(boolean val) {
        this.okButton.setVisible(val);
    }

    public void enableOkButton(boolean val) {
        this.okButton.setEnabled(val);
    }

    public void useCancelButton(boolean val) {
        this.cancelButton.setVisible(val);
    }

    public void enableCancelButton(boolean val) {
        this.cancelButton.setEnabled(val);
    }

    public void useErrorList(boolean val) {
        this.errorListPanel.setVisible(val);
    }

    public void addErrorMsg(String msg) {
        DefaultListModel model = (DefaultListModel) this.errorList.getModel();
        model.addElement(msg);
    }

    public void setProgressBusy(boolean val) {
        this.progressBar.setIndeterminate(val);
    }

    public void setProgressMinimum(int n) {
        this.progressBar.setMinimum(n);
    }

    public void setProgressMaximum(int n) {
        this.progressBar.setMaximum(n);
    }

    public void setProgress(int n) {
        this.progressBar.setValue(n);
    }

    public void setFinished(boolean val) {
        this.progressFinished = val;
    }

    public void setProgressMsgTitle(String title) {
        this.progressBarMsgTitle.setText(title);
    }

    public void setProgressMsg(String msg) {
        this.progressBarMsg.setText(msg);
    }

    public boolean isProgressBusy() {
        return this.progressBar.isIndeterminate();
    }

    public int getProgressMinimum() {
        return this.progressBar.getMinimum();
    }

    public int getProgressMaximum() {
        return this.progressBar.getMaximum();
    }

    public int getProgress() {
        return this.progressBar.getValue();
    }

    public boolean isFinished() {
        return this.progressFinished;
    }

    public int getExitCode() {
        return this.exitCode;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorListPanel = new javax.swing.JPanel();
        errorListTitle = new javax.swing.JLabel();
        errorListScroll = new javax.swing.JScrollPane();
        errorList = new javax.swing.JList();
        buttonsPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        progressBarPanel = new javax.swing.JPanel();
        progressBarMsg = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        progressBarMsgTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        errorListTitle.setText("Errors:");

        errorList.setModel(new javax.swing.DefaultListModel());
        errorListScroll.setViewportView(errorList);

        javax.swing.GroupLayout errorListPanelLayout = new javax.swing.GroupLayout(errorListPanel);
        errorListPanel.setLayout(errorListPanelLayout);
        errorListPanelLayout.setHorizontalGroup(
            errorListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorListPanelLayout.createSequentialGroup()
                .addGroup(errorListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(errorListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(errorListTitle))
                    .addGroup(errorListPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(errorListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)))
                .addContainerGap())
        );
        errorListPanelLayout.setVerticalGroup(
            errorListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorListPanelLayout.createSequentialGroup()
                .addComponent(errorListTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(okButton);

        progressBarMsg.setText("progress msg");

        progressBarMsgTitle.setText("progress msg title:");

        javax.swing.GroupLayout progressBarPanelLayout = new javax.swing.GroupLayout(progressBarPanel);
        progressBarPanel.setLayout(progressBarPanelLayout);
        progressBarPanelLayout.setHorizontalGroup(
            progressBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(progressBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(progressBarPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(progressBarMsg))
                    .addComponent(progressBarMsgTitle)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addContainerGap())
        );
        progressBarPanelLayout.setVerticalGroup(
            progressBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressBarPanelLayout.createSequentialGroup()
                .addComponent(progressBarMsgTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBarMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addComponent(errorListPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    this.exitCode = OK_EXIT;
    setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.exitCode = CANCEL_EXIT;
    setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JList errorList;
    private javax.swing.JPanel errorListPanel;
    private javax.swing.JScrollPane errorListScroll;
    private javax.swing.JLabel errorListTitle;
    private javax.swing.JButton okButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel progressBarMsg;
    private javax.swing.JLabel progressBarMsgTitle;
    private javax.swing.JPanel progressBarPanel;
    // End of variables declaration//GEN-END:variables
}
