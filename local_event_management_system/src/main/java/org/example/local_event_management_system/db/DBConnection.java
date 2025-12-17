package org.example.local_event_management_system.db;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/event_db", //
                "root", //
                "123456" //
        );
    }
}
