package ru.job4j.sql.jdbc.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    public TableEditor() throws SQLException, ClassNotFoundException {
        unitConnection();
    }

    private void unitConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            settings.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(
                settings.getValue("url"),
                settings.getValue("login"),
                settings.getValue("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("CREATE TABLE %s();", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DROP TABLE %s;", tableName);
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s",
                    tableName, columnName, type);
            statement.execute(sql);
        }
    }

    public void deleteColumn(String tableName,  String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s",
                    tableName, columnName);
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(
                null, null, tableName, null)
        ) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
