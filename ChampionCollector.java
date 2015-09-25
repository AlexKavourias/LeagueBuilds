import dto.Static.Champion;
import main.java.riotapi.RiotApiException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ChampionCollector extends AbstractCollector {

    private List<database.models.Champion> champions;
    private URL staticChampionURL;
    private String url = "http://ddragon.leagueoflegends.com/cdn/5.17.1/data/en_US/champion/Aatrox.json";

    public ChampionCollector(String apiKey) {
        super(apiKey);
        this.champions = new ArrayList<>();
    }

    public void collectChampionInfo() throws RiotApiException {
        Map<String, Champion> championMap = api.getDataChampionList().getData();
        for (String name : championMap.keySet()) {
            //champions.add(new database.models.Champion(name, championMap.get(name).getId()));
            Champion champion = championMap.get(name);
            System.out.println(champion.getSpells().size());
        }
        this.insertIntoDatbase(champions);
    }

    public void insertIntoDatbase(List<database.models.Champion> champions) {
        champions.forEach(champ -> new database.models.Champion().insertObject(champ));;
    }

}
