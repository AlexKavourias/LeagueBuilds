import constant.Region;
import dto.Static.Champion;
import main.java.riotapi.RiotApiException;

import java.util.Map;

/**
 * Created by Alex on 8/28/2015.
 */
public class ChampionCollector extends AbstractCollector {

    private Map<String, Integer> championsToId;

    public ChampionCollector(String apiKey) {
        super(apiKey);
    }

    public void collectChampionInfo(Region region) throws RiotApiException {
        Map<String, Champion> championMap = api.getDataChampionList().getData();
        for (String name : championMap.keySet()) {
            championsToId.put(name, championMap.get(name).getId());
        }


    }

    public static void main(String[] args) throws RiotApiException {
        new ChampionCollector("e0b03fac-0d19-49a5-b433-4fd504a7f854").collectChampionInfo(Region.NA);
    }
}
