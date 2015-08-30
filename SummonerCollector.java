import constant.Region;
import database.models.Summoner;
import dto.League.League;
import dto.League.LeagueEntry;
import main.java.riotapi.RiotApiException;

/**
 * Created by Alex on 8/28/2015.
 */
public class SummonerCollector extends AbstractCollector {

    public SummonerCollector(String apiKey) {
        super(apiKey);
    }

    public void collectChallengerLadder() throws RiotApiException {
        League league = this.api.getChallengerLeagues();
        for (Region r : Region.values())
            for (LeagueEntry entry : league.getEntries()) {
                    new Summoner().insertObject(new Summoner(
                            Integer.parseInt(entry.getPlayerOrTeamId()),
                            api.getRegion(),
                            entry.getPlayerOrTeamName(),
                            entry.getLeaguePoints(),
                            entry.getDivision()));
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
