package com.mygdx.game.Views.Listeners;

import com.badlogic.gdx.Input;

public class CreateGameListener implements Input.TextInputListener{
    @Override
    public void input(String text) {
        System.out.println("hei");
    }

    @Override
    public void canceled() {

    }
}
