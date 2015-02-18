package nl.leonjoosse.sunshine;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.leonjoosse.sunshine.connection.OpenWeatherMapApiClient;
import nl.leonjoosse.sunshine.connection.OpenWeatherMapApiClientFactory;
import nl.leonjoosse.sunshine.model.DailyForecast;
import nl.leonjoosse.sunshine.model.Forecast;
import retrofit.RetrofitError;

/**
 * Created by Leon on 4-2-2015.
 */
public class FetchWeatherTask extends AsyncTask<Void, Void, String[]> {

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("E, MMM d");

    public static final String API_KEY = "6961c744f50322363458e66314ef5f4f";

    private String query;

    public FetchWeatherTask(Context context) {
        super();

        query = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(
                        context.getString(R.string.pref_location_key),
                        context.getString(R.string.pref_location_default)
                );
    }

    @Override
    protected String[] doInBackground(Void... params) {

        OpenWeatherMapApiClient client = OpenWeatherMapApiClientFactory.createService();

        Forecast forecast = null;
        try {
            forecast = client.getWeatherForecast(
                    query,
                    API_KEY,
                    7,
                    OpenWeatherMapApiClient.ResponseType.JSON,
                    OpenWeatherMapApiClient.ResponseLanguage.NL,
                    OpenWeatherMapApiClient.TemperatureUnit.METRIC
            );
        } catch (RetrofitError e) {
            e.printStackTrace();
            return null;
        }

        if (forecast == null || forecast.list == null) {
            return null;
        }

        String[] dailyForecasts = new String[forecast.list.size()];
        java.util.List<DailyForecast> list = forecast.list;
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            dailyForecasts[i] = formatForecast(list.get(i));
        }

        return dailyForecasts;
    }

    public String formatForecast(DailyForecast forecast) {

        if (forecast == null) {
            return "No forecast for today";
        }

        Date dateStamp = new Date((valueOrDefault(forecast.dt, 0L) * 1000L)); // forecast.dt is in seconds, make it milliseconds
        String description = forecast.weather != null && forecast.weather.get(0) != null ? valueOrDefault(forecast.weather.get(0).description, "-") : "-";
        double tempHigh = forecast.temp != null ? valueOrDefault(forecast.temp.max, -1D) : -1D;
        double tempLow = forecast.temp != null ? valueOrDefault(forecast.temp.min, -1D) : -1D;

        return dateFormatter.format(dateStamp)
                + " - "
                + description
                + " - "
                + Math.round(tempHigh) + "/" + Math.round(tempLow);
    }

    private <T> T valueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
