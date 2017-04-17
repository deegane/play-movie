package dao;


import com.google.common.collect.Lists;
import model.LocalMovie;
import play.db.Database;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.List;

public class MovieDAOImpl extends JdbcRepository implements MovieDAO {

    @Inject
    public MovieDAOImpl(Database db) {
        super(db);
    }

    public LocalMovie getMovie(String title) {
        try {
            String sql = "select title, rating from movies where lower(title) = ?";

            List<String> fieldValues = Lists.newArrayList(title.toLowerCase());
            ResultSet rs = getResultSetWithParams(sql, fieldValues);

            if(rs.next()) {
                String rating = rs.getString("rating");
                return new LocalMovie(title, rating);
            }
            return new LocalMovie("", "");
        } catch(Exception e) {
            // do something
            return new LocalMovie("","");
        }
    }


    public String getRating(String title) {

        try {

            String sql = "select rating from movies where lower(title) = ?";
            List<String> fieldValues = Lists.newArrayList(title.toLowerCase());
            ResultSet rs = getResultSetWithParams(sql, fieldValues);

            if(rs.next()) {
                return rs.getString("rating");
            }
            return "";
        } catch (Exception e) {
            // do something
            return "";
        }
    }
}
