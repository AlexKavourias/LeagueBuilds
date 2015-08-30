package database.models;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Alex on 8/28/2015.
 */
public class Match extends AbstractModel {
    public static final String tableName = "Matches";
    public final List<String> COLUMNS = Arrays.asList("matchId", "datePlayed", "winner", "lengthSeconds");
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
        return false;
    }
}
