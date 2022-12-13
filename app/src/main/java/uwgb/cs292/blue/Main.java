package uwgb.cs292.blue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController nc = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bnv = findViewById(R.id.bnv);
        NavigationUI.setupWithNavController(bnv, nc);

        System.out.println("This is a test for git");
    }
}