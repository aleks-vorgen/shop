package com.example.demo.dao;

import com.example.demo.dao.category.DAOCategoryImpl;
import com.example.demo.dao.comment.DAOCommentImpl;
import com.example.demo.dao.order.DAOOrderImpl;
import com.example.demo.dao.product.DAOProductImpl;
import com.example.demo.dao.user.DAOUserImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresFactory {
    private static final Logger LOGGER = Logger.getLogger("DataConnections");
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

                if (!connection.isClosed()) {
                    LOGGER.info("Connected successfully");
                }
            } catch (ClassNotFoundException | SQLException e) {
                LOGGER.error("Could not connect to database.\n Message: " + e.getLocalizedMessage());
            }
        }

        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                LOGGER.info("Disconnected successfully");
            }
        } catch (SQLException e) {
            LOGGER.error("Could not disconnect from database.\n Message: " + e.getLocalizedMessage());
        }
    }

    public DAOAccess getDAO(DAOTypes type) {
        switch (type) {
            case CATEGORY: return new DAOCategoryImpl();
            case COMMENT: return new DAOCommentImpl();
            case ORDER: return new DAOOrderImpl();
            case PRODUCT: return new DAOProductImpl();
            case USER: return new DAOUserImpl();
            default: return null;
        }
    }
}