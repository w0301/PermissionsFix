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
        java.awt.GridBagConstraints gridBagConstraints;

        progressBarPanel = new javax.swing.JPanel();
        progressBarMsg = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        progressBarMsgTitle = new javax.swing.JLabel();
        errorListPanel = new javax.swing.JPanel();
        errorListTitle = new javax.swing.JLabel();
        errorListScroll = new javax.swing.JScrollPane();
        errorList = new javax.swing.JList();
        buttonsPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        progressBarPanel.setLayout(new java.awt.GridBagLayout());

        progressBarMsg.setText("progress msg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 25);
        progressBarPanel.add(progressBarMsg, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        progressBarPanel.add(progressBar, gridBagConstraints);

        progressBarMsgTitle.setText("progress msg title:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        progressBarPanel.add(progressBarMsgTitle, gridBagConstraints);

        errorListPanel.setLayout(new java.awt.GridBagLayout());

        errorListTitle.setText("Errors:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        errorListPanel.add(errorListTitle, gridBagConstraints);

        errorList.setModel(new javax.swing.DefaultListModel());
        errorListScroll.setViewportView(errorList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        errorListPanel.add(errorListScroll, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(cancelButton, gridBagConstraints);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(okButton, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(progressBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
