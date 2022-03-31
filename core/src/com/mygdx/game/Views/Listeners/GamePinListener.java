package com.mygdx.game.Views.Listeners;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GamePinListener implements Input.TextInputListener{
    private UserNameListener userNameListener = new UserNameListener();

    @Override
    public void input (String pin) {
        if (pin.length() == 4) {
            Gdx.input.getTextInput(userNameListener, "Enter your name:", "", "Name");
            System.out.println(pin);
        }
    }

    @Override
    public void canceled () {
    }
}
