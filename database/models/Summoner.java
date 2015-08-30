package database.models;

import constant.Region;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 8/28/2015.
 */
public class Summoner extends AbstractModel {
    private static final String tableName = "Summoners";
    private final List<String> COLUMNS = Arrays.asList("summonerId", "region", "name", "points", "division");
    private Region region;
    private String division;
    private String name;
    private int summonerId;
    private int points;


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
        return false;
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
