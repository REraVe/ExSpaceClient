package com.extremekod.extremespace.Effects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.extremekod.extremespace.Es_Resources;

/**
 * Created by Gratt on 23.06.2017.
 */

public class Small_Explosion extends Explosion {
    private Texture texture = Es_Resources.smallExplosionTexture;

    public Small_Explosion(float x, float y) {
        super(x,y);

        Sound sound = Es_Resources.smallExplosionSound;
        sound.play();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,this.getX()-getOriginX(),this.getY()-getOriginY());
    }

}
