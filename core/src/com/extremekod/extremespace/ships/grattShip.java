package com.extremekod.extremespace.ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.extremekod.extremespace.Es_Resources;
import com.extremekod.extremespace.Weapons.Lasers.LightLaser;
import com.extremekod.extremespace.Weapons.Rockets.Rocket;


public class grattShip extends Ship {

    private TextureRegion texture;

    public grattShip(int x, int y, boolean target) {
        super(x, y, target);

        this.texture = new TextureRegion(Es_Resources.grattShipTexture);
    }

    public void fireLightLaser (Stage stage){
        stage.addActor(new LightLaser(this.getX() + this.getWidth(), this.getY() + CENTRAL_FIRE_POINT, target, this));
    }

    public void fireRocket (Stage stage){
        stage.addActor(new Rocket(this.getX() + this.getWidth(), this.getY() + CENTRAL_FIRE_POINT, target, this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), 1, 1, this.getRotation());
    }
}
