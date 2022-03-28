package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
    protected Texture image;
    protected double xPos;
    protected double yPos;
    protected double scale;
    protected int screenWidth;
    protected int screenHeight;
    protected boolean isRendered=false;
    protected boolean senterHeight=false;
    protected boolean senterWidth=false;
    //protected BitmapFont font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));

    public GameObject(Texture image, double xPos, double yPos, double scale, boolean senterHeight, boolean senterWidth) {
        this.image = image;
        this.scale=scale;
        this.screenWidth=Gdx.graphics.getWidth();
        this.screenHeight=Gdx.graphics.getHeight();
        this.xPos = xPos*screenWidth;
        this.yPos = yPos*screenHeight;
        this.senterHeight=senterHeight;
        this.senterWidth= senterWidth;
    }

    public Texture getImage() {
        return this.image;
    }

    public int getHeight(){
        return image.getHeight();
    }

    public int getWidth(){
        return image.getWidth();
    }

    public void drawGameObject(SpriteBatch sb) {
        if(this.xPos>screenWidth/2 && !isRendered && !senterWidth){
            this.xPos-=(this.getWidth()*scale);
            this.isRendered=true;
        }
        if(senterWidth){
            this.xPos = (screenWidth-(this.getWidth()*scale))/2;
        }
        if(senterHeight){
            this.yPos = (screenHeight-(this.getHeight()*scale))/2;
        }
        sb.draw(this.image, (float)this.xPos, (float)(this.yPos), (float)(this.getWidth()*scale), (float)(this.getHeight()*scale));
    }

}
