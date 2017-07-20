package com.extremekod.extremespace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.extremekod.extremespace.screens.MenuScreen;

public class extremeSpace extends Game {

    Game game;

	public static int width = 1024;
	public static int height = 576;

	public static final String TITLE = "extremeSpace";

    public extremeSpace() {
        game = this;
    }


	@Override
	public void create () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        game.setScreen(new MenuScreen(game));
	}

	@Override
	public void render () {
        clearWhite();
        super.render();

    }

    private void clearWhite() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
	public void dispose () {

	}


}
