package neu.edu.madcourse.numad21su_alannapasco;

import android.view.View;

/*
    Interface for LinkItem
    Components needed:
        -This LinkCollector     activity
        -LinkClickListener      interface
        -LinkItem               class
        -RecViewAdapter         bridges the backend LinkItems to frontend RecViewHolder
        -RecViewHolder          manages what's in item_card.xml
 */
public interface LinkClickListener {
    void onItemClick(View view, int index);

    void onItemHold(View view, int index);
}
