package nl.leonjoosse.sunshine;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import static nl.leonjoosse.sunshine.MainActivity.WeatherForecastListFragment;

/**
 * Created by Leon on 4-2-2015.
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

    public void testListItemCount() {

        WeatherForecastListFragment fragment =
                (WeatherForecastListFragment) mainActivity.getSupportFragmentManager()
                        .findFragmentByTag("weather-forecast-list");

        View view = fragment.getView();

        assertNotNull(view);

        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);

        assertEquals(7, listView.getAdapter().getCount());
    }
}
