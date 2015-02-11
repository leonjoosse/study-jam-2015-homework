package nl.leonjoosse.sunshine.model;

/**
 * Represents the temperatures on various parts of a {@link DailyForecast}.
 */
public class Temperature {
    public Double day;
    public Double min;
    public Double max;
    public Double night;
    public Double eve;
    private Double morn;

    public Temperature() {
    }

    public Temperature(Double day, Double min, Double max, Double night, Double eve, Double morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }
}
