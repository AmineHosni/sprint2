package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {

    private static String databaseURL = "jdbc:mysql://localhost:3306/pidevbugfree";
    private static String user = "root";
    private static String password = "";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static Connection c;

    private MyConnection() {
    }

    public static Connection getInstance() {
        if (c != null) {
            return c;
        }
        try {
            Class.forName(driverName);
            c = DriverManager.getConnection(databaseURL, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
