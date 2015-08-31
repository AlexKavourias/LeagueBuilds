import constant.Region;
import database.models.Queryable;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Alex on 8/28/2015.
 */
abstract class AbstractCollector {

    protected RiotApi api;
    protected static final Region[] regions = Region.values();
    protected static final int SEASON = 5;

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
