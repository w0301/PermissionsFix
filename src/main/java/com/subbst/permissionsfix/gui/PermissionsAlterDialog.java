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

import java.util.EnumSet;
import java.util.Set;

import com.subbst.permissionsfix.core.PosixFilePermission;

public class PermissionsAlterDialog extends javax.swing.JDialog {
    public static final int OK_EXIT = 1;
    public static final int CANCEL_EXIT = 2;
    public static final int OTHER_EXIT = 3;

    private int exitCode = OTHER_EXIT;

    public PermissionsAlterDialog(java.awt.Frame parent, String title) {
        super(parent, title, true);
        initComponents();
    }

    Set<PosixFilePermission> getDialogPermissions() {
        Set<PosixFilePermission> retSet = EnumSet.noneOf(PosixFilePermission.class);
        if (ownerRBox.isSelected()) retSet.add(PosixFilePermission.OWNER_READ);
        if (ownerWBox.isSelected()) retSet.add(PosixFilePermission.OWNER_WRITE);
        if (ownerXBox.isSelected()) retSet.add(PosixFilePermission.OWNER_EXECUTE);

        if (groupRBox.isSelected()) retSet.add(PosixFilePermission.GROUP_READ);
        if (groupWBox.isSelected()) retSet.add(PosixFilePermission.GROUP_WRITE);
        if (groupXBox.isSelected()) retSet.add(PosixFilePermission.GROUP_EXECUTE);

        if (othersRBox.isSelected()) retSet.add(PosixFilePermission.OTHERS_READ);
        if (othersWBox.isSelected()) retSet.add(PosixFilePermission.OTHERS_WRITE);
        if (othersXBox.isSelected()) retSet.add(PosixFilePermission.OTHERS_EXECUTE);
        return retSet;
    }

    public int getExitCode() {
        return this.exitCode;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxesPanel = new javax.swing.JPanel();
        ownerRBox = new javax.swing.JCheckBox();
        groupRBox = new javax.swing.JCheckBox();
        othersRBox = new javax.swing.JCheckBox();
        ownerWBox = new javax.swing.JCheckBox();
        groupWBox = new javax.swing.JCheckBox();
        othersWBox = new javax.swing.JCheckBox();
        ownerXBox = new javax.swing.JCheckBox();
        groupXBox = new javax.swing.JCheckBox();
        othersXBox = new javax.swing.JCheckBox();
        titleLabel = new javax.swing.JLabel();
        setButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        boxesPanel.setLayout(new java.awt.GridLayout(3, 3));

        ownerRBox.setText("Owner read");
        boxesPanel.add(ownerRBox);

        groupRBox.setText("Group read");
        boxesPanel.add(groupRBox);

        othersRBox.setText("Others read");
        boxesPanel.add(othersRBox);

        ownerWBox.setText("Owner write");
        boxesPanel.add(ownerWBox);

        groupWBox.setText("Group write");
        boxesPanel.add(groupWBox);

        othersWBox.setText("Others write");
        boxesPanel.add(othersWBox);

        ownerXBox.setText("Owner execute");
        boxesPanel.add(ownerXBox);

        groupXBox.setText("Group execute");
        boxesPanel.add(groupXBox);

        othersXBox.setText("Others execute");
        boxesPanel.add(othersXBox);

        titleLabel.setText("Select permissions:");

        setButton.setText("Set");
        setButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(setButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(setButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void setButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonActionPerformed
    this.exitCode = OK_EXIT;
    setVisible(false);
}//GEN-LAST:event_setButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.exitCode = CANCEL_EXIT;
    setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxesPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox groupRBox;
    private javax.swing.JCheckBox groupWBox;
    private javax.swing.JCheckBox groupXBox;
    private javax.swing.JCheckBox othersRBox;
    private javax.swing.JCheckBox othersWBox;
    private javax.swing.JCheckBox othersXBox;
    private javax.swing.JCheckBox ownerRBox;
    private javax.swing.JCheckBox ownerWBox;
    private javax.swing.JCheckBox ownerXBox;
    private javax.swing.JButton setButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
