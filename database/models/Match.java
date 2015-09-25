package database.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Match extends AbstractModel {
    public static final String tableName = "Matches";
    public static List<String> COLUMNS = Arrays.asList("id", "datePlayed", "length");

    public Match() { super(); }

    public Match(int id, int length, long datePlayed) {
        this.columnsToValues.put("id", id);
        this.columnsToValues.put("length", length);
        this.columnsToValues.put("datePlayed", datePlayed);
    }

    public Match(Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    public List<String> getColumnNames() {
        return COLUMNS;
    }


    @Override
    protected Set<Queryable> resultSetToAbstractModelSet(ResultSet result) {
        return null;
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
    public boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException {
        //if (!this.verifyMap(toInsert.toMap(), true)) {
        //    return false;
        //}
        try {
            String query = String.format("INSERT INTO %s (id, datePlayed, winningTeam, length) VALUES(?, ?, ?, ?)", tableName);
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, (Integer) toInsert.getValue("id"));
            st.setDate(2, (Date) toInsert.getValue("date"));
            st.setString(3, (String) toInsert.getValue("winningTeam"));
            st.setInt(4, (Integer) toInsert.getValue("length"));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
