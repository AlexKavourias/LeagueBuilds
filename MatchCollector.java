import constant.Region;
import database.models.Summoner;
import main.java.riotapi.Request;
import main.java.riotapi.RiotApiException;
import java.util.*;


public class MatchCollector extends AbstractCollector {
    public MatchCollector(String apiKey) {
        super(apiKey);
        this.apiKey = apiKey;
    }

    public void collectMasteries() throws RiotApiException {
        for (Region region: Region.values()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Region", region.getName());
            List<Integer> summonerIds = new ArrayList<>();
            //Put summoner ids for all summoners in the region into a comma separated string for api call
            List<Integer> ids = new ArrayList<>();
            new Summoner().findObject(new Summoner(map)).forEach(s -> ids.add((Integer) s.getValue("id")));
            while (!ids.isEmpty()) {
                StringBuilder idString = new StringBuilder();
                int count = 0;
                while (count++ < maxIDs ) {
                    if (ids.isEmpty())
                        break;
                    idString.append(ids.remove(0));
                    idString.append(",");
                }
                String url = String.format(baseURL,
                        region.getName(), "summoner", idString, "masteries", apiKey);
                System.out.println(url);
                System.out.println(Request.execute(String.format(baseURL,
                        region.getName(), "summoner", idString, "masteries", apiKey)));
            }
        }
    }
}
