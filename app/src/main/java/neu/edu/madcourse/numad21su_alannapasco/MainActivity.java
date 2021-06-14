package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import neu.edu.madcourse.numad21su_alannapasco.Locator.Locator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.about_button_id);
        aboutButton.setOnClickListener(v -> aboutButtonListener());

        Button clickyButton = findViewById(R.id.clicky_button_id);
        clickyButton.setOnClickListener(v -> clickyButtonListener());

        Button linkCButton = findViewById(R.id.link_collector_button_id);
        linkCButton.setOnClickListener(v -> linkCButtonListener());

        Button locatorButton = findViewById(R.id.locator_button_id);
        locatorButton.setOnClickListener(v -> locatorButtonListener());

    }


    // Uses new Intent to create a flow between this interface and the 'about' page
    public void aboutButtonListener() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void clickyButtonListener() {
        Intent intent = new Intent(this, ClickyActivity.class);
        startActivity(intent);
    }

    public void linkCButtonListener() {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }

    public void locatorButtonListener() {
        Intent intent = new Intent(this, Locator.class);
        startActivity(intent);
    }

}