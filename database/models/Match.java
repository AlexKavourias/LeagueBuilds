package database.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public boolean findObject(Map params) {
        return false;
    }

    @Override
    public boolean removeObject(Map params) {
        return false;
    }

    @Override
    public boolean updateObject(Map params, AbstractModel newObject) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException {
        return false;
    }
}
