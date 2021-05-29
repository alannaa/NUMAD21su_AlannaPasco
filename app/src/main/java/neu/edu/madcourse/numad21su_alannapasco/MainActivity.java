package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private String language = "en";
    Context context = LocaleHelper.setLocale(MainActivity.this, language);
    Resources resources = context.getResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "MainActivity onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void aboutButtonListener(View view) {
        //LAUNCH A NEW ACTIVITY HERE
        TextView aboutText = findViewById(R.id.aboutText);
        //If the space is currently empty, populate the aboutText (in the cur language)
        //If the space currently displays the aboutText, remove it.
        if (aboutText.getText().toString().isEmpty()) {
            aboutText.setText(resources.getString(R.string.aboutText_string));
        } else {
            aboutText.setText(resources.getString(R.string.no_value_string));
        }
    }

    // Toggles language between english and arabic
    public void languageButtonListener(View view) {
        if (language.equals("en")){
            language = "ar";
        } else {
            language = "en";
        }
        Context context = LocaleHelper.setLocale(MainActivity.this, language);
        resources = context.getResources();
        onStart();

//        Context context = LocaleHelper.setLocale(MainActivity.this, language);
//        Resources resources = context.getResources();
//
//        TextView helloWorldText = findViewById(R.id.hello_world_text_id);
//        helloWorldText.setText(resources.getString(R.string.helloWorld_string));
//
//        TextView aboutText = findViewById(R.id.about_button_id);
//        if (aboutText.getText().toString().isEmpty()) {
//            aboutText.setText(resources.getString(R.string.no_value_string));
//        } else {
//            aboutText.setText(resources.getString(R.string.aboutText_string));
//        }
//
//        TextView aboutButton = findViewById(R.id.about_button_id);
//        aboutButton.setText(resources.getString(R.string.aboutButton_string));
//
//        TextView langButton = findViewById(R.id.language_button_id);
//        langButton.setText(resources.getString(R.string.toggleLangButton_string));

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