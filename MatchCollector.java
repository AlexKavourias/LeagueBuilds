

import database.models.Match;
import database.models.Summoner;

import java.util.AbstractCollection;
import java.util.List;

/**
 * Created by Alex on 8/30/2015.
 */
public class MatchCollector extends AbstractCollector {

    public MatchCollector(String apiKey) {
        super(apiKey);
    }

    public List<Match> getSummonerMatches(Summoner summoner) {

        api.getMatchList((long) )
    }

}
