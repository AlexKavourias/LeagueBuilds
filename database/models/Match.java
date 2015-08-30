package database.models;

import java.sql.Date;
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
public class Match extends AbstractModel {
    public static final String tableName = "Matches";
    public final List<String> COLUMNS = Arrays.asList("id", "datePlayed", "region", "winningTeAM", "length");
    private int matchId;
    private int lengthSeconds;
    private Date datePlayed;
    private String winner;

    @Override
    protected Map<String, Object> toMap() {
        return null;
    }

    @Override
    public List<String> getColumnNames() {
        return this.COLUMNS;
    }


    @Override
    protected Set<AbstractModel> resultSetToAbstractModelSet(ResultSet result) {
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
        if (!this.verifyMap(toInsert.toMap(), true)) {
            return false;
        }
        try {
            String query = String.format("INSERT INTO %s (id, datePlayed, winningTeam, length) VALUES(?, ?, ?, ?)", tableName);
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, (Integer) toInsert.getValue("id"));
            st.setDate(2, (Date) toInsert.getValue("date"));
            st.setString(3, (String) toInsert.getValue("winningTeam"));
            st.setInt(4, (Integer) toInsert.getValue("length"));
            return st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
