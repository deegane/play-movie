package dao;

import java.sql.SQLException;

public interface MovieDAO {

    String getRating(String title) throws SQLException;
}
