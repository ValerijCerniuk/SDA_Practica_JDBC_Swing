package com.util;

public class DatabaseQueries {
    public static final String FIND_ALL = "SELECT * FROM ";
    public static final String WERE = "WHERE ";
    public static final String FIND_PROJECT_BY_NAME = "SELECT * FROM %s WHERE projectName = '%s'";
    public static final String ADD_TABLE_BY_NAME = "INSERT INTO TestDB (%s) VALUES ( '%s')";
    public static final String DELETE_TABLE_BY_NAME = "";
}
