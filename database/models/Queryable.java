package database.models;

import java.util.Map;

interface Queryable {

    /**
     * Find object matching a given set of parameters in the table belonging to the @class
     * @param params
     * @return True on success
     */
    boolean findObject(Map params);

    /**
     * Remove object matching a given set of parameters in the table belonging to the @class
     * @param params
     * @return True on success
     */
    boolean removeObject(Map params);

    /**
     * Change objects matching the given params to the provided object
     * @param params
     * @param newObject
     * @throws IllegalArgumentException when the provided object is not an instance of @class
     * @return True on success
     */
    boolean updateObject(Map params, AbstractModel newObject) throws IllegalArgumentException;

    /**
     *
     * @param toInsert
     * @return True on success
     * @throws IllegalArgumentException when {{toInsert}} is not instance of @class
     */
    boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException;

}