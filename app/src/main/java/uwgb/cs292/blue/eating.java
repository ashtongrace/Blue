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
import android.widget.CheckBox;

import java.util.Calendar;

public class eating extends Fragment {
    private int breakfast = 0, lunch = 0, dinner = 0;

    public eating() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        breakfast = preferences.getInt("eating-breakfast", 0);
        lunch = preferences.getInt("eating-lunch", 0);
        dinner = preferences.getInt("eating-dinner", 0);

        Log.e("on-create", "brk - " + String.valueOf(breakfast) + " lnh - " + String.valueOf(lunch) + " din - " + String.valueOf(dinner));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Calendar now = Calendar.getInstance();
        int today = now.get(Calendar.DAY_OF_MONTH);
        Log.e("Date Math", "Today is " + today);

        final CheckBox b = (CheckBox) view.findViewById(R.id.breakfast_cb);
        final CheckBox l = (CheckBox) view.findViewById(R.id.lunch_cb);
        final CheckBox d = (CheckBox) view.findViewById(R.id.dinner_cb);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int today = now.get(Calendar.DAY_OF_MONTH);
                Log.e("Date Math", "Today is " + today);

                final CheckBox b = (CheckBox) view.findViewById(R.id.breakfast_cb);
                if (b.isChecked()) {
                    breakfast = today;
                } else {
                    breakfast = 0;
                }
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int today = now.get(Calendar.DAY_OF_MONTH);
                Log.e("Date Math", "Today is " + today);

                final CheckBox l = (CheckBox) view.findViewById(R.id.lunch_cb);
                if (l.isChecked()) {
                    lunch = today;
                } else {
                    lunch = 0;
                }
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int today = now.get(Calendar.DAY_OF_MONTH);
                Log.e("Date Math", "Today is " + today);

                final CheckBox d = (CheckBox) view.findViewById(R.id.dinner_cb);
                if (d.isChecked()) {
                    dinner = today;
                } else {
                    dinner = 0;
                }
            }
        });

        if (breakfast == today)
        {
            b.setChecked(true);
        }
        if (lunch == today)
        {
            l.setChecked(true);
        }
        if (dinner == today)
        {
            d.setChecked(true);
        }
    }

    @Override
    public void onStop () {
        super.onStop();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("eating-breakfast", breakfast);
        editor.putInt("eating-lunch", lunch);
        editor.putInt("eating-dinner", dinner);
        editor.apply();

        Log.e("on-stop", "brk - " + String.valueOf(breakfast) + " lnh - " + String.valueOf(lunch) + " din - " + String.valueOf(dinner));
    }
}