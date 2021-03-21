package sk.kosickaakademia.hingis.rat_house.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;

    static {

        Properties props = new Properties();

        InputStream inputStream = DatabaseConnection.class
                                .getClassLoader()
                                .getResourceAsStream("db.properties");

        try {

            props.load(inputStream);

        } catch (IOException e) {

            e.printStackTrace();
        }

        String url = props.getProperty("url");
        String name = props.getProperty("name");
        String password = props.getProperty("pwd");

        try {

            connection = DriverManager
                        .getConnection(url, name, password);

        }  catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    public static Connection connect() {

        return connection;
    }
}
