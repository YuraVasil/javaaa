package l5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesWithJDBC {
    public static void main(String[] args) {
        // З'єднання з базою даних
        String url = "jdbc:postgresql://localhost:5432/shop";
        String username = "postgres";
        String password = "1111";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("З'єднання з базою даних успішно!");

                // Видалення таблиць, якщо вони існують
                String[] dropTablesSQL = {
                        "DROP TABLE IF EXISTS Product",
                        "DROP TABLE IF EXISTS Category",
                        "DROP TABLE IF EXISTS Store"
                };

                for (String dropTableSQL : dropTablesSQL) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(dropTableSQL);
                        System.out.println("Таблиця видалена (якщо існує)!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                // Створення таблиць з використанням зовнішніх ключів
                String[] createTablesSQL = {
                        "CREATE TABLE Store (name VARCHAR(255) PRIMARY KEY)",
                        "CREATE TABLE Category (name VARCHAR(255) PRIMARY KEY, store_name VARCHAR(255), FOREIGN KEY (store_name) REFERENCES Store(name))",
                        "CREATE TABLE Product (" +
                                "name VARCHAR(255) PRIMARY KEY," +
                                "price DOUBLE PRECISION," +
                                "expirationDate DATE," +
                                "quantity INTEGER," +
                                "category_name VARCHAR(255)," +
                                "FOREIGN KEY (category_name) REFERENCES Category(name)" +
                                ")"
                };

                for (String createTableSQL : createTablesSQL) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(createTableSQL);
                        System.out.println("Таблиця створена успішно!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
