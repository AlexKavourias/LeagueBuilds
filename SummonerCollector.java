import constant.Region;
import database.models.Summoner;
import dto.League.LeagueEntry;
import main.java.riotapi.RiotApiException;
import java.util.HashMap;


public class SummonerCollector extends AbstractCollector {

    public SummonerCollector(String apiKey) {
        super(apiKey);
    }

    public void collectChallengerLadder() throws RiotApiException {
        for (Region r : Region.values()) {
            api.setRegion(r);
            for (LeagueEntry entry : api.getChallengerLeagues(r).getEntries()) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", Integer.parseInt(entry.getPlayerOrTeamId()));
                map.put("name", entry.getPlayerOrTeamName());
                map.put("points", entry.getLeaguePoints());
                map.put("division", entry.getDivision());
                new Summoner().insertObject(new Summoner(map));
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to update info for summoners, filtering out ones that already exist
     * TODO
     */
    public void updateChallengeLadderInfo() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
