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
package com.subbst.permissionsfix.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runner class for tests.
 *
 * Use main method in this class to run all tests.
 */
public class TestRunner {

    /**
     * Main method that runs all tests.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Class[] allTestClasses = new Class[] {
            PosixFilePermissionsTest.class,
            FileListerTest.class
        };
        Result result = JUnitCore.runClasses(allTestClasses);
        if (result.wasSuccessful()) {
            System.out.println("All tests were successful.");
        }
        else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

}
