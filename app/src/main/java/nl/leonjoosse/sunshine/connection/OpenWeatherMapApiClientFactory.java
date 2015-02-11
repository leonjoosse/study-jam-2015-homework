package nl.leonjoosse.sunshine.connection;

import retrofit.RestAdapter;

/**
 * Created by Leon on 4-2-2015.
 */
public final class OpenWeatherMapApiClientFactory {

    private static RestAdapter openWeatherMapApiAdapter;

    private OpenWeatherMapApiClientFactory() {
        // No need to instantiate
    }

    public static OpenWeatherMapApiClient createService() {

        if (openWeatherMapApiAdapter == null) {
            openWeatherMapApiAdapter = new RestAdapter.Builder()
                    .setEndpoint(OpenWeatherMapApiClient.ENDPOINT)
                    .build();
        }

        return openWeatherMapApiAdapter.create(OpenWeatherMapApiClient.class);
    }
}
