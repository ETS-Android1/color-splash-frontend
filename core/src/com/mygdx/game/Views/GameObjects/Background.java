package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ColorSplash;

public class Background extends GameObject{

    public Background(Texture image, double xPos, double yPos, double scale, boolean senterHeight, boolean senterWidth ) {
        super(image, xPos, yPos, scale, senterHeight, senterWidth);

    }

    public void drawBackground(SpriteBatch sb) {
        sb.draw(this.image, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
