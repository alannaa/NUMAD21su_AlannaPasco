package neu.edu.madcourse.numad21su_alannapasco.AtYourService;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class FlightRecViewAdapter extends RecyclerView.Adapter<FlightRecViewAdapter.RecViewHolder> {

    private final ArrayList<FlightInfo> flightList;

    public FlightRecViewAdapter(ArrayList<FlightInfo> flightlist) {
        this.flightList = flightlist;
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
        FlightInfo curFlight = flightList.get(index);
        holder.setFlightPrice(curFlight.getPrice());
        holder.setFlightDate(curFlight.getDate());
        holder.setFlightFrom(curFlight.getFrom());
        holder.setFlightTo(curFlight.getTo());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }
}
