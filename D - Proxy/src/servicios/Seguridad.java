package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seguridad {

    public boolean Autorizacion(String user, String password) {
// 1. Get your connection details from the Supabase dashboard
        String host = "aws-1-us-east-1.pooler.supabase.com";
        String database = "postgres";
        String port = "6543"; // Changed to Session Pooler port
        String userDB = "postgres.pnxqspwlzpyticceqjkj"; // Add project reference
        String passwordDB = "super-password-4";

// Create the JDBC connection string with SSL
        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database + "?sslmode=require";

        Connection connection = null;

        try {
            // 3. Establish the connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(jdbcUrl, userDB, passwordDB);
            System.out.println("Connection successful!");


            String sqlQuery = "SELECT * FROM \"Seguridad\" WHERE \"user\" = ? AND password = ?";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, user);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            // System.out.println(rs.);

            if (rs.next()) {
                System.out.println("Usuario " + user + " autorizado");
                rs.close();
                statement.close();
                return true;
                
            } else {
                System.out.println("Invalid username or password");
                rs.close();
                statement.close();
                return false;
            }

            

        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } finally {
            try {
                // 7. Always close the connection
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }
}
