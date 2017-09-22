package com.starproductions.starmotion.starmotion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jakob on 21.09.2017.
 */

public class HighscoreNameDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DarkAlertStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_name_highscore, null));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameActivity gameActivity = (GameActivity) getActivity();
                EditText input = ((AlertDialog)dialogInterface).findViewById(R.id.dialog_name_highscore_input);
                gameActivity.saveScore(input.getText().toString());
            }
        });

        return builder.create();
    }

    @Override
    public void onStart(){
        super.onStart();
        Button positive = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(getResources().getColor(R.color.colorStarmotionYellow));
        positive.setBackgroundColor(getResources().getColor(R.color.black));
    }
}
