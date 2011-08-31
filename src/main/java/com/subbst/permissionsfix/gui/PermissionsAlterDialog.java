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
import java.io.FileFilter;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.subbst.permissionsfix.core.FileLister;
import com.subbst.permissionsfix.core.FileListerFilter;
import com.subbst.permissionsfix.core.PosixFilePermission;

/**
 * Class of permissions alter dialog.
 */
public class PermissionsAlterDialog extends BaseDialog {

    public PermissionsAlterDialog(Frame parent, String title) {
        super(parent, title);
        initComponents();
        alterTypeBoxes.add(replacePermissionsBox);
        alterTypeBoxes.add(addPermissionsBox);
        alterTypeBoxes.add(removePermissionsBox);
    }

    public void usePatternSelection(boolean val) {
        patternSelectionPanel.setVisible(val);
        pack();
    }

    public FileFilter getDialogFileFilter(FileLister lister) throws PatternSyntaxException {
        FileListerFilter retFilter = new FileListerFilter(lister);
        retFilter.setAcceptingDirectory(alterDirectoriesBox.isSelected());
        retFilter.setAcceptingHidden(alterHiddenBox.isSelected());

        String includePatternText = includePatternField.getText();
        if (!includePatternText.equals("")) retFilter.setIncludePattern(Pattern.compile(includePatternText, Pattern.CASE_INSENSITIVE));

        String excludePatternString = excludePatternField.getText();
        if (!excludePatternString.equals("")) retFilter.setExcludePattern(Pattern.compile(excludePatternString, Pattern.CASE_INSENSITIVE));

        return retFilter;
    }

    public Set<PosixFilePermission> getDialogPermissions() {
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

    public int getDialogAlterType() {
        return replacePermissionsBox.isSelected() ? FileLister.REPLACE_ALTER :
               (addPermissionsBox.isSelected() ? FileLister.ADD_ALTER : FileLister.REMOVE_ALTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        alterTypeBoxes = new javax.swing.ButtonGroup();
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
        replacePermissionsBox = new javax.swing.JRadioButton();
        addPermissionsBox = new javax.swing.JRadioButton();
        removePermissionsBox = new javax.swing.JRadioButton();
        buttonsPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        setButton = new javax.swing.JButton();
        patternSelectionPanel = new javax.swing.JPanel();
        includePatternLabel = new javax.swing.JLabel();
        includePatternField = new javax.swing.JTextField();
        excludePatternLabel = new javax.swing.JLabel();
        excludePatternField = new javax.swing.JTextField();
        alterDirectoriesBox = new javax.swing.JCheckBox();
        alterHiddenBox = new javax.swing.JCheckBox();
        includeOptionsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        boxesPanel.setLayout(new java.awt.GridBagLayout());

        boxesPanelTitle.setText("Select permissions:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        boxesPanel.add(boxesPanelTitle, gridBagConstraints);

        ownerRBox.setText("Owner read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerRBox, gridBagConstraints);

        groupRBox.setText("Group read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupRBox, gridBagConstraints);

        othersRBox.setText("Others read");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersRBox, gridBagConstraints);

        ownerWBox.setText("Owner write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerWBox, gridBagConstraints);

        groupWBox.setText("Group write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupWBox, gridBagConstraints);

        othersWBox.setText("Others write");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersWBox, gridBagConstraints);

        ownerXBox.setText("Owner execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(ownerXBox, gridBagConstraints);

        groupXBox.setText("Group execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(groupXBox, gridBagConstraints);

        othersXBox.setText("Others execute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        boxesPanel.add(othersXBox, gridBagConstraints);

        replacePermissionsBox.setSelected(true);
        replacePermissionsBox.setText("Replace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        boxesPanel.add(replacePermissionsBox, gridBagConstraints);

        addPermissionsBox.setText("Add");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        boxesPanel.add(addPermissionsBox, gridBagConstraints);

        removePermissionsBox.setText("Remove");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        boxesPanel.add(removePermissionsBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(boxesPanel, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        buttonsPanel.add(cancelButton, gridBagConstraints);

        setButton.setText("Ok");
        setButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        buttonsPanel.add(setButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        patternSelectionPanel.setLayout(new java.awt.GridBagLayout());

        includePatternLabel.setText("Include pattern:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        patternSelectionPanel.add(includePatternLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        patternSelectionPanel.add(includePatternField, gridBagConstraints);

        excludePatternLabel.setText("Exclude pattern:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        patternSelectionPanel.add(excludePatternLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        patternSelectionPanel.add(excludePatternField, gridBagConstraints);

        alterDirectoriesBox.setText("Only directories");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        patternSelectionPanel.add(alterDirectoriesBox, gridBagConstraints);

        alterHiddenBox.setText("Hidden files also");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        patternSelectionPanel.add(alterHiddenBox, gridBagConstraints);

        includeOptionsLabel.setText("Include options:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        patternSelectionPanel.add(includeOptionsLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(patternSelectionPanel, gridBagConstraints);

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
    private javax.swing.JRadioButton addPermissionsBox;
    private javax.swing.JCheckBox alterDirectoriesBox;
    private javax.swing.JCheckBox alterHiddenBox;
    private javax.swing.ButtonGroup alterTypeBoxes;
    private javax.swing.JPanel boxesPanel;
    private javax.swing.JLabel boxesPanelTitle;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField excludePatternField;
    private javax.swing.JLabel excludePatternLabel;
    private javax.swing.JCheckBox groupRBox;
    private javax.swing.JCheckBox groupWBox;
    private javax.swing.JCheckBox groupXBox;
    private javax.swing.JLabel includeOptionsLabel;
    private javax.swing.JTextField includePatternField;
    private javax.swing.JLabel includePatternLabel;
    private javax.swing.JCheckBox othersRBox;
    private javax.swing.JCheckBox othersWBox;
    private javax.swing.JCheckBox othersXBox;
    private javax.swing.JCheckBox ownerRBox;
    private javax.swing.JCheckBox ownerWBox;
    private javax.swing.JCheckBox ownerXBox;
    private javax.swing.JPanel patternSelectionPanel;
    private javax.swing.JRadioButton removePermissionsBox;
    private javax.swing.JRadioButton replacePermissionsBox;
    private javax.swing.JButton setButton;
    // End of variables declaration//GEN-END:variables
}
