package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/"; //localhost -> 127.0.0.1
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final int TIMEOUT = 5;


    private Connection connection;


    public JDBConnectionWrapper(String schema) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASSWORD);
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection(){
        return connection;
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS book (\n" +
                "id bigint NOT NULL AUTO_INCREMENT,\n" +
                "author varchar(500) NOT NULL,\n" +
                "title varchar(500) NOT NULL,\n" +
                "publishedDate datetime DEFAULT NULL,\n" +
                "price int NOT NULL,\n" +
                "stock int NOT NULL,\n" +
                "PRIMARY KEY(id),\n" +
                "UNIQUE KEY id_UNIQUE(id)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;\n";

        statement.execute(sql);
    }

}
