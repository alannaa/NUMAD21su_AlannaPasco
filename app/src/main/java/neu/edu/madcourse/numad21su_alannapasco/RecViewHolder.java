package neu.edu.madcourse.numad21su_alannapasco;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/*
    A generalized RecycleView ViewHolder for a layout that has an item name and description
 */
public class RecViewHolder extends RecyclerView.ViewHolder {

    public TextView itemName;
    public TextView itemDescr;

    public RecViewHolder(View itemView, final LinkClickListener listener) {
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name_id);
        itemDescr = itemView.findViewById(R.id.item_descr_id);

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                int index = getLayoutPosition();
                if (index != RecyclerView.NO_POSITION) {
                    listener.onItemClick(index);
                }
            }
        });
    }

    public void setItemName(String newItemName){
        this.itemName.setText(newItemName);
    }

    public void setItemDescr(String newItemDescr){
        this.itemDescr.setText(newItemDescr);
    }
}
