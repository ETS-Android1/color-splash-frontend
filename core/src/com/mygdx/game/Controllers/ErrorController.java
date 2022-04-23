package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.ViewManager;

import io.socket.emitter.Emitter;

public class ErrorController {

    protected ViewManager viewManager;

    public ErrorController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.startErrorListener();
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

    public ViewManager getViewManager() {
        return viewManager;
    }
}
