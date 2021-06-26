package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class AtYourService extends AppCompatActivity {

    private Handler thHandler = new Handler();
    ImageView loadingAnimation;
    Button queryAPIbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);

        queryAPIbutton = findViewById(R.id.queryAPI_button_id);
        queryAPIbutton.setOnClickListener(v -> queryAPIonSeparateRunnableThread());

        loadingAnimation = findViewById(R.id.loading_animation_id);

        //Defaults:
        // Country: US
        // Currency: USD
        // Locality: en-US
        //What we'll need to interact with:
        //originplace
            //Need to query database for code translation
        //destinationplace
            //Need to query database for code translation
        //Dropdown:
            // Round trip -> displays both date boxes
            // One way -> displays only departure date box, return set to " "
        //CheckBox:
        //Anytime - just find me the cheapest! (removes dates input boxes; set both any time)
        //Departure Date - outboundpartialdate (optional)
            //Specify YYYY-MM-DD or YYYY-MM or "anytime"(maybe default anytime)
        //Return Date - inboundpartialdate (optional)
            //empty string == one way  vs "anytime" means anytime
    }

    public void queryAPIonSeparateRunnableThread(){
        loadingAnimation.setVisibility(View.VISIBLE);
        queryAPIbutton.setVisibility(View.INVISIBLE);
        Runnable query = new ScannerAPIQuery();
        new Thread(query).start();
    }

    //Class that implements the Runnable interface and queries the Scanner API
    class ScannerAPIQuery implements Runnable {
        @Override
        public void run(){

            //This is where you will query the API
            //and send the results back to the UI Thread to display the results
            Log.v("RUNNABLE", "!!!!running on different thread!!!!!!!!");
            try {
                Thread.sleep(10000); // 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.v("RUNNABLE", "!!!waking up!!!!!!!");


            thHandler.post(() -> {
                //What you send back
                loadingAnimation.setVisibility(View.INVISIBLE);
                queryAPIbutton.setVisibility(View.VISIBLE);
            });
        }
    }

}