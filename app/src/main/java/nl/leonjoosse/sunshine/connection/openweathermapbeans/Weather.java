package nl.leonjoosse.sunshine.connection.openweathermapbeans;

/**
 * A description of the weather of a {@link DailyForecast}.
 */
public class Weather {
    public Double id;
    public String main;
    public String description;
    public String icon;

    public Weather() {
    }

    public Weather(Double id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }
}
