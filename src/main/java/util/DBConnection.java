package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static  final  String URL = System.getenv("DBURL");
    private static  final  String USERNAME = System.getenv("ROOT");
    private static  final  String PASSWORD = System.getenv("Bhavya@2");

    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}