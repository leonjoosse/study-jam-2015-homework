package nl.leonjoosse.sunshine.connection.openweathermapbeans;

import java.util.List;

/**
 * A forecast for a certain amount of days.
 */
public class Forecast {

    public String cod;
    public String message;
    public List<DailyForecast> list;

    public Forecast() {
    }

    public Forecast(String cod, String message, List<DailyForecast> list) {
        this.cod = cod;
        this.message = message;
        this.list = list;
    }
}
