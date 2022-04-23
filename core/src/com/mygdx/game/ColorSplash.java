package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.Views.MainMenuView;

public class ColorSplash extends Game {

	public static final int V_WIDTH=360;
	public static final int V_HEIGHT=640;

	public static final String TITLE = "Color Splash";

	private ViewManager viewManager;
	private Music music;

	public SpriteBatch batch;
	public static final SocketManager socketManager = new SocketManager();
	public ErrorHandler errorHandler;


	@Override
	public void create () {
		batch = new SpriteBatch();
		viewManager = new ViewManager();
		errorHandler = new ErrorHandler(viewManager);
		viewManager.push(new MainMenuView(viewManager));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void render () {
		super.render();
		viewManager.update(Gdx.graphics.getDeltaTime());
		viewManager.render(batch);
	}
}
