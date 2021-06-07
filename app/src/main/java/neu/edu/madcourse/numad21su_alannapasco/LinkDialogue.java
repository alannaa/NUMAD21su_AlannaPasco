package neu.edu.madcourse.numad21su_alannapasco;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class LinkDialogue extends AppCompatDialogFragment {

    public interface LinkDialogueListener {
        void processLinkItem(View parentView, String linkName, String linkURL);
    }

    private EditText editTextName;
    private EditText editTextURL;
    private LinkDialogueListener listener;
    private final View parentView;

    public LinkDialogue(View parentView) {
        this.parentView = parentView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newlink_dialogue, null);

        builder.setView(view)
                .setTitle("Add New Link")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancelling closes the dialog box
                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //onClick is over ridden below to enforce correctly formatted input
                    }
                });
        editTextName = view.findViewById(R.id.edit_name_id);
        editTextURL = view.findViewById(R.id.edit_url_id);

        AlertDialog d = builder.create();
        d.show();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> onClickSaveButton(d));
        return d;
    }

    //Handle the intricacies of the 'save' button
    // - error when name/URL omitted or URL not formatted correctly
    // - if no errors, process the new link item
    public void onClickSaveButton(AlertDialog d){
        String linkName = editTextName.getText().toString();
        String linkURL = editTextURL.getText().toString();
        boolean errors = false;

        if (linkName.isEmpty() || linkURL.isEmpty()) {
            Toast t = Toast.makeText(parentView.getContext(), "Name and URL required",
                    Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            t.show();
            errors = true;
        }

        if (!linkURL.isEmpty() && !Patterns.WEB_URL.matcher(editTextURL.getText().toString()).matches()) {
            Toast t = Toast.makeText(parentView.getContext(), "Invalid URL",
                    Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            t.show();
            errors = true;
        }
        if (!errors) {
            listener.processLinkItem(parentView, linkName, linkURL);
            d.dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        try {
            listener = (LinkDialogueListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement LinkDialogueListener");
        }
        super.onAttach(context);
    }
}
