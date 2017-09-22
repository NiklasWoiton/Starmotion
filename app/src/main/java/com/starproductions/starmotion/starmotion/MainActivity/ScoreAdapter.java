package com.starproductions.starmotion.starmotion.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.ScoreSystem.Score;

import java.util.ArrayList;

class ScoreAdapter extends ArrayAdapter<Score> {

    private ArrayList<Score> scoreArrayList;
    private TextView name;
    private TextView score;
    private Context context;


    ScoreAdapter(@NonNull Context context, @NonNull ArrayList<Score> objects) {
        super(context, R.layout.list_item_score, objects);
        scoreArrayList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        Score score = scoreArrayList.get(position);

        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_score, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.score_list_item_name)).setText(score.getPlayername());
        ((TextView)convertView.findViewById(R.id.score_list_item_score)).setText(String.valueOf(score.getScore()));

        return convertView;
    }

}
