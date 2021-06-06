package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
    }


    // Uses new Intent to create a flow between this interface and the 'about' page
    public void aboutButtonListener() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    // Uses new Intent to create a flow between this interface and the 'clicky' page
    public void clickyButtonListener() {
        Intent intent = new Intent(this, ClickyActivity.class);
        startActivity(intent);
    }

    // Uses new Intent to create a flow between this interface and the 'Link Collector' page
    public void linkCButtonListener() {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }

}