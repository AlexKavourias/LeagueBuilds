package database.models;

import database.DatabaseSetup;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractModel implements Queryable {
    protected static final Connection conn = DatabaseSetup.getConnection();
    protected static String tableName;

    public AbstractModel() {
        //Default constructor, used for interacting with methods without specifying object params
    }

    public AbstractModel(Map<String, Object> parameters) {
        for (String column : this.getColumnNames()) {
            assert this.getColumnNames().contains(column);
        }
    }

    @Override
    public Set<AbstractModel> findObject(AbstractModel model) {
        StringBuilder query = new StringBuilder(String.format("SELECT * FROM %s", tableName));
        boolean firstParam = true;
        for (String key : model.toMap().keySet()) {
            assert this.getColumnNames().contains(key);
            if (firstParam) {
                firstParam = false;
            } else {
                query.append(String.format("AND %s = %s", key, this.toMap().get(key)));
            }
        }
        try {
            ResultSet result = conn.createStatement().executeQuery(query.toString());
            return this.resultSetToAbstractModelSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    /**
     * verifies that the provided Map does not contain a column that is not one of the table's column names
     * @param params the Map to verify
     * @return true if the Map is valid, false otherwise
     */
    protected boolean verifyMap(Map<String, Object> params) {
        for (String key : params.keySet()) {
            if (!this.getColumnNames().contains(key))
                return false;
        }
        return true;
    }

    public Object getValue(String columnName) {
        if (this.getColumnNames().contains(columnName))
            return this.toMap().get(columnName);
        return null;
    }

    protected abstract Map<String, Object> toMap();

    protected abstract Set<AbstractModel> resultSetToAbstractModelSet(ResultSet result);

}
