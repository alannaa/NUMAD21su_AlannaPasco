package neu.edu.madcourse.numad21su_alannapasco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

/*
    Activity containing the Link Collector feature
    Components needed:
        -This LinkCollector     activity
        -LinkClickListener      interface
        -LinkItem               class
        -RecViewAdapter         bridges the backend LinkItems to frontend RecViewHolder
        -RecViewHolder          manages what's in item_card.xml
 */
public class LinkCollector extends AppCompatActivity {

    private ArrayList<LinkItem> linkList = new ArrayList<>();

    private RecyclerView recView;
    private RecViewAdapter recViewAdapter;
    private RecyclerView.LayoutManager recLayoutManger;

    private static final String NUM_LINKS = "NUM_LINKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        FloatingActionButton addLinkFAB = findViewById(R.id.link_collector_FAB_id);
        addLinkFAB.setOnClickListener(v -> fabListener());

        //TODO Item touch helper (swipe right/left)
    }

    // Allows User to add a link to the link collector via a custom dialogue
    // after the new link is added, a snackbar is displayed with the outcome of the add
    public void fabListener() {
        //Always add to top of list; posn set to 0 always
        int newItemPosn = 0;
        //TODO: DIALOGUE BOX

        linkList.add(newItemPosn, new LinkItem("EXAMPLE NAME",  "example url . com"));
        recViewAdapter.notifyItemInserted(newItemPosn);

        //TODO SNACKBAR
    }


    /////////////// Accommodate config changes ///////////////
    //Handle orientation changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = linkList == null ? 0 : linkList.size();
        outState.putInt(NUM_LINKS, size);

        for(int i = 0; i < size; i++){
            // send key/value pair for each link on list
            outState.putString(LinkItem.generateNameKey(i), linkList.get(i).getLinkName());
            outState.putString(LinkItem.generateURLKey(i), linkList.get(i).getLinkURL());
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
            if (linkList != null || linkList.size() == 0) {
                int size = savedInstanceState.getInt(NUM_LINKS);
                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString(LinkItem.generateNameKey(i));
                    String linkURL = savedInstanceState.getString(LinkItem.generateURLKey(i));

                    LinkItem linkItem = new LinkItem(linkName, linkURL);
                    linkList.add(linkItem);
                }
            }
        }
        //else, we want the list to appear blank
    }

    private void createRecyclerView(){
        recLayoutManger = new LinearLayoutManager(this);

        recView = findViewById(R.id.recycler_view_id);
        recView.setHasFixedSize(true);

        recViewAdapter = new RecViewAdapter(linkList);
        LinkClickListener icl = new LinkClickListener() {
            @Override
            public void onItemClick(int index) {
                //attributions bond to the item has changed
                linkList.get(index).onItemClick(index);
                recViewAdapter.notifyItemChanged(index);
            }
        };
        recViewAdapter.setOnListClickListener(icl);
        recView.setAdapter(recViewAdapter);
        recView.setLayoutManager(recLayoutManger);
    }
    /////////////// End: Accommodate config changes ///////////////
}