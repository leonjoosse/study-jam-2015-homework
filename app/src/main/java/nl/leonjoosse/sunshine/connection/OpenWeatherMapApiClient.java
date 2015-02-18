package nl.leonjoosse.sunshine.connection;

import nl.leonjoosse.sunshine.connection.openweathermapbeans.Forecast;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface that describes a subset of the API calls of the OpenWeatherMap (OWM) API. Applies to
 * version 2.5 of the OWM API. Where possible, query parameters are defined in this interface.
 */
public interface OpenWeatherMapApiClient {

    public static final String ENDPOINT = "http://api.openweathermap.org/data/2.5";

    /**
     * The code for the API response language. {@link #toString()} returns the lowercase version of
     * the code for use in the API url.
     */
    public enum ResponseLanguage {
        EN, NL
    }

    /**
     * The API response temperature unit
     */
    public enum TemperatureUnit {
        METRIC, IMPERIAL
    }

    /**
     * The response doc type for the API.
     */
    public enum ResponseType {
        XML, JSON
    }

    @GET("/forecast/daily")
    public Forecast getWeatherForecast(@Query("q") String query,
                                       @Query("APPID") String apiKey,
                                       @Query("cnt") int amountOfDays,
                                       @Query("mode") ResponseType responseType,
                                       @Query("lang") ResponseLanguage language,
                                       @Query("units") TemperatureUnit units);
}
