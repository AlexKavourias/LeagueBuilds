package database.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collections.*

/**
 * Created by Alex on 8/28/2015.
 */
public class Champion extends AbstractModel {
    private final List<String> COLUMNS = Arrays.asList("name", "id");
    private final String tableName = "Champions";
    private String name;
    private int id;

    public Champion() {
      //Default constructor, used for interacting with methods without specifying object params
    }

    public Champion(String name, int id) {
        this.name = name;
        this.id = id;
    }

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
