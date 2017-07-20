package com.extremekod.extremespace.Effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.extremekod.extremespace.Events.Collisions;
import com.extremekod.extremespace.Weapons.Weapon;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by Gratt on 23.06.2017.
 */

public class Explosion extends Actor {

    private Texture texture;

    public static ArrayList<Explosion> explosionsList = new ArrayList<Explosion>();

    public static final float TEXTURE_HEIGHT = 128;
    public static final float TEXTRUE_WIDTH = 128;
    public static final float HALF_HEIGHT = TEXTURE_HEIGHT / 2;
    public static final float HALF_WIDTH = TEXTRUE_WIDTH / 2;

    public Handler handler;

    public Explosion(float x, float y) {
        this.setX(x);
        this.setY(y);
        this.setOrigin(HALF_WIDTH + Weapon.HALF_WIDTH, HALF_HEIGHT + Weapon.HALF_HEIGHT);
        this.setWidth(TEXTURE_HEIGHT);
        this.setHeight(TEXTRUE_WIDTH);

        explosionsList.add(this);

        Collisions.alreadyExploding = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Collisions.alreadyExploding=false;

                    for (Explosion explosion :
                        explosionsList) {
                        explosion.remove();

                    }
                    explosionsList.clear();
                    }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}

