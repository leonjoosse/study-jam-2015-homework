package nl.leonjoosse.sunshine;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Some utility methods, specific for this app.
 */
public class Utils {

    public static String getLocationSettingOrDefault(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(
                        context.getString(R.string.pref_location_key),
                        context.getString(R.string.pref_location_default)
                );
    }
}
