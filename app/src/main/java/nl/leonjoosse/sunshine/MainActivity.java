package nl.leonjoosse.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Main activity and entry point of this application. Displays a list of forecasts for the coming 7
 * days. Clicking on a forecast opens a {@link DetailActivity}, where more details of the forecast
 * are displayed.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment(), "weather-forecast-list")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.action_show_location_on_map:
                Intent geoIntent = new Intent(Intent.ACTION_VIEW);
                String location = Utils.getLocationSettingOrDefault(this);
                geoIntent.setData(Uri.parse("geo:0,0?q=" + Uri.encode(location)));

                if (geoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(geoIntent);
                } else {
                    Toast.makeText(this, getString(R.string.map_app_not_found), Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
