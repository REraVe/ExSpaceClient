package com.extremekod.extremespace.Events;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.extremekod.extremespace.Effects.Big_Explosion;
import com.extremekod.extremespace.Effects.Small_Explosion;
import com.extremekod.extremespace.Weapons.Lasers.LightLaser;
import com.extremekod.extremespace.Weapons.Rockets.Rocket;
import com.extremekod.extremespace.ships.Ship;

/**
 * Created by Gratt on 23.06.2017.
 */

public class Collisions {

    public static boolean alreadyExploding;

    public static void collisionsDetector(Stage stage){
        laserCollision(stage);
        rocketCollision(stage);
    }

    private static void laserCollision(Stage stage){
        if (alreadyExploding)
            return;

        if (LightLaser.lasersList == null || Ship.ships == null)
            return;

        for (Ship ship : Ship.ships) {
            for (LightLaser laser : LightLaser.lasersList) {

                if (ship.getCollisionBox().overlaps(laser.getCollisionBox()) && !ship.equals(laser.getSelf())) {
                    stage.addActor(new Small_Explosion(laser.getX(), laser.getY()));
                    laser.gone();

                    // send_to_server("doMiniExplosionEnemy");
                }
            }
        }

    }

    private static void rocketCollision(Stage stage) {
        if (alreadyExploding)
            return;

        if (LightLaser.lasersList == null || Ship.ships == null)
            return;

        for (Ship ship : Ship.ships) {
            for (Rocket rocket : Rocket.rocketsList) {
                if (ship.getCollisionBox().overlaps(rocket.getCollisionBox()) && !ship.equals(rocket.getSelf())) {
                    stage.addActor(new Big_Explosion(rocket.getX(), rocket.getY()));
                    rocket.gone();

                    // send_to_server("doExplosionEnemy");
                }

            }
        }

    }

}
