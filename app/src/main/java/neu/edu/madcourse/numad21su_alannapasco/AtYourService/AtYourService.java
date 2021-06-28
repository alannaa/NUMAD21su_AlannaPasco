package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import neu.edu.madcourse.numad21su_alannapasco.AboutActivity;
import neu.edu.madcourse.numad21su_alannapasco.LinkCollector.LinkCollector;
import neu.edu.madcourse.numad21su_alannapasco.R;

public class AtYourService extends AppCompatActivity {

    //TODO: handle layout errors
    //TODO: test one way and anytime checkboxes

    //For simplicity, defaults include:
    //Country == US ; Currency == USD ; Locale == en-US
    private final String DEFAULTS_QUOTES = "/apiservices/browsequotes/v1.0/US/USD/en-US";
    private final String DEFAULTS_PLACES = "/apiservices/autosuggest/v1.0/US/USD/en-US";
    private final String apiHost = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
    private final String apiKey = "d464289547msh88d10c92e7e7c93p14eb4djsn3874fe4ced5d";
    private String errMsg = "";
    private final Handler thHandler = new Handler();

    private final String FLIGHTS = "FLIGHTS";

    private EditText depart, destin, departDate, returnDate;
    CheckBox anytimeCheckBox, onewayCheckBox;
    Button queryAPIbutton;
    ImageView loadingAnimation;
    String results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);

        depart = findViewById(R.id.depart_input_id);
        destin = findViewById(R.id.destin_input_id);
        departDate = findViewById(R.id.depart_date_input_id);
        returnDate = findViewById(R.id.return_date_input_id);

        onewayCheckBox = findViewById(R.id.oneway_checkbox_id);
        onewayCheckBox.setOnClickListener(v->onewayCheckBoxListener());

        anytimeCheckBox = findViewById(R.id.anytime_checkbox_id);
        anytimeCheckBox.setOnClickListener(v -> anytimeCheckBoxListener());

        queryAPIbutton = findViewById(R.id.queryAPI_button_id);
        queryAPIbutton.setOnClickListener(v -> queryAPIonSeparateRunnableThread());
        loadingAnimation = findViewById(R.id.loading_animation_id);
    }

    public void queryAPIonSeparateRunnableThread(){
        loadingAnimation.setVisibility(View.VISIBLE);
        queryAPIbutton.setVisibility(View.INVISIBLE);
        depart.setVisibility(View.INVISIBLE);
        destin.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(this, AYSResultsPage.class);
        Runnable query = new ScannerAPIQuery(intent);
        new Thread(query).start();
    }

    public void onewayCheckBoxListener() {
        if (onewayCheckBox.isChecked()) {
            returnDate.setVisibility(View.INVISIBLE);
        } else {
            returnDate.setVisibility(View.VISIBLE);
        }
    }

    public void anytimeCheckBoxListener() {
        if (anytimeCheckBox.isChecked()) {
            departDate.setVisibility(View.INVISIBLE);
            returnDate.setVisibility(View.INVISIBLE);
        } else {
            departDate.setVisibility(View.VISIBLE);
            returnDate.setVisibility(View.VISIBLE);
        }
    }

    //Class that implements the Runnable interface and queries the Scanner API
    class ScannerAPIQuery implements Runnable {

        final Intent resultsIntent;

        public ScannerAPIQuery(Intent resultsIntent){
            this.resultsIntent = resultsIntent;
        }

        @Override
        public void run(){
            try {
                URL endpoint = new URL(formatURLasString(
                        depart.getText().toString(), destin.getText().toString(),
                        departDate.getText().toString(),
                        returnDate.getText().toString()));

                HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
                conn.setRequestProperty("x-rapidapi-host", apiHost);
                conn.setRequestProperty("x-rapidapi-key", apiKey);
                conn.setRequestMethod("GET");
                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                results = convertStreamToString(inputStream);

            } catch (IOException e) {
                errMsg = "Sorry, there's an issue. Please check your network connection and " +
                        "try again";
            }

            thHandler.post(() -> {
                //Callback data
                loadingAnimation.setVisibility(View.INVISIBLE);
                queryAPIbutton.setVisibility(View.VISIBLE);
                depart.setVisibility(View.VISIBLE);
                destin.setVisibility(View.VISIBLE);

                if (!errMsg.equals("")) {
                    Toast t = Toast.makeText(AtYourService.this, errMsg, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    t.show();
                    errMsg = "";
                } else {
                    resultsIntent.putExtra(FLIGHTS, results);
                    startActivity(resultsIntent);
                }
            });
        }

        private String convertStreamToString(InputStream is) {
            Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next().replace(",", ",\n") : "";
        }

        private String formatURLasString(String depart, String destin, String departDate, String returnDate) {
            String formattedDepart = formatPlaceCode(depart);
            String formattedDestin = formatPlaceCode(destin);
            String formattedDepartDate = "anytime";
            String formattedReturnDate = "";

            //both checked
                //-need anytime and ""
            //anytime but not one way
                //-need anytime and anytime
            //one way but not anytime
                //-need depart and ""
            //both unchecked
                //-need depart and return

            if (anytimeCheckBox.isChecked() && !onewayCheckBox.isChecked()) {
                formattedDepartDate = "anytime";
                formattedReturnDate = "anytime";
            } if (onewayCheckBox.isChecked() &&!anytimeCheckBox.isChecked()) {
                formattedDepartDate = FlightInfo.reformatDateInputString(departDate);
                formattedReturnDate = "";
            } if (!onewayCheckBox.isChecked() &&!anytimeCheckBox.isChecked()) {
                formattedDepartDate = FlightInfo.reformatDateInputString(departDate);
                formattedReturnDate = FlightInfo.reformatDateInputString(returnDate);
            }

            String requiredInputs = "/" + formattedDepart + "/" + formattedDestin + "/" + formattedDepartDate;
            if (!formattedReturnDate.equals("")){
                requiredInputs += "/" + returnDate;
            }

            return "https://" + apiHost + DEFAULTS_QUOTES + requiredInputs + "/?rapidapi-key=" + apiKey;
        }

        private String formatPlaceCode(String place){
            try {
                URL endpoint = new URL("https://"
                        + apiHost + DEFAULTS_PLACES
                        + "/?query=" + place);
                HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
                conn.setRequestProperty("x-rapidapi-key", apiKey);
                conn.connect();

                InputStream inputStream = conn.getInputStream();
                final String response = convertStreamToString(inputStream);

                JSONObject results =  new JSONObject(response);
                JSONObject aPLace = results.getJSONArray("Places").getJSONObject(0);
                String placeID = aPLace.getString("CityId");
                return placeID;

            } catch (IOException e) {
                Log.v("PLACELOC_EXCEPTION", "io exception: " + e.toString());
            } catch (JSONException e) {
                Log.v("PLACELOC_EXCEPTION", "JSON exception" + e.toString());
            }
            return "";
        }
    }



}