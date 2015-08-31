import constant.Region;
import database.models.Queryable;
import dto.Static.Champion;
import main.java.riotapi.RiotApiException;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Alex on 8/28/2015.
 */
public class ChampionCollector extends AbstractCollector {

    private List<database.models.Champion> champions;

    public ChampionCollector(String apiKey) {
        super(apiKey);
        this.champions = new ArrayList<>();
    }

    public void collectChampionInfo(Region region) throws RiotApiException {
        Map<String, Champion> championMap = api.getDataChampionList().getData();
        for (String name : championMap.keySet()) {
            champions.add(new database.models.Champion(name, championMap.get(name).getId()));
        }
        this.insertIntoDatbase(champions);
    }

    public void insertIntoDatbase(List<database.models.Champion> champions) {
        champions.forEach(champ -> new database.models.Champion().insertObject(champ));;
    }

}
