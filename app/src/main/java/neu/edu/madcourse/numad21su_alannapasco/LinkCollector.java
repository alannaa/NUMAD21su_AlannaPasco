package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        FloatingActionButton linkCFAB = findViewById(R.id.link_collector_FAB_id);
        linkCFAB.setOnClickListener(v -> fabListener());
    }

    // Allows User to add a link to the link collector via a custom dialogue
    // after the new link is added, a snackbar is displayed with the outcome of the add
    public void fabListener() {


    }
}