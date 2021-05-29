package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class AboutActivity extends AppCompatActivity {

    private final String TAG = "AboutActivity____";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "AboutActivity onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


    @Override
    protected void onStart(){
        Log.v(TAG, "AboutActivity onStart()");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.v(TAG, "AboutActivity onResume()");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.v(TAG, "AboutActivity onPause()");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.v(TAG, "AboutActivity onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.v(TAG, "AboutActivity onDestroy()");
        super.onDestroy();
    }

}