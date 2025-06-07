package com.escapeRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/escaperoomdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pistacho";

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Conexión a la base de datos establecida exitosamente");
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener la conexión: " + e.getMessage());
            throw new RuntimeException("Error de conexión", e);
        }
        return connection;
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            logger.severe("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
