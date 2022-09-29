package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DatabaseCreation {
    public static void main(LinkedList<String> statementList) {
        new DatabaseCreation(statementList);
    }

    public DatabaseCreation(LinkedList<String> statementList) {
        createBatchQuery(statementList);
    }



    private void createBatchQuery(LinkedList<String> statementList) throws RuntimeException {
            Statement statement;

            try {
                Connection dbConnection = createConnection();
                statement = dbConnection.createStatement();
                dbConnection.setAutoCommit(false);
                for (String line : statementList
                     ) {
                    statement.addBatch(line);
                }
                statement.executeBatch();
                dbConnection.commit();
                dbConnection.setAutoCommit(true);
                statement.close();
                dbConnection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
            private Connection createConnection() {
                String url = "jdbc:mariadb://localhost/";
                String userName = "user";
                String password = "user";
                Connection dbConnection = null;
                try {
                    dbConnection = DriverManager.getConnection(
                            url,
                            userName,
                            password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            return dbConnection;
    }
}
