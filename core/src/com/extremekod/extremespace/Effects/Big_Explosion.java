package com.extremekod.extremespace.Effects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.extremekod.extremespace.Es_Resources;

/**
 * Created by Gratt on 23.06.2017.
 */

public class Big_Explosion extends Explosion {

    private Texture texture = Es_Resources.explosionTexture;

    public Big_Explosion(float x, float y) {
        super(x,y);

        Sound sound = Es_Resources.explosionSound;
        sound.play();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,this.getX()-getOriginX(),this.getY()-getOriginY());
    }

}

