package nl.leonjoosse.sunshine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A fragment with a list that shows the weather forecast for today and the next 6 days.
 */
public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> adapter;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler(Looper.getMainLooper());

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateData();
    }

    private void updateData() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                new FetchWeatherTask(getActivity()) {

                    @Override
                    protected void onPostExecute(String[] strings) {
                        super.onPostExecute(strings);

                        updateUI(strings);
                    }
                }.execute();
            }
        });
    }

    public void updateUI(final String[] data) {
        handler.post(new Runnable() {
            public void run() {
                if (adapter != null && isResumed()) {
                    if (data != null) {
                        adapter.setNotifyOnChange(false);
                        adapter.clear();
                        adapter.addAll(data);
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter.clear();
                    }
                }
            }
        });
    }
}

