package com.extremekod.extremespace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gratt on 24.06.2017.
 */

public class Es_Resources {
    public static final Texture grattShipTexture      = new Texture("ship_1.png");
    public static final Texture rerShipTexture        = new Texture("rership.png");
    public static final Texture lightLaserTexture     = new Texture("light_laser.png");
    public static final Texture rocketTexture         = new Texture("rocket.png");
    public static final Texture explosionTexture      = new Texture("explosion.png");
    public static final Texture smallExplosionTexture = new Texture("small_explosion.png");

    public static final Sound explosionSound      = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
    public static final Sound smallExplosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/small_explosion.mp3"));
}
