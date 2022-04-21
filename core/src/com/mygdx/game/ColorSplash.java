package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.Views.MainMenuView;

import java.net.URISyntaxException;

/*import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;*/

public class ColorSplash extends Game {

	public static final int V_WIDTH=360;
	public static final int V_HEIGHT=640;

	public static final String TITLE = "Color Splash";

	private ViewManager gsm;
	public SpriteBatch batch;
	private SockerManager socketManager;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new ViewManager();
		gsm.push(new MainMenuView(gsm));
		socketManager = new SockerManager();
	}

	@Override
	public void render () {
		super.render();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
