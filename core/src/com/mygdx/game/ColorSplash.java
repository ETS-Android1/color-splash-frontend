package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.views.ViewManager;
import com.mygdx.game.views.MainMenuView;

public class ColorSplash extends Game {

	private ViewManager viewManager;

	public SpriteBatch batch;
	public static final SocketManager socketManager = SocketManager.getInstance();
	public ErrorHandler errorHandler;


	@Override
	public void create () {
		batch = new SpriteBatch();
		viewManager = new ViewManager();
		errorHandler = new ErrorHandler(viewManager);
		viewManager.push(new MainMenuView(viewManager));
	}

	@Override
	public void render () {
		super.render();
		viewManager.update(Gdx.graphics.getDeltaTime());
		viewManager.render(batch);
	}

}
