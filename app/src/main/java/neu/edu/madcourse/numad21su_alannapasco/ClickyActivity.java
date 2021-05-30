package neu.edu.madcourse.numad21su_alannapasco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Activity manager of Clicky page UI
public class ClickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        Button AButton = findViewById(R.id.button_a_id);
        AButton.setOnClickListener(this::clickyButtonListener);

        Button BButton = findViewById(R.id.button_b_id);
        BButton.setOnClickListener(this::clickyButtonListener);

        Button CButton = findViewById(R.id.button_c_id);
        CButton.setOnClickListener(this::clickyButtonListener);

        Button DButton = findViewById(R.id.button_d_id);
        DButton.setOnClickListener(this::clickyButtonListener);

        Button EButton = findViewById(R.id.button_e_id);
        EButton.setOnClickListener(this::clickyButtonListener);

        Button FButton = findViewById(R.id.button_f_id);
        FButton.setOnClickListener(this::clickyButtonListener);
    }

    //IDE warns against using IDs in switch statements. Any advice on a fix?
    public void clickyButtonListener(View v){
        switch(v.getId()) {
            case R.id.button_a_id:
                togglePressedMsg(R.string.A_string);
                break;
            case R.id.button_b_id:
                togglePressedMsg(R.string.B_string);
                break;
            case R.id.button_c_id:
                togglePressedMsg(R.string.C_string);
                break;
            case R.id.button_d_id:
                togglePressedMsg(R.string.D_string);
                break;
            case R.id.button_e_id:
                togglePressedMsg(R.string.E_string);
                break;
            case R.id.button_f_id:
                togglePressedMsg(R.string.F_string);
                break;
        }
    }

    //Helper function to toggle between displaying a "Pressed: X" message and returning
    //to the default of "Pressed: __" with no letter displayed
    public void togglePressedMsg(int buttonPressed){
        //The 'pressed_string' TextView is currently:
        TextView curPressedMsg = findViewById(R.id.cur_pressed_msg_id);
        //We want the pressed_string TextView to be updated to:
        String newPressedStr = getResources().getString(R.string.pressed_string) + " " +
                getResources().getString(buttonPressed);
        //If the most recently pressed button was the same button, we want to toggle the
        //pressed message on and off:
        if (curPressedMsg.getText().toString().equals(newPressedStr)) {
            curPressedMsg.setText(R.string.pressed_string);
        } else {
            curPressedMsg.setText(newPressedStr);
        }
    }
}