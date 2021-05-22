package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String language = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayAbout(View view) {
        //Set the context to the appropriate language
        Context context = LocaleHelper.setLocale(MainActivity.this, language);
        Resources resources = context.getResources();
        //Grab the aboutText TextView
        TextView aboutText = findViewById(R.id.aboutText);
        //If the space is currently empty, populate the aboutText (in the cur language)
        //If the space currently displays the aboutText, remove it.
        if (aboutText.getText().toString().isEmpty()) {
            aboutText.setText(resources.getString(R.string.aboutText_string));
        } else {
            aboutText.setText(resources.getString(R.string.no_value_string));
        }
    }

    // Attempt to translate app between English, Arabic, and Spanish, for fun
    // I could not figure how to change all text in one line, so I manually
    // reset all strings over again to make them display in the new language.
    // I'm sure there is a cleaner way to do this and would love help/feedback.
    public void toggleLanguage(View view) {
        switch(language) {
            case "en":
                language = "ar";
                break;
            case "ar":
                language = "es";
                break;
            default:
                language = "en";
        }
        Context context = LocaleHelper.setLocale(MainActivity.this, language);
        Resources resources = context.getResources();

        TextView helloWorldText = findViewById(R.id.helloWorldView);
        helloWorldText.setText(resources.getString(R.string.helloWorld_string));

        TextView aboutText = findViewById(R.id.aboutText);
        if (aboutText.getText().toString().isEmpty()) {
            aboutText.setText(resources.getString(R.string.no_value_string));
        } else {
            aboutText.setText(resources.getString(R.string.aboutText_string));
        }

        TextView aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setText(resources.getString(R.string.aboutButton_string));

        TextView langButton = findViewById(R.id.ToggleLangButton);
        langButton.setText(resources.getString(R.string.toggleLangButton_string));

    }

}