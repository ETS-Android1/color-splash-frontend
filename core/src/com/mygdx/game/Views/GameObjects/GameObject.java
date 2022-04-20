package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GameObject {
    protected Texture image;
    protected TextureRegion animatedImage;
    protected double xPos;
    protected double yPos;
    protected double scale;
    protected int screenWidth;
    protected int screenHeight;
    protected boolean isRendered=false;
    protected Rectangle bounds;
    //protected BitmapFont font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));

    public GameObject(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        this.image = image;
        this.scale=scale;
        this.screenWidth=Gdx.graphics.getWidth();
        this.screenHeight=Gdx.graphics.getHeight();
        this.xPos = xPos*screenWidth;
        this.yPos = yPos*screenHeight;
        this.bounds = new Rectangle((float)this.xPos, (float)(this.yPos), (float)this.getWidth(), (float)this.getHeight());
        if(centerWidth){
            this.xPos = (screenWidth-(this.getWidth()))/2;
        }
        if(centerHeight){
            this.yPos = (screenHeight-(this.getHeight()))/2;
        }
        if(this.xPos>screenWidth/2 && !isRendered && !centerWidth){
            this.xPos-=(this.getWidth());
            this.bounds = new Rectangle((float)this.xPos, (float)(this.yPos), (float)this.getWidth(), (float)this.getHeight());
            this.isRendered=true;
        }
    }

    public Texture getImage() {
        return this.image;
    }

    public double getHeight(){
        return image.getHeight()*this.scale;
    }

    public double getWidth(){
        return image.getWidth()*this.scale;
    }

    public double getXPos() { return xPos; }

    public double getYPos() { return yPos; }

    public void drawStage(TextField field){
        field.setPosition((float) this.xPos, (float) this.yPos, 1);
    }

    public void drawGameObject(SpriteBatch sb) {
        sb.draw(this.image, (float)this.xPos, (float)(this.yPos), (float)(this.getWidth()), (float)(this.getHeight()));
    }

    public void setImage(Texture image) {
        this.image=image;
    }

    public void setFilePath(String filepath){this.image=new Texture(Gdx.files.internal(filepath));}


}
