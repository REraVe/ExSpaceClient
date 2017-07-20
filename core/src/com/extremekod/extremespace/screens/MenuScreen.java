package com.extremekod.extremespace.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.extremekod.extremespace.extremeSpace;


/**
 * Created by Gratt on 18.06.2017.
 */

public class MenuScreen implements Screen {

    public static TextureAtlas buttonAtlas;

    public Game game;
    private Image background;
    private Stage stage;
    private Skin skin;

    private Button button;
    private Button.ButtonStyle buttonStyle;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        background = new Image(new Texture("menu_back.jpg"));
        stage.addActor(background);

        init_buttons();
    }

    private void init_buttons() {
        skin = new Skin();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/es_buttons.pack"));
        skin.addRegions(buttonAtlas);

        buttonStyle = new TextButton.TextButtonStyle();

        buttonStyle.up   = skin.getDrawable("startBtn");
        buttonStyle.down = skin.getDrawable("startBtn_pressed");

        button = new Button(buttonStyle);
        button.setPosition(extremeSpace.width /2 - 150,extremeSpace.height /2 - 50);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game));
            }
        });

        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        stage.draw();
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
}

