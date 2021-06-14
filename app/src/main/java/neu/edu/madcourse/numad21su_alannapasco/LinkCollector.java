package neu.edu.madcourse.numad21su_alannapasco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/*
    Activity containing the Link Collector feature
    Components needed:
        -This LinkCollector     activity
        -LinkClickListener      interface
        -LinkItem               class
        -RecViewAdapter         bridges the backend LinkItems to frontend RecViewHolder
        -RecViewHolder          manages what's in item_card.xml
 */
public class LinkCollector extends AppCompatActivity implements LinkDialogue.LinkDialogueListener {

    private ArrayList<LinkItem> linkList = new ArrayList<>();

    FloatingActionButton addLinkFAB;
    private RecyclerView recView;
    private RecViewAdapter recViewAdapter;
    private RecyclerView.LayoutManager recLayoutManger;

    private static final String NUM_LINKS = "NUM_LINKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        addLinkFAB = findViewById(R.id.link_collector_FAB_id);
        addLinkFAB.setOnClickListener(this::fabListener);

        //Specify swipe actions: swiping left OR right deletes item
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView rv, RecyclerView.ViewHolder vh, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder vh, int dir) {
                int position = vh.getLayoutPosition();
                linkList.remove(position);
                recViewAdapter.notifyItemRemoved(position);

                Toast.makeText(LinkCollector.this, "Deleted an item", Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recView);
    }

    // Allows User to add a link to the link collector via a dialogue box
    // this method simply flows to the dialogue box
    public void fabListener(View view) {
        LinkDialogue linkDialogue = new LinkDialogue(view);
        linkDialogue.show(getSupportFragmentManager(), "link dialog");
    }

    @Override
    public void processLinkItem(View parentView, String linkName, String linkURL) {
        int newItemPosn = 0;
        LinkItem newItem = new LinkItem(linkName,  linkURL);
        linkList.add(newItemPosn, newItem);
        recViewAdapter.notifyItemInserted(newItemPosn);
        String msg = "New link added successfully";
        if(!recViewAdapter.linkListContains(newItem)){
            msg ="Something went wrong; Try again";
        }
        Snackbar.make(parentView, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
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
        LinkClickListener lcl = new LinkClickListener() {
            @Override
            public void onItemClick(View view, int index) {
                //attributions bond to the item has changed
                linkList.get(index).onItemClick(view, index);
                recViewAdapter.notifyItemChanged(index);
            }
            @Override
            public void onItemHold(View view, int index) {
                linkList.get(index).onItemHold(view, index);
                recViewAdapter.notifyItemChanged(index);
            }
        };
        recViewAdapter.setOnClickListener(lcl);
        recView.setAdapter(recViewAdapter);
        recView.setLayoutManager(recLayoutManger);

    }
    /////////////// End: Accommodate config changes ///////////////
}