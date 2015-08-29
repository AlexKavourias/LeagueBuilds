package database.models;

import database.DatabaseSetup;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 8/28/2015.
 */
public class PlayerBuild extends AbstractModel {
    private final String tableName = "Builds";
    private final List<String> COLUMNS = Arrays.asList(
            "summonerId", "matchId", "kills", "deaths", "assists", "team", "item1", "item2", "item3",
            "item4", "item5", "item6", "trinket", "spell1", "spell2"
            );
    private int summonerId;
    private int matchId;
    private int kills;
    private int deaths;
    private int assists;
    private String team;
    private Set<String> items;
    private String trinket;
    private String spell1;
    private String spell2;

    public PlayerBuild() {}

    public PlayerBuild(int summonerId, int matchId, int kills, int deaths, int assists, String team,
                       Set<String> items, String trinket, String spell1, String spell2) {
        this.summonerId = summonerId;
        this.matchId = matchId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.team = team;
        this.items = items;
        this.trinket = trinket;
        this.spell1 = spell1;
        this.spell2 = spell2;
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
        try {
            Statement st = DatabaseSetup.getConnection().createStatement();
            st.execute("Insert into")
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerBuild)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBuild that = (PlayerBuild) o;

        if (summonerId != that.summonerId) return false;
        if (matchId != that.matchId) return false;
        if (kills != that.kills) return false;
        if (deaths != that.deaths) return false;
        if (assists != that.assists) return false;
        if (!tableName.equals(that.tableName)) return false;
        if (!team.equals(that.team)) return false;
        if (!items.equals(that.items)) return false;
        if (!trinket.equals(that.trinket)) return false;
        if (!spell1.equals(that.spell1)) return false;
        return spell2.equals(that.spell2);
    }


    @Override
    public int hashCode() {
        int result = tableName.hashCode();
        result = 31 * result + summonerId;
        result = 31 * result + matchId;
        result = 31 * result + kills;
        result = 31 * result + deaths;
        result = 31 * result + assists;
        result = 31 * result + team.hashCode();
        result = 31 * result + items.hashCode();
        result = 31 * result + trinket.hashCode();
        result = 31 * result + spell1.hashCode();
        result = 31 * result + spell2.hashCode();
        return result;
    }

}
