package neu.edu.madcourse.numad21su_alannapasco;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String linkName = editTextName.getText().toString();
                        String linkURL = editTextURL.getText().toString();
                        listener.processLinkItem(parentView, linkName, linkURL);
                    }
                });
        editTextName = view.findViewById(R.id.edit_name_id);
        editTextURL = view.findViewById(R.id.edit_url_id);

        return builder.create();
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
