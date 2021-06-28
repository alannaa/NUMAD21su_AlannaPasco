package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class AYSResultsPage extends AppCompatActivity {

    private ArrayList<FlightInfo> flightList = new ArrayList<>();

    private RecyclerView recView;
    private FlightRecViewAdapter recViewAdapter;
    private RecyclerView.LayoutManager recLayoutManager;
    private JSONObject results;

    private static final String NUM_LINKS = "NUM_LINKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aysresults_page);

        init(savedInstanceState);

        try {
            results = new JSONObject(getIntent().getStringExtra("FLIGHTS"));
            JSONArray quotes = results.getJSONArray("Quotes");
            JSONArray places = results.getJSONArray("Places");

            for(int i = 0; i < quotes.length(); i++){
                JSONObject aQuote = quotes.getJSONObject(i);
                JSONObject leg = aQuote.getJSONObject("OutboundLeg");
                //made up of JSONObjects with keys: MinPrice, OriginId, DestinationId, DepartureDate
                FlightInfo newFlight = new FlightInfo(
                        aQuote.getString("MinPrice"),
                        FlightInfo.reformatDateOutputString(leg.getString("DepartureDate")),
                        getPlaceName(places, leg.getString("OriginId")),
                        getPlaceName(places, leg.getString("DestinationId")));
                flightList.add(newFlight);
                recViewAdapter.notifyItemInserted(flightList.size()-1);
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

    /////////////// Accommodate config changes ///////////////
    //Handle orientation changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = flightList == null ? 0 : flightList.size();
        outState.putInt(NUM_LINKS, size);

        for(int i = 0; i < size; i++){
            // send key/value pair for each link on list
            outState.putString(FlightInfo.generatePriceKey(i), String.valueOf(flightList.get(i).getPrice()));
            outState.putString(FlightInfo.generateDateKey(i), flightList.get(i).getDate());
            outState.putString(FlightInfo.generateFromKey(i), flightList.get(i).getFrom());
            outState.putString(FlightInfo.generateToKey(i), flightList.get(i).getTo());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState){
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState){
        //If this activity has been opened before and has existing data
        if (savedInstanceState != null && savedInstanceState.containsKey(NUM_LINKS)) {
            if (flightList != null || flightList.size() == 0) {
                int size = savedInstanceState.getInt(NUM_LINKS);
                for (int i = 0; i < size; i++) {
                    String price = savedInstanceState.getString(FlightInfo.generatePriceKey(i));
                    String date = savedInstanceState.getString(FlightInfo.generateDateKey(i));
                    String from = savedInstanceState.getString(FlightInfo.generateFromKey(i));
                    String to = savedInstanceState.getString(FlightInfo.generateToKey(i));

                    FlightInfo flight = new FlightInfo(price, date, from, to);
                    flightList.add(flight);
                }
            }
        }
        //else, we want the list to appear blank
    }

    private void createRecyclerView(){
        recLayoutManager = new LinearLayoutManager(this);

        recView = findViewById(R.id.flight_recycler_view_id);

        recViewAdapter = new FlightRecViewAdapter(flightList);
        recView.setAdapter(recViewAdapter);
        recView.setLayoutManager(recLayoutManager);
    }
    /////////////// End: Accommodate config changes ///////////////
}