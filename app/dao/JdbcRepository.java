package dao;

import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcRepository {

    private Database db;
    private Connection connection;

    @Inject
    public JdbcRepository(Database db) {
        this.db = db;
    }

     ResultSet getResultSet(String sql) throws SQLException {
        return db.getConnection().prepareStatement(sql).executeQuery();
    }

     ResultSet getResultSetWithParams(String sql, List<String> fieldValues) throws SQLException {
        PreparedStatement preparedStatement =db.getConnection().prepareStatement(sql);

       for(int i=0;i<fieldValues.size();i++) {
           preparedStatement.setString(i+1,fieldValues.get(i));
       }
       return preparedStatement.executeQuery();
    }

     Connection getConnection() {
        return db.getConnection();
    }
}