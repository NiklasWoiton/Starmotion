package com.starproductions.starmotion.starmotion;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;

import com.starproductions.starmotion.starmotion.GameObjects.Actor;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.GameObjects.SpaceShip;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.starproductions.starmotion.starmotion.GameConstants.GRID_CONSTANT;

/**
 * Created by Admin on 23.07.2017.
 */

public class CollisionManager {

    private ArrayList<Actor> actors;
    private ArrayList<Actor>[][] grid;
    private float rectWidth;
    private float rectHeight;
    private int rows;
    private int columns;

    public CollisionManager() {
        int displayWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int displayHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        float aspectRatio = (float) displayWidth / displayHeight;
        rows = (int) (GRID_CONSTANT / aspectRatio);
        columns = (int) (GRID_CONSTANT * aspectRatio);
        rectWidth = displayWidth / columns;
        rectHeight = displayHeight / rows;
    }

    public void update(ArrayList<Actor> actors) {
        this.actors = actors;
        grid = new ArrayList [rows][columns];
        assignActors();
        findPossibleCollisions();
    }

    private void assignActors(){
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            Rect hitBox = actor.getHitBox();
            int leftColumn = (int) (hitBox.left / rectWidth);
            int rightColumn = (int) (hitBox.right / rectWidth);
            for (;leftColumn <= rightColumn; leftColumn++){
                int topRow = (int) (hitBox.top / rectHeight);
                int bottomRow = (int) (hitBox.bottom / rectHeight);
                if(leftColumn >= 0 && leftColumn < columns){
                    for (;topRow <= bottomRow; topRow++){
                        if(topRow >= 0 && topRow < rows){
                            if (grid[topRow][leftColumn] == null) {
                                grid[topRow][leftColumn] = new ArrayList<>();
                            }
                            grid[topRow][leftColumn].add(actor);
                        }
                    }
                }
            }
        }
    }

    private void findPossibleCollisions() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ArrayList<Actor> list = grid[i][j];
                if (list != null && list.size() > 1) {
                    for (int k = 0; k < list.size() - 1; k++) {
                        if (list.get(k) instanceof SpaceShip) {
                            for (int l = k + 1; l < list.size(); l++) {
                                collisionDetection(list.get(k), list.get(l));
                            }
                        }
                    }
                }
            }
        }
    }

    private void collisionDetection(Actor one, Actor two) {
        if (one.isPlayer() != two.isPlayer()){
            if (one.getHitBox().intersect(two.getHitBox())) {
                one.onCollide(two);
                two.onCollide(one);
            }
        }
    }
}
