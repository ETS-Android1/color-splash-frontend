package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;

public class Button extends GameObject{

    protected Texture image;
    protected Rectangle bounds;

    public Button(Texture image, double xPos, double yPos, double scale, boolean senterHeight, boolean senterWidth) {
        super(image, xPos, yPos, scale, senterHeight, senterWidth);
        //position = new Vector2(xPos, yPos);
        //bounds = new Rectangle(xPos, yPos, image.getWidth(), image.getHeight());
    }

    public boolean isObjectClicked() {
        int clickedX = Gdx.input.getX();
        int clickedY = Gdx.input.getY();

        //Checks if click inside x-position of image
        if (clickedX >= xPos && clickedX <= xPos + image.getWidth()) {
            //Checks if click inside y-position of image
            return clickedY >= yPos - image.getHeight() && clickedY <= yPos;
        }
        return false;
    }


}
