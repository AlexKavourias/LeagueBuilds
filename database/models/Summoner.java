package database.models;

import constant.Region;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 8/28/2015.
 */
public class Summoner extends AbstractModel {
    private static final String tableName = "Summoners";
    private final List<String> COLUMNS = Arrays.asList("id", "region", "name", "points", "division");

    @Override
    public boolean removeObject(AbstractModel model) {
        return false;
    }

    @Override
    public List<String> getColumnNames() {
        return this.COLUMNS;
    }


    @Override
    public boolean updateObject(AbstractModel oldObject, AbstractModel newObject) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException {
        if (!this.verifyMap(toInsert.toMap())) {
            return false;
        }
        try {
            String query = String.format("INSERT INTO %s (id, name) VALUES(?, ?)", tableName);
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, (Integer) toInsert.getValue("summonerId"));
            st.setString(2, (String) toInsert.getValue("region"));
            st.setString(3, (String) toInsert.getValue("name"));
            st.setInt(4, (Integer) toInsert.getValue("points"));
            st.setString(5, (String) toInsert.getValue("division"));
            return st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Map<String, Object> toMap() {
        return null;
    }

    @Override
    protected Set<AbstractModel> resultSetToAbstractModelSet(ResultSet result) {
        return null;
    }
}
