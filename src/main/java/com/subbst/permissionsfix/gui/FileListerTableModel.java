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
import java.io.FileFilter;
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
    private static final ColumnInfo[] columnInfos;

    private List<TableModelListener> tableListeners = new ArrayList<TableModelListener>();

    protected static abstract class ColumnInfo {
        public abstract String getName();
        public abstract String getValue(ListEntry le);
    }

    static {
        columnInfos = new ColumnInfo[] {
            new ColumnInfo() {
                @Override
                public String getName() {
                    return "File name";
                }

                @Override
                public String getValue(ListEntry le) {
                    return le.getFile().getAbsolutePath();
                }
            },
            new ColumnInfo() {
                @Override
                public String getName() {
                    return "Permissions";
                }

                @Override
                public String getValue(ListEntry le) {
                    return permissionsSetToString(le.getPermissions(), le.getFile().isDirectory());
                }
            },
            new ColumnInfo() {
                @Override
                public String getName() {
                    return "New permissions";
                }

                @Override
                public String getValue(ListEntry le) {
                    return le.arePermissionsAltered() ?
                           permissionsSetToString(le.getAlteredPermissions(), le.getFile().isDirectory()) :
                           "not set";
                }
            }
        };
    }

    private static String permissionsSetToString(Set<PosixFilePermission> perms, boolean isDir) {
        if (perms == null) return "not set";
        StringBuilder retStr = new StringBuilder("---------");
        for (PosixFilePermission perm : perms) {
            retStr.setCharAt(perm.getPosition(), perm.getSymbol());
        }
        if(isDir) retStr.insert(0, 'd');
        return retStr.toString();
    }

    public FileListerTableModel(File baseFile) {
        super(baseFile);
    }

    @Override
    public void alterPermissions(FileFilter filter, Set<PosixFilePermission> perms, int alterType) {
        super.alterPermissions(filter, perms, alterType);
        fireTableChanged();
    }

    @Override
    public void alterPermissions(int[] indexes, Set<PosixFilePermission> perms, int alterType) {
        super.alterPermissions(indexes, perms, alterType);
        fireTableChanged();
    }

    @Override
    public void saveFiles() {
        super.saveFiles();
        fireTableChanged();
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
        return columnInfos[i1].getValue(en);
    }

    @Override
    public int getRowCount() {
        return getFileList().size();
    }

    @Override
    public int getColumnCount() {
        return columnInfos.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnInfos[i].getName();
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
