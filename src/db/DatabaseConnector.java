package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // Relative path to the SQLite database
    private static final String URL = "jdbc:sqlite:database/th43.db"; 

    /**
     * Connect to the SQLite database.
     * 
     * @return a Connection object to the database
     */
    public static Connection connect() throws SQLException {
        // SQLite connection string
        Connection conn = DriverManager.getConnection(URL);
        return conn;
    }
}
