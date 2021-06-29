package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class FlightRecViewAdapter extends RecyclerView.Adapter<FlightRecViewAdapter.RecViewHolder> implements Filterable {

    private final ArrayList<FlightInfo> flightList;
    private ArrayList<FlightInfo> displayedFlightList;

    public FlightRecViewAdapter(ArrayList<FlightInfo> flightlist) {
        this.flightList = flightlist;
        this.displayedFlightList = flightList;
    }

    public static class RecViewHolder extends  RecyclerView.ViewHolder {
        private final TextView price;
        private final TextView date;
        private final TextView from;
        private final TextView to;

        public RecViewHolder(final View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.flight_price_id);
            date = itemView.findViewById(R.id.flight_date_id);
            from = itemView.findViewById(R.id.flight_from_id);
            to = itemView.findViewById(R.id.flight_to_id);
        }

        public void setFlightPrice(String price){
            this.price.setText("$" + price);
        }

        public void setFlightDate(String date){
            this.date.setText("Flight Date: " + date);
        }

        public void setFlightFrom(String from){
            this.from.setText("From: " + from);
        }

        public void setFlightTo(String to){
            this.to.setText("To: " + to);
        }

    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent,
                false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlightRecViewAdapter.RecViewHolder holder, int index) {
        FlightInfo curFlight = displayedFlightList.get(index);

        holder.setFlightPrice(curFlight.getPrice());
        holder.setFlightDate(curFlight.getDate());
        holder.setFlightFrom(curFlight.getFrom());
        holder.setFlightTo(curFlight.getTo());
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<FlightInfo> filteredList = new ArrayList<>();
                if (constraint.equals("")){
                    displayedFlightList = flightList;

                    Log.v("HELP", "displayed" + displayedFlightList.toString());
                    Log.v("HELP", "all" + flightList.toString());
                    notifyDataSetChanged();
                } else {

                    for (FlightInfo f : flightList) {
                        if (f.getLeg().equals(constraint.toString())) {
                            filteredList.add(f);
                        }
                    }
                    displayedFlightList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return displayedFlightList.size();
    }

    public ArrayList<FlightInfo> getDisplayList(){
        return this.displayedFlightList;
    }

}
