package com.mygdx.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Views.ViewManager;

public class Button extends GameObject{

    private boolean checked = false;
    private Sound clickSound;
    private ViewManager vm;

    public Button(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth, ViewManager vm) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        this.vm=vm;
    }

    public boolean isObjectClicked() {
        int clickedX = Gdx.input.getX();
        int clickedY = this.screenHeight-Gdx.input.getY();

        if (this.bounds.contains(clickedX,clickedY)) {
            if(this.vm.isSound()){
                this.clickSound.play();
            }
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
