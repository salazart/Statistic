package com.sz.db.services;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnService {
	private static String user = "sa";
    private static String password = "250525";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://10.39.10.47;databaseName=WorkDB";
    
    protected static final Logger log = LogManager.getRootLogger();

    protected Connection getConnection() throws Exception{
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, user, password);
	}
}
