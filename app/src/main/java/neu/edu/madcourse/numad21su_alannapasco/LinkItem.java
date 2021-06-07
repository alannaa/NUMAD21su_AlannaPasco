package neu.edu.madcourse.numad21su_alannapasco;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/*
    Represents one single item in the LinkCollector feature
    Components needed:
        -This LinkCollector     activity
        -LinkClickListener      interface
        -LinkItem               class
        -RecViewAdapter         bridges the backend LinkItems to frontend RecViewHolder
        -RecViewHolder          manages what's in item_card.xml
 */
public class LinkItem implements LinkClickListener {

    private final String linkName;
    public final String linkURL;

    public static final String INSTANCE_KEY = "INSTANCE_KEY";

    public LinkItem(String linkName, String linkURL){
        this.linkName = linkName;
        this.linkURL = linkURL;
    }

    @Override
    public void onItemClick(View view, int index){
        String properURL = ensureURLFormat(this.linkURL);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(properURL));
        try {
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(view.getContext(), "Error: browner cannot open this link",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Will make up for the fact that users may save URLs in any of the following format:
    // - google.com
    // - www.google.com
    // - https://www.google.com
    private String ensureURLFormat(String URL){
        //Must check that URL.length is > 11 to avoid string index out of bounds error
        if(URL.length() > 11 && URL.substring(0,12).equals("https://www.")){
            return URL;
        } if (URL.substring(0,4).equals("www.")){
            return "https://" + URL;
        }
        //If the URL is less than 11 characters, or isn't formatted correctly,
        //prefix the essential 11 characters to the URL
        return "https://www." + URL;
    }

    @Override
    public void onItemHold(View view, int index){
        Log.v("ONITEMHOLD _________", "ITEM HELD");
        //Attempted to implement the ability to edit URL from holding down the item
        //however could not get this to work due to the difficulty of getting the dialogue
        //box to work from this class
    }

    public String getLinkName(){
        return this.linkName;
    }

    public String getLinkURL(){
        return this.linkURL;
    }

    static String generateNameKey(int index){
        return INSTANCE_KEY + index + "na";
    }

    static String generateURLKey(int index){
        return INSTANCE_KEY + index + "ur";
    }

}
