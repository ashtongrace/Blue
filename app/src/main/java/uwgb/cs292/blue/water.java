package uwgb.cs292.blue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class water extends Fragment {
    private int today = 0;

    public water() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        today = preferences.getInt(
                "water-" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day),
                0);

        final EditText s = (EditText) view.findViewById(R.id.water_num_of_cups);
        if (today > 0) {
            s.setText(String.valueOf(today));
        }

        final Button b = (Button) view.findViewById(R.id.water_enter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("water-onclick", "clicked");
                final EditText s = (EditText) view.findViewById(R.id.water_num_of_cups);
                today = Integer.parseInt(s.getText().toString());
            }
        });
    }

    @Override
    public void onStop () {
        super.onStop();

        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if ( today > 0 ) {
            editor.putInt(
                    "water-" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day),
                    today
            );
        }
        editor.apply();

        Log.e("on-stop", "today - " + String.valueOf(today));
    }
}