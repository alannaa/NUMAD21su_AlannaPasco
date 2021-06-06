package neu.edu.madcourse.numad21su_alannapasco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
    Bridges ArrayList of LinkItems to interactive RecyclerView
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecViewHolder> {

    private final ArrayList<LinkItem> linkList;
    private LinkClickListener listener;

    public RecViewAdapter(ArrayList<LinkItem> linkList) {
        this.linkList = linkList;
    }

    public void setOnClickListener(LinkClickListener listener) {
        this.listener = listener;
    }



    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent,
                false);
        return new RecViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int index) {
        LinkItem curItem = linkList.get(index);
        //This code has been slightly changed from the lecture:
        //I moved the setters to the Holder class to avoid such tight coupling/interclass-reliance
        holder.setItemName(curItem.getLinkName());
        holder.setItemDescr(curItem.getLinkURL());
    }

    @Override
    public int getItemCount(){
        return linkList.size();
    }

    public boolean linkListContains(LinkItem li) {
        return linkList.contains(li);
    }
}
