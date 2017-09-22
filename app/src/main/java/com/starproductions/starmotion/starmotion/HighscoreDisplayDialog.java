package com.starproductions.starmotion.starmotion;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.starproductions.starmotion.starmotion.ScoreSystem.Score;
import com.starproductions.starmotion.starmotion.ScoreSystem.ScoreManager;

import java.util.ArrayList;

/**
 * Created by Jakob on 22.09.2017.
 */

public class HighscoreDisplayDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        ScoreManager scoreManager = new ScoreManager(getActivity());
        scoreManager.start();
        ArrayList<Score> scores = scoreManager.getAllScores();
        scoreManager.stop();
        ScoreAdapter scoreAdapter = new ScoreAdapter(getActivity(), scores);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DarkAlertStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_display_highscore, null);
        ListView listView = view.findViewById(R.id.dialog_display_highscore_list);

        listView.setAdapter(scoreAdapter);

        builder.setView(view);
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

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
