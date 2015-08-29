package database.models;

import constant.Region;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
