package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

import io.socket.emitter.Emitter;

public class ErrorHandler {

    protected ViewManager viewManager;

    public ErrorHandler(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.startErrorListener();
        this.createGameDeletedListener();
    }

    public void startErrorListener() {
        ColorSplash.socketManager.createListener(EventsConstants.error, errorListener());
    }

    public Emitter.Listener errorListener() {
        return new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Gdx.app.postRunnable (new Runnable() {
                    @Override
                    public void run() {
                        viewManager.peek().setError("ERROR: " + args[0]);
                    }
                });
            }
        };
    }

    public void createGameDeletedListener() {
        ColorSplash.socketManager.createListener(EventsConstants.gameDeleted, gameDeletedListener());
    }

    public Emitter.Listener gameDeletedListener() {
        return new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Gdx.app.postRunnable (new Runnable() {
                    @Override
                    public void run() {
                        viewManager.set(new MainMenuView(viewManager));
                        viewManager.peek().setError("ERROR: " + args[0]);
                    }
                });
            }
        };
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
