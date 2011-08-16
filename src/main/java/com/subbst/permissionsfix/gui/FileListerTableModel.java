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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.subbst.permissionsfix.core.FileLister;
import com.subbst.permissionsfix.core.PosixFilePermission;

/**
 * Model for JTable component.
 */
public class FileListerTableModel extends FileLister implements TableModel {

    private static final int COLUMNS_COUNT = 3;
    private static final String[] columnsNames;


    private List<TableModelListener> tableListeners = new ArrayList<TableModelListener>();

    static {
        columnsNames = new String[] {
            "File name",
            "Permissions",
            "New permissions"
        };
    }

    private static String permissionsSetToString(Set<PosixFilePermission> perms, boolean isDir) {
        StringBuilder retStr = new StringBuilder("---------");
        if (perms != null) {
            for (PosixFilePermission perm : perms) {
                retStr.setCharAt(perm.getPosition(), perm.getSymbol());
            }
        }
        if(isDir) retStr.insert(0, 'd');
        return retStr.toString();
    }

    public FileListerTableModel(File baseFile) {
        super(baseFile);
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        this.tableListeners.add(tl);
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        this.tableListeners.remove(tl);
    }

    public void fireTableChanged() {
        for (TableModelListener tl : tableListeners) {
            tl.tableChanged(new TableModelEvent(this));
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
    }

    @Override
    public Object getValueAt(int i, int i1) {
        ListEntry en = getFileList().get(i);
        if(i1 == 0) return en.getFile().getAbsolutePath();
        if(i1 == 1) return permissionsSetToString(en.getPermissions(), en.getFile().isDirectory());
        return permissionsSetToString(en.getAlteredPermissions(), en.getFile().isDirectory());
    }

    @Override
    public int getRowCount() {
        return getFileList().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_COUNT;
    }

    @Override
    public String getColumnName(int i) {
        return columnsNames[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

}
