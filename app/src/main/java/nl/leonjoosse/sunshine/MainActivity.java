package nl.leonjoosse.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherForecastListFragment(), "weather-forecast-list")
                    .commit();
        }
    }

    /**
     * A fragment with a list that shows the weather forecast for today and the next 6 days.
     */
    public static class WeatherForecastListFragment extends Fragment {

        private static final List<String> FORECAST_FAKE_DATA = Arrays.asList(
                "Vandaag - Zonnig - 5",
                "Morgen - Zonnig - 5",
                "Vrijdag - Zonnig - 5",
                "Zaterdag - Zonnig - 5",
                "Zondag - Zonnig - 5",
                "Maandag - Zonnig - 5",
                "Dinsdag - Zonnig - 5"
        );

        private ArrayAdapter<String> adapter;
        private ListView listView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview, FORECAST_FAKE_DATA);

            listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(adapter);

            return rootView;
        }
    }
}
