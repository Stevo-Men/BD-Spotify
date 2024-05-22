package com.example.demobd3fx.database;

import com.example.demobd3fx.dao.ResultSetHandler;
import com.example.demobd3fx.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private final String connectionUrl;
    private final String username;
    private final String password;

    private Connection connection = null;

    public Database(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    public boolean openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(connectionUrl, username, password);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Invalid connection!");
            return false;
        }
    }

    public void closeConnection() {
        IOUtils.closeQuietly(connection);
    }

    public static Database buildFromConfig() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Database.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }
        return new Database(prop.getProperty("db.connection.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
    }

    public ResultSet executeQuery(String query, Object... params) {
        PreparedStatement statement = null;
        try {
            openConnection();
            statement = connection.prepareStatement(query);
            fillStatementWithParams(statement, params);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(statement);
        }
        return null;
    }

    public <T> T executeQuery(String query, ResultSetHandler<T> rsh, Object... params) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            openConnection();
            statement = connection.prepareStatement(query);
            fillStatementWithParams(statement, params);
            rs = statement.executeQuery();
            return rsh.handle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(rs);
            IOUtils.closeQuietly(statement);
        }
        return null;
    }

    public <T> T selectInstance(String query, ResultSetHandler<T> rsh, Object... params) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            openConnection();
            statement = connection.prepareStatement(query);
            fillStatementWithParams(statement, params);
            rs = statement.executeQuery();
            T result = null;
            if (rs.next()) {
                result = rsh.handle(rs);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(rs);
            IOUtils.closeQuietly(statement);
            closeConnection();
        }
        return null;
    }

    public <T> List<T> selectInstances(String query, ResultSetHandler<T> rsh, Object... params) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            openConnection();
            //connection.setAutoCommit(false);
            //connection.commit();
            //connection.rollback();
            statement = connection.prepareStatement(query);
            fillStatementWithParams(statement, params);
            rs = statement.executeQuery();
            List<T> results = new ArrayList<>();
            while (rs.next()) {
                T result = rsh.handle(rs);
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(rs);
            IOUtils.closeQuietly(statement);
            closeConnection();
        }
        return null;
    }

    public int executeUpdate(String query, Object... params) {
        PreparedStatement statement = null;
        try {
            openConnection();
            statement = connection.prepareStatement(query);
            fillStatementWithParams(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(statement);
        }
        return 0;
    }

    private void fillStatementWithParams(PreparedStatement statement, Object[] params) throws SQLException {
        if (params == null) {
            return;
        }
        for (int i = 0; i < params.length; ++i) {
            statement.setObject(i+1, params[i]);
        }
    }

}
