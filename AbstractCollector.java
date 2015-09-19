import constant.Region;
import main.java.riotapi.RiotApi;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractCollector {
    protected String baseURL = "https://br.api.pvp.net/api/lol/%s/v1.4/%s/%s/%s?api_key=%s";
    protected RiotApi api;
    protected static final int maxIDs = 40;
    protected String apiKey;
    protected static final Region[] regions = Region.values();
    protected static final int SEASON = 5;

    public AbstractCollector(String apiKey) {
        api = new RiotApi(apiKey);
    }

    protected void setRegion(Region region) {
        api.setRegion(region);
    }

    protected RiotApi getApi() {
        return this.api;
    }

    protected void queueApiCall() {
    }



}