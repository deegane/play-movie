package dao;


import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAOImpl implements MovieDAO {

    private Database db;
    private Connection connection;

    @Inject
    public MovieDAOImpl(Database db) {
        this.db = db;
        this.connection=db.getConnection();
    }

    public String getRating(String title) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement("select rating from movies where title = ?");

        preparedStatement.setString(1, title);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            return rs.getString("rating");
        }

        return "";
    }
}
