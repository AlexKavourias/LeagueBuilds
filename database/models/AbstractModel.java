package database.models;

import database.DatabaseSetup;

import java.lang.instrument.IllegalClassFormatException;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractModel implements Queryable {
    protected static final Connection conn = DatabaseSetup.getConnection();

}
