package neu.edu.madcourse.numad21su_alannapasco;

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
    void onItemClick(int index);

    //void onItemHold(int index);
}
