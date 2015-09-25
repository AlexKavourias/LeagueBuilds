package database.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class PlayerBuild extends AbstractModel {
    private final String tableName = "Builds";
    private final List<String> COLUMNS = Arrays.asList(
            "summonerId", "matchId", "kills", "deaths", "assists",
            "team", "item0", "item1", "item2", "item3",
            "item4", "item5", "item6", "spell1", "spell2"
            );

    public PlayerBuild() { super(); }

    public PlayerBuild(Map<String, Object> parameters) {
        super(parameters);

    }

    public PlayerBuild(int summonerId, int matchId, int kills, int deaths, int assists, String team,
                       List<Integer> items, int spell1, int spell2) {
        super();
        this.columnsToValues.put("summonerId", summonerId);
        this.columnsToValues.put("matchId", matchId);
        this.columnsToValues.put("kills", kills);
        this.columnsToValues.put("deaths", deaths);
        this.columnsToValues.put("assists", assists);
        this.columnsToValues.put("team", team);
        this.columnsToValues.put("items", items);
        this.columnsToValues.put("spell1", spell1);
        this.columnsToValues.put("spell2", spell2);
    }

    @Override
    public List<String> getColumnNames() {
        return this.COLUMNS;
    }

    @Override
    protected Set<Queryable> resultSetToAbstractModelSet(ResultSet result) {
        Set<Queryable> results = new HashSet<>();
        try {
            while (result.next()) {
                //temporary solution, not a fan of this. TODO
                results.add(new PlayerBuild(result.getInt("summonerId"),
                        result.getInt("matchId"),
                        result.getInt("kills"),
                        result.getInt("deaths"),
                        result.getInt("assists"),
                        result.getString("team"),
                        new ArrayList<Integer>(Arrays.asList(
                                result.getInt("item0"), result.getInt("item1"), result.getInt("item2"),
                                result.getInt("item3"), result.getInt("item4"), result.getInt("item5"),
                                result.getInt("item6"))),
                        result.getInt("spell1"),
                        result.getInt("spell2")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    @Override
    public boolean insertObject(AbstractModel toInsert) throws IllegalArgumentException {

//        if (!this.verifyMap(toInsert.toMap(), true))
//            throw new IllegalArgumentException("Provided Map contains invalid column names");
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO Builds (summonerId, matchId, kills, deaths," +
                    "assists, team, item0, item1, item2, item3, item4, item5, item6, spell1, spell2) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ?, ?, ?, ?)");
            List items = (List) toInsert.toMap().get("items");
            st.setInt(1, (Integer) toInsert.getValue("summonerId"));
            st.setInt(2, (Integer) toInsert.getValue("matchId"));
            st.setInt(3, (Integer) toInsert.getValue("kills"));
            st.setInt(4, (Integer) toInsert.getValue("deaths"));
            st.setInt(5, (Integer) toInsert.getValue("assists"));
            st.setObject(6, (String) toInsert.getValue("team"));
            st.setInt(7, (Integer) items.get(0));
            st.setInt(8, (Integer) items.get(1));
            st.setInt(9, (Integer) items.get(2));
            st.setInt(10, (Integer) items.get(3));
            st.setInt(11, (Integer) items.get(4));
            st.setInt(12, (Integer) items.get(5));
            st.setInt(13, (Integer) items.get(6));
            st.setInt(14, (Integer) toInsert.getValue("spell1"));
            st.setInt(15, (Integer) toInsert.getValue("spell2"));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    public boolean equals(Object o) {
        if (!(o instanceof PlayerBuild)) return false;
        if (this == o) return true;

        PlayerBuild that = (PlayerBuild) o;
        return this.toMap().equals(that.toMap());
    }

    @Override
    public int hashCode() {
        int result = tableName.hashCode();
        result = 31 * result + this.toMap().hashCode();
        return result;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

}
