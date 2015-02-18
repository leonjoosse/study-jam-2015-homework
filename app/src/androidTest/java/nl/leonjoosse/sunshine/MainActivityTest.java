package nl.leonjoosse.sunshine;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import nl.leonjoosse.sunshine.connection.openweathermapbeans.DailyForecast;
import nl.leonjoosse.sunshine.connection.openweathermapbeans.Temperature;
import nl.leonjoosse.sunshine.connection.openweathermapbeans.Weather;

/**
 * Tests whether the ForecastFragment is shown and {@link ForecastFragment#updateUI(String[])}.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mainActivity = getActivity();
    }

    public void testForecastFragmentIsShown() {

        ForecastFragment fragment = (ForecastFragment) mainActivity.getSupportFragmentManager()
                .findFragmentByTag("weather-forecast-list");

        // Simulate that we fetched some forecast data we put in the list view
        final String[] testData = new String[]{"test-1", "test-2", "test-3"};

        fragment.updateUI(testData);

        getInstrumentation().waitForIdleSync();

        View view = fragment.getView();
        assertNotNull(view);

        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        assertNotNull(listView);
        assertEquals(testData.length, listView.getCount());

        fragment.updateUI(new String[0]);
        getInstrumentation().waitForIdleSync();
        assertEquals(0, listView.getCount());

        fragment.updateUI(null);
        getInstrumentation().waitForIdleSync();
        assertEquals(0, listView.getCount());
    }

    public void testFormatForecast() {

        FetchWeatherTask task = new FetchWeatherTask(getInstrumentation().getContext(), "null");

        final long dateStamp = new GregorianCalendar(2015, 2, 11).getTimeInMillis();

        Weather weather = new Weather(900D, "Cloudy", "bewolkt", "icon");
        Temperature temp = new Temperature(1D, 2D, 3D, 4D, 5D, 6D);
        String expectedOutput = FetchWeatherTask.dateFormatter.format(new Date(dateStamp)) + " - bewolkt - 3/2";

        DailyForecast df = new DailyForecast(dateStamp / 1000L, // DailyForecast expects seconds
                0.0D, 0.0D, 0.0D, 0.0D, 0.0D, temp, Arrays.asList(weather));

        assertEquals(expectedOutput, task.formatForecast(df));
    }
}
