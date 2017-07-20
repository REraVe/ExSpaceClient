package com.extremekod.extremespace.Weapons.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.extremekod.extremespace.Es_Resources;
import com.extremekod.extremespace.Weapons.Weapon;
import com.extremekod.extremespace.ships.Ship;

import java.util.ArrayList;

/**
 * Created by Gratt on 23.06.2017.
 */

public class LightLaser extends Weapon {

    private final float SPEED = 8;

    public static ArrayList<LightLaser> lasersList = new ArrayList<LightLaser>();

    private Texture texture;
    private boolean dead = false;

    public LightLaser(float x, float y, boolean target, Ship self) {
        super(x, y, target, self);

        this.texture = Es_Resources.lightLaserTexture;
        lasersList.add(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha, SPEED);

        if (!super.dead) {
            batch.draw(texture, this.getX(), this.getY());
        }
    }

}
