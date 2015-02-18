package nl.leonjoosse.sunshine.connection.openweathermapbeans;

import java.util.List;

/**
 * Represents a forecast for a specific day from the OpenWeatherMap API. The date is included in the
 * {@link #dt} (in seconds, not milliseconds).
 */
public class DailyForecast {

    public Long dt; // is in seconds
    public Double pressure;
    public Double humidity;
    public Double speed;
    public Double deg;
    public Double clouds;
    public Temperature temp;
    public List<Weather> weather;

    public DailyForecast() {
    }

    public DailyForecast(Long dt, Double pressure, Double humidity, Double speed, Double deg, Double clouds, Temperature temp, List<Weather> weather) {
        this.dt = dt;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.temp = temp;
        this.weather = weather;
    }
}
