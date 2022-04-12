package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.Views.MainMenuView;

import java.net.URISyntaxException;

import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class ColorSplash extends Game {

	public static final int V_WIDTH=360;
	public static final int V_HEIGHT=640;

	private Socket socket;
	public static final String TITLE = "Color Splash";

	private ViewManager gsm;
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new ViewManager();
		gsm.push(new MainMenuView(gsm));
		try {
			socket = IO.socket("https://color-splash.herokuapp.com");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					System.out.println("Hello world");
				}
			});
			System.out.println("trying to connect");
			socket.connect();
			System.out.println(socket.connected());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render () {
		super.render();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
