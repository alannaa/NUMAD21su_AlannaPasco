package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button aboutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "MainActivity onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.about_button_id);
        aboutButton.setOnClickListener(v -> aboutButtonListener());
    }

    // Uses a new Intent to create a flow between this interface and the 'about' page
    // Then uses 'startActivity' to navigate to the new activity
    public void aboutButtonListener() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart(){
        Log.v(TAG, "MainActivity onStart()");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.v(TAG, "MainActivity onResume()");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.v(TAG, "MainActivity onPause()");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.v(TAG, "MainActivity onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.v(TAG, "MainActivity onDestroy()");
        super.onDestroy();
    }


}