package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void displayAbout(View view) {
        TextView aboutText =  findViewById(R.id.aboutText); //by default has no value
        aboutText.setText(R.string.aboutText_string);
    }

}