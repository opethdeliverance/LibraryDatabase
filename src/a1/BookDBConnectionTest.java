package a1;

import java.sql.*;

public class BookDBConnectionTest {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3304/books";
    static final String USER = "root";
    static final String PASS = "OdinRaiden66";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from titles";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("ISBN \t\t\t Title \t\t\t\t\t\t\t\t\t Edition Number \t Copyright");
            while (resultSet.next()) {
                System.out.printf("\n%s \t\t %s  %d  %s ",
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("editionNumber"),
                        resultSet.getString("copyright"));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}


