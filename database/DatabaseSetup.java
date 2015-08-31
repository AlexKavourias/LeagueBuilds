package database;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Alex on 8/28/2015.
 */
public class DatabaseSetup {
    private static Connection conn;
    private static String username = "postgres";
    private static String password = "secret";
    private static String url = "jdbc:postgresql://localhost:5432/LeagueBuilds";

    public DatabaseSetup() {
        setupConnection();
    }

    public static void setupConnection() {
        if (conn != null)
            return;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        setupConnection();
        return conn;
    }

    public static void createTables() throws SQLException {
        //Champion table
        setupConnection();
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS champions " +
                "(id int PRIMARY KEY," +
                " name varchar(15))");

        //Summoner Table   id (primary key) Region name division-points division ladder-rank
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS Summoners " +
                "(id int PRIMARY KEY, " +
                "region VARCHAR(6), " +
                "name varchar(30)," +
                "points int," +
                "division VARCHAR(2))");

        //Matches Table  id (primary key) winner (RED or BLUE) duration .. other info?
        //TODO
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS Matches " +
                "(id int PRIMARY KEY, " +
                "datePlayed date, " +
                "region VARCHAR(3)," +
                "winningTeam varchar(4), " +
                "length int)");

        //Build summoner-id(foreign key) match-id(foreign key) items1-6 spell-progression"qwwqwerrqwwe..."
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS Builds " +
                "(summonerId int REFERENCES champions(id)," +
                "matchId int REFERENCES matches(id), " +
                "kills int," +
                "deaths int," +
                "assists int," +
                "team varchar(6), " +
                "item0 int," +
                "item1 int," +
                "item2 int," +
                "item3 int," +
                "item4 int," +
                "item5 int," +
                "item6 int," +
                "spell1 int, " +
                "spell2 int)");
    }
}
