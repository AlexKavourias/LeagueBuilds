import dto.League.LeagueEntry;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;

/**
 * Created by Alex on 8/28/2015.
 */
public class RiotExample {

    private RiotApi api;

    public RiotExample() throws RiotApiException {
        this.api = new RiotApi("e0b03fac-0d19-49a5-b433-4fd504a7f854");
        int count = 0;
        for (LeagueEntry entry : api.getChallengerLeagues().getEntries()) {
            System.out.println(count++);
            System.out.println(entry.getDivision());
            System.out.println(entry.getLeaguePoints());
            System.out.println(entry.getLosses());
            System.out.println(entry.getMiniSeries());
            System.out.println(api.getSummonerById(entry.getPlayerOrTeamId()).getName());
            System.out.println(entry.getWins());
            System.out.println("=======================");
        }
    }

    public static void main(String[] args) throws RiotApiException {
        new RiotExample();
    }
}
