package com.extremekod.extremespace.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.extremekod.extremespace.Events.Collisions;
import com.extremekod.extremespace.NetworkClient;
import com.extremekod.extremespace.Player;
import com.extremekod.extremespace.extremeSpace;
import com.extremekod.extremespace.ships.Ship;
import com.extremekod.extremespace.ships.grattShip;
import com.extremekod.extremespace.ships.rerShip;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gratt on 19.06.2017.
 */

public class PlayScreen implements Screen {

    private static final String EMPTY_MESSAGE = "-1";
    private static ArrayList<String> eventsList = new ArrayList<String>();

    private Game game;
    private Stage stage;
    private Image background;
    private Skin fireBtnSkin;

    private Player player1;
    private Player player2;

    private Button btnFireLaserP1;
    private Button btnFireRocketP1;
    private Button btnFireLaserP2;
    private Button btnFireRocketP2;
    private Button.ButtonStyle buttonStyle;

    private grattShip grattShip;
    private rerShip rerShip;

    public PlayScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        player1 = new Player(Player.PLAYER_ONE);
        player2 = new Player(Player.PLAYER_TWO);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        init_background();
        init_buttons();

        // Left ship
        grattShip = new grattShip(30, extremeSpace.height / 2, true);
        stage.addActor(grattShip);

        // Right ship
        rerShip = new rerShip(700, extremeSpace.height / 2 + 50, false);
        rerShip.rotateBy(180f);
        stage.addActor(rerShip);

        player1.setShip(grattShip);
        player2.setShip(rerShip);

        runBackgroundThread();
    }

    private void init_background() {
        background = new Image(new Texture("playstate_back.jpg"));
        stage.addActor(background);
    }

    private void init_buttons() {
        fireBtnSkin = new Skin();
        fireBtnSkin.addRegions(MenuScreen.buttonAtlas );

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up   = fireBtnSkin.getDrawable("fireBtn");
        buttonStyle.down = fireBtnSkin.getDrawable("fireBtn_pressed");

        switch (Player.getMyNetworkPlayerNumber()) {
            case Player.PLAYER_ONE:
                addButtonsForPlayerOne();
                break;

            case Player.PLAYER_TWO:
                addButtonsForPlayerTwo();
                break;

            default:
                addButtonsForPlayerOne();
                addButtonsForPlayerTwo();
        }
    }

    private void addButtonsForPlayerOne() {
        btnFireLaserP1 = new Button(buttonStyle);
        btnFireLaserP1.setPosition(extremeSpace.width * 0.17f, extremeSpace.height * 0.01f);
        btnFireLaserP1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                grattShip.isFireLightLaser = true;
                grattShip.fireLightLaser(stage);
            }
        });
        btnFireLaserP1.setSize(128, 128);

        btnFireRocketP1 = new Button(buttonStyle);
        btnFireRocketP1.setPosition(extremeSpace.width * 0.29f, extremeSpace.height * 0.01f);
        btnFireRocketP1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                grattShip.isFireRocket = true;
                grattShip.fireRocket(stage);
            }
        });
        btnFireRocketP1.setSize(128, 128);

        stage.addActor(btnFireLaserP1);
        stage.addActor(btnFireRocketP1);
    }

    private void addButtonsForPlayerTwo() {
        btnFireLaserP2 = new Button(buttonStyle);
        btnFireLaserP2.setPosition(extremeSpace.width * 0.56f, extremeSpace.height * 0.01f);
        btnFireLaserP2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rerShip.isFireLightLaser = true;
                rerShip.fireLightLaser(stage);
            }
        });
        btnFireLaserP2.setSize(128, 128);

        btnFireRocketP2 = new Button(buttonStyle);
        btnFireRocketP2.setPosition(extremeSpace.width * 0.68f, extremeSpace.height * 0.01f);
        btnFireRocketP2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rerShip.isFireRocket = true;
                rerShip.fireRocket(stage);
            }
        });
        btnFireRocketP2.setSize(128, 128);

        stage.addActor(btnFireLaserP2);
        stage.addActor(btnFireRocketP2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        processNetworkMessages();

        stage.draw();

        Collisions.collisionsDetector(stage);

        controls_P1P2();
    }

    private void controls_P1P2() {
        switch (Player.getMyNetworkPlayerNumber()) {
            case Player.PLAYER_ONE:
                if (Gdx.input.isTouched() && Gdx.input.getX() < extremeSpace.width * 0.2f) {
                    handleInput(grattShip);
                }
                break;

            case Player.PLAYER_TWO:
                if (Gdx.input.isTouched() && Gdx.input.getX() > extremeSpace.width * 0.8f) {
                    handleInput(rerShip);
                }
                break;

            default:
                if (Gdx.input.isTouched() && Gdx.input.getX() < extremeSpace.width * 0.2f) {
                    handleInput(grattShip);
                }

                if (Gdx.input.isTouched() && Gdx.input.getX() > extremeSpace.width * 0.8f) {
                    handleInput(rerShip);
                }
        }
    }

    private void handleInput(Ship ship) {
        float touchY     = extremeSpace.height - Gdx.input.getY();
        float shipCenter = ship.getY() + Ship.HALF_HEIGHT;

        if (Gdx.input.isTouched()) {
            if(touchY  <  shipCenter)
                ship.moveDown(2);
            else if(touchY  > shipCenter)
                ship.moveUp(2);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void runBackgroundThread() {
        if (!NetworkClient.MULTIPLAYER)
            return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);

                        Player ownerPlayer = Player.getMyNetworkPlayer();

                        if (ownerPlayer == null)
                            return;

                        Ship ownerShip = Player.getMyNetworkPlayer().getShip();

                        NetworkClient networkClient = NetworkClient.createNetworkClient();

                        if (networkClient != null)
                            return;

                        JSONObject jsonToSend = new JSONObject();
                        jsonToSend.put("networkSessionNumber", NetworkClient.getNetworkSessionNumber());
                        jsonToSend.put("networkPlayerNumber",  NetworkClient.getNetworkPlayerNumber());

                        jsonToSend.put("shipX", ownerShip.getX());
                        jsonToSend.put("shipY", ownerShip.getY());

                        jsonToSend.put("fireLightLaser", ownerShip.isFireLightLaser);
                        jsonToSend.put("fireRocket",     ownerShip.isFireRocket);

                        String result = networkClient.sendMessage(jsonToSend.toString());

                        ownerShip.isFireLightLaser = false;
                        ownerShip.isFireRocket     = false;

                        if (!result.equals(EMPTY_MESSAGE)) {
                            JSONObject jsonAnswer = new JSONObject(result);

                            if (jsonAnswer.getInt("networkSessionNumber") == NetworkClient.getNetworkSessionNumber()
                                    && jsonAnswer.getInt("networkPlayerNumber") != NetworkClient.getNetworkPlayerNumber()) {

                                int playerNumber = jsonAnswer.getInt("networkPlayerNumber");
                                Player player = Player.getPlayersMap().get(playerNumber);

                                Ship enemyShip = player.getShip();

                                enemyShip.setX(jsonAnswer.getInt("shipX"));
                                enemyShip.setY(jsonAnswer.getInt("shipY"));

                                if (jsonAnswer.getBoolean("fireLightLaser")) {
                                    enemyShip.fireLightLaser(stage);
                                }

                                if (jsonAnswer.getBoolean("fireRocket")) {
                                    enemyShip.fireRocket(stage);
                                }

                            }
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void processNetworkMessages() {
        if (eventsList.size() == 0)
            return;

        eventsList.clear();
    }

}
