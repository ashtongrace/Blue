package uwgb.cs292.blue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void reset_password(View view) {
        String u = ((EditText) findViewById(R.id.username)).getText().toString();
        String p = ((EditText) findViewById(R.id.password)).getText().toString();

        if (u != "" && p != "") {
            Intent intent = getIntent();
            intent.putExtra("username", u);
            intent.putExtra("password", p);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}