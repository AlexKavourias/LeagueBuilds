package database.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Alex on 8/28/2015.
 */
public class Champion extends AbstractModel {
    private final List<String> COLUMNS = Arrays.asList("name", "id");
    private static final String tableName = "Champions";
    private final String insertQuerySnub = "INSERT INTO Champions (id, name) VALUES(";
    private String name;
    private int id;
    private Map<String, Object> columnToValue = new HashMap<>();

    public Champion() {}

    public Champion(String name, int id) {
        this.columnToValue.put("name", name);
        this.columnToValue.put("id", id);
    }

    public Champion(Map<String, Object> params) {
        super(params);
        this.columnToValue = params;
    }

    @Override
    public boolean removeObject(AbstractModel model) {
        return false;
    }

    @Override
    public boolean updateObject(AbstractModel oldObject, AbstractModel newObject) throws IllegalArgumentException {
        return false;
    }

    @Override
    public List<String> getColumnNames() {
        return this.COLUMNS;
    }


    @Override
    public boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException {
        if (!this.verifyMap(toInsert.toMap(), true)) {
            return false;
        }
        try {
            String query = String.format("INSERT INTO %s (id, name) VALUES(?, ?)", tableName);
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, (Integer) toInsert.getValue("id"));
            st.setString(2, (String) toInsert.getValue("name"));
            return st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Set<Queryable> resultSetToAbstractModelSet(ResultSet result) {
        HashSet<Queryable> results = new HashSet<>();
        try {
            while (result.next()) {
                //temporary solution, not a fan of this. TODO
                results.add(new Champion(result.getString("name"), result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    @Override
    public Map<String, Object> toMap() {
        return this.columnToValue;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
