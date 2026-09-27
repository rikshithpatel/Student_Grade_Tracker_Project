package code.alpha.studentgradetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url =
            "jdbc:postgresql://localhost:5432/studentgradetracker";

    private static final String username = "postgres";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}