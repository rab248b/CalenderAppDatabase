package com.tamu.cs.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MySQLConnUtils {
 
public static Connection getMySQLConnection()
        throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
    // Note: Change the connection parameters accordingly.
//    String hostName = "database2.cs.tamu.edu";
//    String dbName = "rahul.bhagat";
//    String userName = "rahul.bhagat";
//    String password = "Rahubhag248";
	String hostName = "database2.cs.tamu.edu";
    String dbName = "jigna";
    String userName = "jigna";
    String password = "Jig@2468";
    return getMySQLConnection(hostName, dbName, userName, password);
}
 
public static Connection getMySQLConnection(String hostName, String dbName,
        String userName, String password) throws SQLException,
        ClassNotFoundException {
    
    // Declare the class Driver for MySQL DB
    // This is necessary with Java 5 (or older)
    // Java6 (or newer) automatically find the appropriate driver.
    // If you use Java> 5, then this line is not needed.
//    Class.forName("com.mysql.jdbc.Driver");
 
 
    // URL Connection for MySQL
    // Example: jdbc:mysql://localhost:3306/simplehr
    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
 
    Connection conn = DriverManager.getConnection(connectionURL, userName,
            password);
    return conn;
}
}
