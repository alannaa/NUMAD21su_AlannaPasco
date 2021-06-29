package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class AYSResultsPage extends AppCompatActivity {

    private ArrayList<FlightInfo> flightList = new ArrayList<>();

    private FlightRecViewAdapter recViewAdapter;
    private CheckBox filterOut, filterIn;
    private JSONObject results;

    private static final String NUM_FLIGHTS = "NUM_FLIGHTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aysresults_page);

        filterOut = findViewById(R.id.out_filter_id);
        filterOut.setOnClickListener(v -> filterCheckListener());

        filterIn = findViewById(R.id.in_filter_id);
        filterIn.setOnClickListener(v -> filterCheckListener());

        createRecyclerView();
        buildFlightList();
    }


    @Override
    protected void onResume() {
        //handles saving the filter when changing orientation
        super.onResume();
        filterCheckListener();
    }

    public void filterCheckListener() {
        if (filterOut.isChecked() && !filterIn.isChecked()) {
            recViewAdapter.getFilter().filter("OUT");
        } if (filterIn.isChecked() && !filterOut.isChecked()) {
            recViewAdapter.getFilter().filter("IN");
        } if ((!filterIn.isChecked() && !filterOut.isChecked()) ||
                (filterIn.isChecked() && filterOut.isChecked())) {
            recViewAdapter.getFilter().filter("");
        }

    }

    private void buildFlightList(){
        int numOutBound = 0;
        try {
            results = new JSONObject(getIntent().getStringExtra("FLIGHTS"));
            JSONArray quotes = results.getJSONArray("Quotes");
            JSONArray places = results.getJSONArray("Places");

            for(int i = 0; i < quotes.length(); i++){
                JSONObject aQuote = quotes.getJSONObject(i);
                JSONObject legOut = aQuote.getJSONObject("OutboundLeg");
                //made up of JSONObjects with keys: MinPrice, OriginId, DestinationId, DepartureDate
                FlightInfo newOut = new FlightInfo(
                        aQuote.getString("MinPrice"),
                        FlightInfo.reformatDateOutputString(legOut.getString("DepartureDate")),
                        getPlaceName(places, legOut.getString("OriginId")),
                        getPlaceName(places, legOut.getString("DestinationId")),
                        "OUT");
                flightList.add(numOutBound, newOut);
                recViewAdapter.notifyItemInserted(numOutBound);
                numOutBound++;

                if (aQuote.has("InboundLeg")) {
                    JSONObject legIn = aQuote.getJSONObject("InboundLeg");
                    FlightInfo newIn = new FlightInfo(
                            aQuote.getString("MinPrice"),
                            FlightInfo.reformatDateOutputString(legIn.getString("DepartureDate")),
                            getPlaceName(places, legIn.getString("OriginId")),
                            getPlaceName(places, legIn.getString("DestinationId")),
                            "IN");
                    flightList.add(newIn);
                    recViewAdapter.notifyItemInserted(flightList.size()-1);
                }
            }

            if (quotes.length() == 0){
                TextView errorMessage = findViewById(R.id.ays_results_error_id);
                errorMessage.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            handleJsonError();
        }
    }

    public String getPlaceName(JSONArray places, String id){
        try {
            for (int i = 0; i < places.length(); i++){
                JSONObject aPlace = places.getJSONObject(i);
                String matchId = aPlace.getString("PlaceId");
                if (matchId.equals(id)) {
                    return aPlace.getString("Name");
                }
            }
        } catch (JSONException e) {
            handleJsonError();
        }
        return "Null";
    }

    public void handleJsonError(){
        //Kill this activity and return to previous screen:
        Toast t = Toast.makeText(AYSResultsPage.this, "Oops, something went wrong on our end. Try again!", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        t.show();
        finish();
    }

    private void createRecyclerView(){
        RecyclerView.LayoutManager recLayoutManager = new LinearLayoutManager(this);
        RecyclerView recView = findViewById(R.id.flight_recycler_view_id);

        recViewAdapter = new FlightRecViewAdapter(flightList);
        recView.setAdapter(recViewAdapter);
        recView.setLayoutManager(recLayoutManager);
    }
}