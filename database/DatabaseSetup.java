package database;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Alex on 8/28/2015.
 */
public class DatabaseSetup {
    private static Connection conn;
    private static String username = "database";
    private static String password = "secret";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";

    public void setupConnection() throws ClassNotFoundException, SQLException {
        if (conn != null)
            return;
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        conn = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        setupConnection();
        return conn;
    }

    private static void createTables() throws SQLException {
        //Champion table
        conn.createStatement().execute("CREATE TABLE champions " +
                "(id int UNIQUE NOT NULL," +
                " name varchar(15))");

        //Summoner Table   id (primary key) Region name division-points division ladder-rank
        conn.createStatement().execute("CREATE TABLE Summoners " +
                "(id int UNIQUE NOT NULL, " +
                "region VARCHAR(3), " +
                "name varchar(30)," +
                "points int," +
                "division int)");

        //Matches Table  id (primary key) winner (RED or BLUE) duration .. other info?
        //TODO
        conn.createStatement().execute("CREATE TABLE Matches " +
                "(id UNIQUE NOT NULL, " +
                "date_players date, " +
                "winner varchar(4), " +
                "length int)");

        //Build summoner-id(foreign key) match-id(foreign key) items1-6 spell-progression"qwwqwerrqwwe..."
        conn.createStatement().execute("CREATE TABLE Build " +
                "(summoner_id REFERENCES champions(id)," +
                "match_id REFERENCES matches(id), " +
                "kills int," +
                "deaths int," +
                "assists int," +
                "team varchar(4), " +
                "item1 varchar(30), " +
                "item2 varchar(30), " +
                "item3 varchar(30), " +
                "item4 varchar(30), " +
                "item5 varchar(30), " +
                "item6 varchar(30), " +
                "trinket varchar(30)," +
                "spell1 varchar(30), " +
                "spell2 varchar(30),");
    }
}
