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
        java.awt.GridBagConstraints gridBagConstraints;

        boxesPanel = new javax.swing.JPanel();
        boxesPanelTitle = new javax.swing.JLabel();
        ownerRBox = new javax.swing.JCheckBox();
        groupRBox = new javax.swing.JCheckBox();
        othersRBox = new javax.swing.JCheckBox();
        ownerWBox = new javax.swing.JCheckBox();
        groupWBox = new javax.swing.JCheckBox();
        othersWBox = new javax.swing.JCheckBox();
        ownerXBox = new javax.swing.JCheckBox();
        groupXBox = new javax.swing.JCheckBox();
        othersXBox = new javax.swing.JCheckBox();
        buttonsPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        setButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        boxesPanel.setLayout(new java.awt.GridBagLayout());

        boxesPanelTitle.setText("Select permissions:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        boxesPanel.add(boxesPanelTitle, gridBagConstraints);

        ownerRBox.setText("Owner read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerRBox, gridBagConstraints);

        groupRBox.setText("Group read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupRBox, gridBagConstraints);

        othersRBox.setText("Others read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersRBox, gridBagConstraints);

        ownerWBox.setText("Owner write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerWBox, gridBagConstraints);

        groupWBox.setText("Group write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupWBox, gridBagConstraints);

        othersWBox.setText("Others write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersWBox, gridBagConstraints);

        ownerXBox.setText("Owner execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerXBox, gridBagConstraints);

        groupXBox.setText("Group execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupXBox, gridBagConstraints);

        othersXBox.setText("Others execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersXBox, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton, new java.awt.GridBagConstraints());

        setButton.setText("Set");
        setButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(setButton, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boxesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boxesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel boxesPanelTitle;
    private javax.swing.JPanel buttonsPanel;
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
    // End of variables declaration//GEN-END:variables
}
