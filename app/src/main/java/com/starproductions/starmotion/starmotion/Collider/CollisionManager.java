package com.starproductions.starmotion.starmotion.Collider;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;

import com.starproductions.starmotion.starmotion.GameObjects.Actor;
import com.starproductions.starmotion.starmotion.GameObjects.SpaceShip;

import java.util.ArrayList;

import static com.starproductions.starmotion.starmotion.GameConstants.GRID_CONSTANT;

/**
 * Created by Admin on 23.07.2017.
 */

public class CollisionManager {

    private ArrayList<Actor> actors;
    private ArrayList<Actor>[][] grid;
    private int rectWidth;
    private int rectHeight;
    private int rows;
    private int columns;

    public CollisionManager() {
        int displayWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int displayHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        float aspectRatio = (float) displayWidth / displayHeight;
        rows = (int) (aspectRatio * GRID_CONSTANT);
        columns = (int) (GRID_CONSTANT / aspectRatio);
        rectWidth = displayWidth / rows;
        rectHeight = displayHeight / columns;
    }

    public void update(ArrayList<Actor> actors) {
        this.actors = actors;
        grid = new ArrayList[rows][columns];
        assignActors();
        searchCollisions();
    }

    private void assignActors() {
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            Rect hitBox = actor.getHitBox();
            float gridLeft = (float) hitBox.left / rectWidth;
            float gridRight = (float) hitBox.right / rectWidth;
            float gridTop = (float) hitBox.top / rectHeight;
            float gridBottom = (float) hitBox.bottom / rectHeight;
            int gridHorizontal = (int) gridLeft;
            int gridVertical = (int) gridTop;
            while (gridHorizontal <= gridRight && gridHorizontal < rows && gridHorizontal >= 0) {
                while (gridVertical <= gridBottom && gridVertical < columns && gridVertical >= 0) {
                    if (grid[gridHorizontal][gridVertical] == null)
                        grid[gridHorizontal][gridVertical] = new ArrayList<>();
                    grid[gridHorizontal][gridVertical].add(actors.get(i));
                    gridVertical++;
                }
                gridHorizontal++;
            }
        }
    }

    private void searchCollisions() {
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
        if (one.isPlayer() != two.isPlayer()) {
            Rect minLeft = one.getHitBox();
            Rect maxLeft = two.getHitBox();
            if (two.getHitBox().left < one.getHitBox().left) {
                minLeft = two.getHitBox();
                maxLeft = one.getHitBox();
            }
            if (minLeft.right > maxLeft.left) {
                Rect minTop = one.getHitBox();
                Rect maxTop = two.getHitBox();
                if (two.getHitBox().top < one.getHitBox().top) {
                    minTop = two.getHitBox();
                    maxTop = one.getHitBox();
                }
                if (minTop.bottom > maxTop.top) {
                    Log.v("Collision", "Collision");//Todo, delete
                    one.onCollide(two);
                    two.onCollide(one);
                }
            }
        }
    }
}
