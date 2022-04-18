package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splash extends GameObject {

    private Texture texture;
    private Array<TextureRegion> frames;
    private int frameWidth;
    private int frameCount = 0;
    private int maxFrames = 5;
    private List<String> blue_splash = Arrays.asList("splash_1_blue.png", "splash_2_blue.png","splash_3_blue.png","splash_4_blue.png", "splash_5_blue.png");
    private List<String> red_splash = Arrays.asList("splash_1_red.png", "splash_2_red.png","splash_3_red.png","splash_4_red.png", "splash_5_red.png");
    private List<String> green_splash = Arrays.asList("splash_1_green.png", "splash_2_green.png","splash_3_green.png","splash_4_green.png", "splash_5_green.png");
    private List<String> yellow_splash = Arrays.asList("splash_1_yellow.png", "splash_2_yellow.png","splash_3_yellow.png","splash_4_yellow.png", "splash_5_yellow.png");
    private Map<Integer, List<String>> splashes = new HashMap<Integer, List<String>>();

    public Splash(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        splashes.put(0,blue_splash);
        splashes.put(1, red_splash);
        splashes.put(2, green_splash);
        splashes.put(3, yellow_splash);
        texture = image;
        //this.setImage(new Texture(Gdx.files.internal("splash_blue.png")));
        frames = new Array<TextureRegion>();
        frameWidth = texture.getWidth()/this.maxFrames;
        for (int i=0; i<this.maxFrames; i++){
            frames.add(new TextureRegion(texture, i*frameWidth, 0, frameWidth, texture.getHeight()));
        }
        this.setTexture();
    }

    public Map<Integer, List<String>> getSplashes(){
        return this.splashes;
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
