package database.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Summoner extends AbstractModel {
    protected static final String tableName = "Summoners";
    private final List<String> COLUMNS = Arrays.asList("id", "region", "name", "points", "division");

    public Summoner() { super(); }

    public Summoner(Map<String, Object> map) {
        super(map);
    }

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
        if (!this.verifyMap(toInsert.toMap(), true)) {
            return false;
        }
        try {
            String query = String.format("INSERT INTO %s (id, region, name, points, division) VALUES(?, ?, ?, ?, ?)", tableName);
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, (Integer) toInsert.getValue("id"));
            st.setString(2, (String) toInsert.getValue("region"));
            st.setString(3, (String) toInsert.getValue("name"));
            st.setInt(4, (Integer) toInsert.getValue("points"));
            st.setString(5, (String) toInsert.getValue("division"));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Set<Queryable> resultSetToAbstractModelSet(ResultSet result) {
        Set<Queryable> results = new HashSet<>();
        try {
            while (result.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", result.getInt("id"));
                map.put("region", result.getString("region"));
                map.put("name", result.getInt("points"));
                map.put("points", result.getInt("points"));
                map.put("division", result.getString("division"));
                results.add(new Summoner(map));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
