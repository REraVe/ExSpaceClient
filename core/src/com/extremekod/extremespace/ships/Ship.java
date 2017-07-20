package com.extremekod.extremespace.ships;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

/**
 * Created by Gratt on 20.06.2017.
 */

public abstract class Ship extends Actor{

    private static final int TEXTURE_WIDTH  = 256;
    private static final int TEXTURE_HEIGHT = 128;

    public static final float HALF_WIDTH  = TEXTURE_WIDTH / 2;
    public static final float HALF_HEIGHT = TEXTURE_HEIGHT / 2;

    public static final float CENTRAL_FIRE_POINT = TEXTURE_HEIGHT / 2 - 16;

    public static ArrayList<Ship> ships = new ArrayList<Ship>();

    private Rectangle collisionBox;

    public boolean isFireLightLaser = false;
    public boolean isFireRocket     = false;

    public boolean target;

    public Ship(int x, int y, boolean target){
        this.setHeight(TEXTURE_HEIGHT);
        this.setWidth(TEXTURE_WIDTH);
        this.setOrigin(HALF_WIDTH, HALF_HEIGHT);
        this.setX(x);
        this.setY(y);

        this.target = target;

        setCollisonBox();

        ships.add(this);
    }

    private void setCollisonBox() {
        collisionBox = new Rectangle(this.getX() + HALF_WIDTH / 2, this.getY() + HALF_HEIGHT / 2, HALF_WIDTH, HALF_HEIGHT);
    }

    private void updateCollisionBox() {
        collisionBox.set(this.getX() + HALF_WIDTH / 2, this.getY() + HALF_HEIGHT / 2, HALF_WIDTH, HALF_HEIGHT);
    }

    public void moveDown(float y) {
        this.setY(this.getY() - y);

        updateCollisionBox();
    }

    public void moveUp(float y){
        this.setY(this.getY() + y);

        updateCollisionBox();
    }

    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }

    public static ArrayList<Ship> getShips() {
        return ships;
    }

    public void fireLightLaser (Stage stage){}

    public void fireRocket (Stage stage){}
}


