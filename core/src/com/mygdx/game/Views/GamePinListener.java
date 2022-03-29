package com.mygdx.game.Views;
import com.badlogic.gdx.Input;

public class GamePinListener implements Input.TextInputListener{

    @Override
    public void input (String pin) {
        if (pin.length() == 4) {
            System.out.println(pin);
        }
    }

    @Override
    public void canceled () {
    }
}
