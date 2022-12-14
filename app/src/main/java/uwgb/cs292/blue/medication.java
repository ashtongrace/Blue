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
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;


public class medication extends Fragment {
    HashSet<String> medications = new HashSet<String>();
    HashMap<String, Integer> amounts = new HashMap<String, Integer>();

    public medication() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        medications = (HashSet<String>) preferences.getStringSet("medication-names", new HashSet<String>());

        int amount = 0;

        for (String medication : medications) {
            amount = preferences.getInt("medication-" + medication, 0);
            amounts.put(medication, amount);
            Log.d("medications-oncreated", "The " + medication + " with an amount of " + String.valueOf(amount) + " was stored previously");
        }

        Log.d("medications-oncreated", "The medications HashSet has " + String.valueOf(medications.size()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medication, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        for (String name : amounts.keySet()) {
            TextView t = (TextView) getActivity().findViewById(R.id.medication_list);
            t.setLines(amounts.size() + 1);
            t.append(name + " : " + String.valueOf(amounts.get(name)) + System.getProperty("line.separator"));
        }

        final Button b = (Button) view.findViewById(R.id.medication_enter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("medication-onclick", "clicked");

                String name = ((EditText) view.findViewById(R.id.medication_name)).getText().toString();
                if (name.isEmpty()) {
                    return;
                }

                try {
                    Integer amount = Integer.parseInt(
                            ((EditText) view.findViewById(R.id.medication_amount)).getText().toString()
                    );
                    amounts.put(name, amount);
                    TextView t = (TextView) view.findViewById(R.id.medication_list);
                    t.setLines(amounts.size() + 1);
                    t.append(name + " : " + String.valueOf(amount) + System.getProperty("line.separator"));
                } catch (Exception e) {
                    return;
                }

                if (medications.add(name)) {
                    Log.d("medications-onclick", "this medication was not in the list before, it was added");
                } else {
                    Log.d("medications-onclick", "this medication was found in the list before, it was not re-added");
                }
            }
        });
    }

    @Override
    public void onStop () {
        super.onStop();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putStringSet("medication-names", medications);
        for (String name : amounts.keySet()) {
            editor.putInt("medication-" + name, amounts.get(name));
        }
        editor.apply();

        Log.e("on-stop", "applied medications hashset");
    }

    public void medlist() {
        TextView t = (TextView) getActivity().findViewById(R.id.medication_list);
        String b = "";

        t.setLines(amounts.size() + 1);
        for (String name : amounts.keySet()) {
            b = b + name + " : " + String.valueOf(amounts.get(name)) + System.getProperty("line.separator");
        }
    }
}