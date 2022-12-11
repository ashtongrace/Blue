package uwgb.cs292.blue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Objects;

public class Login extends AppCompatActivity {
    private HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users.put("w", "w");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String u = data.getStringExtra("username");
                String p = data.getStringExtra("password");
                Log.d("add-user", u);
                Log.d("add-user", p);
                users.put(u, p);
            }
        }
    }

    public void login(View view) {
        String u = ((EditText) findViewById(R.id.username)).getText().toString();
        String p = ((EditText) findViewById(R.id.password)).getText().toString();

        if (Objects.equals(users.get(u), p)) {
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        }
    }

    public void reset_password(View view) {
        Intent intent = new Intent(this, ResetPassword.class);
        startActivityForResult(intent, 1);
    }
}