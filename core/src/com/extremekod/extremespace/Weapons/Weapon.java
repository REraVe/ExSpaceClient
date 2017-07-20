package com.extremekod.extremespace.Weapons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.extremekod.extremespace.ships.Ship;

/**
 * Created by Gratt on 23.06.2017.
 */

public class Weapon extends Actor {

    boolean target;
    public boolean dead = false;

    private static final int TEXTURE_HEIGHT = 32;
    private static final int TEXTURE_WIDTH  = 32;
    public static final float HALF_WIDTH  = TEXTURE_WIDTH / 2;
    public static final float HALF_HEIGHT = TEXTURE_HEIGHT / 2;

    private Ship self;

    private Rectangle collisionBox;

    public void draw(Batch batch, float parentAlpha,float fly_speed) {
        if (!dead)
            super.draw(batch, parentAlpha);

        fly(fly_speed);

        updateCollisionBox();
    }

    public Weapon(float x, float y, boolean target, Ship self) {
        this.target = target;
        this.setHeight(TEXTURE_HEIGHT);
        this.setWidth(TEXTURE_WIDTH);

        init_position(x, y, target);

        setCollisionBox();
        this.self = self;
    }

    private void setCollisionBox() {
        collisionBox = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }

    private void init_position(float x, float y, boolean target) {
        if (target)
            this.setX(x);
        else
            this.setX(x);

        this.setY(y);

        this.setOrigin(x + HALF_WIDTH, y + HALF_HEIGHT);
    }

    private void fly(float speed) {
        if (target)
            this.setX(this.getX() + speed);
        else
            this.setX(this.getX() - speed);

        this.setOrigin(this.getX() + HALF_WIDTH, this.getY() + HALF_HEIGHT);
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    private void updateCollisionBox() {
        collisionBox.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void gone(){
        this.dead = true;
    }

    public Ship getSelf() {
        return self;
    }
}