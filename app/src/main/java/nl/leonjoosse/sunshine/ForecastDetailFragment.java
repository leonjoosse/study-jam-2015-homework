package nl.leonjoosse.sunshine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment that displays the weather forecast of a specific day.
 */
public class ForecastDetailFragment extends Fragment {

    public static final String ARG_TEXT = "text";

    private TextView textDetail;

    private String text = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(ARG_TEXT)) {
            text = arguments.getString(ARG_TEXT);
        }

        if (text == null) {
            text = "No weather today!";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textDetail = (TextView) view.findViewById(R.id.detail_text);

        textDetail.setText(text);
    }
}
