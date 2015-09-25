import database.models.Match;
import database.models.PlayerBuild;
import database.models.Summoner;

import database.models.Queryable;
import dto.Game.Game;
import dto.Game.RawStats;
import dto.Game.RecentGames;
import main.java.riotapi.RiotApiException;

import java.util.*;


public class MatchAndPlayerBuildCollector extends AbstractCollector {

    public MatchAndPlayerBuildCollector(String apiKey) {
        super(apiKey);
    }

    public void collectSummonerMatches() throws RiotApiException {
        Set<Queryable> summoners = new Summoner().findObject(new Summoner());
        System.out.println("Number of summoners: " + summoners.size());
        for (Queryable queryable : summoners) {
            Summoner summoner = (Summoner) queryable;
            RecentGames games = null;
            try {
                games = api.getRecentGames((Integer) summoner.getValue("id"));
                games.getGames().forEach(game -> {
                    new Match().insertObject(createMatchFromGame(game));
                    new PlayerBuild().insertObject(createPlayerBuildFromGame(game));
                });
            } catch (RiotApiException e) {
                e.printStackTrace();
                if (e.getErrorCode() == 429)
                    try {
                        System.out.println("Sleep for ten seconds..");
                        Thread.sleep(10000);
                        games = api.getRecentGames((Integer) summoner.getValue("id"));
                        games.getGames().forEach(game -> {
                            new Match().insertObject(createMatchFromGame(game));
                            new PlayerBuild().insertObject(createPlayerBuildFromGame(game));
                        });
                    } catch (InterruptedException | RiotApiException e1) {
                        e1.printStackTrace();
                    }
            }
        }
    }

    private Match createMatchFromGame(Game game) {
        game.getGameId();
        game.getCreateDate();
        return new Match((int) game.getGameId(),
                game.getStats().getTimePlayed(),
                game.getCreateDate());
    }

    private PlayerBuild createPlayerBuildFromGame(Game game) {
        RawStats stats = game.getStats();
        List<Integer> items = getItems(stats);
        System.out.println("in create player build:\t" + items);
        PlayerBuild pb = new PlayerBuild(game.getChampionId(),
                (int) game.getGameId(),
                stats.getChampionsKilled(),
                stats.getNumDeaths(),
                stats.getAssists(),
                stats.getTeam() == 100 ? "blue" : "purple",
                items,
                game.getSpell1(),
                game.getSpell2());
        System.out.println(pb.getValue("items") + "\t" + pb);
        return pb;
    }

    private List<Integer> getItems(RawStats stats) {
        System.out.println("stats: " + stats.getItem0());
        ArrayList<Integer> items = new ArrayList<Integer>(7);
        items.add(stats.getItem0());
        items.add(stats.getItem1());
        items.add(stats.getItem2());
        items.add(stats.getItem3());
        items.add(stats.getItem4());
        items.add(stats.getItem5());
        items.add(stats.getItem6());
        return items;
    }

}
