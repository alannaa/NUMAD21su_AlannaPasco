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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayAbout(View view) {
        TextView aboutText = findViewById(R.id.aboutText);

        if (aboutText.getText().toString().isEmpty()) {
            aboutText.setText(R.string.aboutText_string);
        } else {
            aboutText.setText(R.string.no_value_string);
        }
    }

    String language = "en";
    // Attempt to "toggle" between arabic and english text for fun.
    // I could not figure how to change all text to arabic in one line, so I manually
    // reset all strings over again to make them display in arabic. I'm sure there is a cleaner
    // way to do this and would love help/feedback.
    public void toggleLanguage(View view) {
        if (language.equals("en")){
            language = "ar";
        } else {
            language = "en";
        }
        Context context = LocaleHelper.setLocale(MainActivity.this, language);

        Resources resources = context.getResources();

        TextView helloWorldText = findViewById(R.id.helloWorldView);
        helloWorldText.setText(resources.getString(R.string.helloWorld_string));

        TextView aboutText = findViewById(R.id.aboutText);
        aboutText.setText(resources.getString(R.string.aboutText_string));

        TextView aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setText(resources.getString(R.string.aboutButton_string));

        TextView langButton = findViewById(R.id.ToggleLangButton);
        langButton.setText(resources.getString(R.string.toggleLangButton_string));

    }

}