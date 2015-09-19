package database.models;

import database.DatabaseLog;
import database.DatabaseSetup;

import java.sql.*;
import java.util.*;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractModel implements Queryable {
    protected static final Connection conn = DatabaseSetup.getConnection();
    protected Map<String, Object> columnsToValues;

    public AbstractModel() {
        //Default constructor, used for interacting with methods without specifying object params
        this.columnsToValues = new HashMap<>();
    }

    public AbstractModel(Map<String, Object> parameters) {
        for (String column : parameters.keySet()) {
            assert this.getColumnNames().contains(column);
        }
        this.columnsToValues = parameters;
    }

    @Override
    public Set<Queryable> findObject(AbstractModel model) {
        StringBuilder query = new StringBuilder(String.format("SELECT * FROM %s", this.getTableName()));
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
            DatabaseLog.log(query.toString(), this.getClass().toString());
            return this.resultSetToAbstractModelSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    /**
     * verifies that the provided Map does not contain a column that is not one of the table's column names
     * @param params the Map to verify
     * @param strict when true, will make sure that the given map has a value for everyone column
     * @return true if the Map is valid, false otherwise
     */
    protected boolean verifyMap(Map<String, Object> params, boolean strict) {
        if (strict) {
            return params.keySet().equals(new HashSet<>(this.getColumnNames()));
        }
        for (String key : params.keySet()) {
            if (!this.getColumnNames().contains(key))
                return false;
        }
        return true;
    }

    @Override
    public Object getValue(String columnName) {
        return this.toMap().get(columnName);
    }

    @Override
    public Map<String, Object> toMap() {
        return this.columnsToValues;
    }

    protected abstract Set<Queryable> resultSetToAbstractModelSet(ResultSet result);

}
