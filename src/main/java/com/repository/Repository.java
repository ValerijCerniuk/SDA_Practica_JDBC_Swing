package com.repository;

import com.util.DatabaseQueries;
import com.util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * default service of project repositories interacting with database
 */

public class Repository {
    // galetu grazinti list jaigu resultSeta sumapintume
    public ResultSet findAllInTable(String tabName) {
        ResultSet projectResultSet = null;
        try {
            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
            projectResultSet = statement.executeQuery(DatabaseQueries.FIND_ALL + tabName);
            DatabaseUtil.connectionToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectResultSet;
    }

    public ResultSet findColumnByTableAndColumnNames(String tableName , String columnName) {
        ResultSet projectResultSet = null;
        try {
            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
            String sql = "SELECT * FROM" + tableName + "WHERE" + columnName;
            projectResultSet = statement.executeQuery(sql);
            DatabaseUtil.connectionToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectResultSet;
    }

    public ResultSet findByName(String projectName) {
        ResultSet projectResultSet = null;

        try {
            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
            String sql = String.format(DatabaseQueries.FIND_PROJECT_BY_NAME, projectName);
            projectResultSet = statement.executeQuery(sql);
            DatabaseUtil.connectionToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectResultSet;
    }

    public void addTable(String tableName) {
        int affectedRows = 0;
        if (findAllInTable(tableName) == null) {
            try {
                Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
                String sql = String.format(DatabaseQueries.ADD_TABLE_BY_NAME, tableName);
                statement.executeUpdate(sql);
                DatabaseUtil.connectionToDatabase().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Already exist, not added");
        }
    }

// pabandit jeskot su parametru listu
//    public ResultSet findByNameAndBudget(String projectName) {
//        ResultSet projectResultSet = null;
//
//        try {
//            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
//            String sql = String.format(DatabaseQueries.FIND_TABLE_BY_NAME, projectName, budget);
//            projectResultSet = statement.executeQuery(sql);
//            DatabaseUtil.connectionToDatabase().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return projectResultSet;
//    }

    public void deleteTabByName (String tabName){

        try {
            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
            String sql = String.format(DatabaseQueries.DELETE_TABLE_BY_NAME, tabName);
            statement.executeUpdate(sql);
            DatabaseUtil.connectionToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Deleted");
    }

    public void updateTable(String sql) {
        try {
            Statement statement = DatabaseUtil.connectionToDatabase().createStatement();
            statement.executeUpdate(sql);
            DatabaseUtil.connectionToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}