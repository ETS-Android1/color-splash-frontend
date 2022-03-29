package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

public class Button extends GameObject{

    //protected Vector3 bounds;

    public Button(Texture image, double xPos, double yPos, double scale, boolean senterHeight, boolean senterWidth) {
        super(image, xPos, yPos, scale, senterHeight, senterWidth);


        //position = new Vector2(xPos, yPos);
        //bounds = new Rectangle(xPos, yPos, image.getWidth(), image.getHeight());
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
