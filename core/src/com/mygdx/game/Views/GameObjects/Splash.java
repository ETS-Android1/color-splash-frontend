package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Splash extends GameObject {

    private Texture texture;
    private Array<TextureRegion> frames;
    private int frameWidth;
    private int frameCount = 0;
    private int maxFrames = 4;

    public Splash(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        texture = image;
        this.setImage(new Texture(Gdx.files.internal("splash.png")));
        frames = new Array<TextureRegion>();
        frameWidth = image.getWidth()/this.maxFrames;
        for (int i=0; i<this.maxFrames; i++){
            frames.add(new TextureRegion(texture, i*frameWidth, 0, frameWidth, texture.getHeight()));
        }
        this.setTexture();
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(){
        if(this.frameCount<this.maxFrames-1) {
            this.frameCount++;
        }
        this.setTexture();
    }

    public TextureRegion getTexture(){
        return this.frames.get(frameCount);
    }

    private void setTexture(){
        this.setTextureRegion(this.frames.get(frameCount));
    }
}
