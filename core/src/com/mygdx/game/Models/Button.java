package com.mygdx.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Button extends GameObject{

    private boolean checked = false;
    private Music clickSound2;
    private Sound clickSound;


    public Button(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }

    public boolean isObjectClicked() {
        int clickedX = Gdx.input.getX();
        int clickedY = this.screenHeight-Gdx.input.getY();

        if (this.bounds.contains(clickedX,clickedY)) {
            this.clickSound.play();
            return true;
        }
        return false;

    }

    public void setChecked(boolean bool) {
        this.checked = bool;
        if (checked) {
            this.setImage(new Texture(Gdx.files.internal("button_selected.png")));
        }
        else {
            this.setImage(new Texture(Gdx.files.internal("button_unselected.png")));
        }

    }

    public boolean getIsChecked() {
        return this.checked;
    }


}
