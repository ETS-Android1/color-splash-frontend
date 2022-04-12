package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.awt.Rectangle;

public class Button extends GameObject{

    private com.badlogic.gdx.scenes.scene2d.ui.Button button;
    private com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle style = new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle();

    //protected Vector3 bounds;

    public Button(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        style.checked = new Image(image).getDrawable();
        style.checkedDown = new Image(new Texture(Gdx.files.internal("button_selected.png"))).getDrawable();
        button = new com.badlogic.gdx.scenes.scene2d.ui.Button(style);


        //position = new Vector2(xPos, yPos);
        //bounds = new Rectangle(xPos, yPos, image.getWidth(), image.getHeight());
    }

    public com.badlogic.gdx.scenes.scene2d.ui.Button getButton() {
        return this.button;
    }

    public boolean isObjectClicked() {
        int clickedX = Gdx.input.getX();
        int clickedY = this.screenHeight-Gdx.input.getY();

        if (this.bounds.contains(clickedX,clickedY)) {
            return true;
        }
        return false;

    }


}
