package persistence.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static Connection connection = null;

    public static Connection getDbConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/book_management";
            String user = "root";
            String password = "truong247";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish the connection
                connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM book_management.book");
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                }

            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found: " + e.getMessage());
            } catch (SQLException e) {
                throw new SQLException("DbConnection error: " + e.getMessage());
            }
        }
        return connection;
    }
}
