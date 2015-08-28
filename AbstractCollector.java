import constant.Region;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractCollector {

    protected static RiotApi api;
    protected static final Region[] regions = Region.values();

    public AbstractCollector(String apiKey) {
        if (api == null)
            api = new RiotApi(apiKey);
    }

    protected void setRegion(Region region) {
        api.setRegion(region);
    }

    protected RiotApi getApi() {
        return this.api;
    }

    protected void queueApiCall() {
        //TODO
    }
}
