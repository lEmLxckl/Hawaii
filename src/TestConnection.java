import java.sql.*;
import java.util.ArrayList;

public class TestConnection {
    private String database = "jdbc:mysql://localhost:3306/carrental";
    private String username = "Emre";
    private String password = "Emre";
    private Connection connection = null;

    public TestConnection() {
        createConnection();
    }

    Connection createConnection() {
        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
            System.out.println("Conenction was made");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }
}
