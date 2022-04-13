package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.GameObjects.Splash;

public class SplashView extends View{
    private Splash blueSplash;
    private Splash greenSplash;
    private Splash redSplash;
    private Splash yellowSplash;

    protected SplashView(ViewManager vm) {
        super(vm);
        blueSplash = new Splash(new Texture(Gdx.files.internal("splashanimation_blue.png")),1,1,3,true,true);
        greenSplash = new Splash(new Texture(Gdx.files.internal("splashanimation_green.png")),1,1,3,true,true);
        redSplash = new Splash(new Texture(Gdx.files.internal("splashanimation_red.png")),1,1,3,true,true);
        yellowSplash = new Splash(new Texture(Gdx.files.internal("splashanimation_yellow.png")),1,1,3,true,true);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        blueSplash.setFrameCount();
    }

    @Override
    public void render(SpriteBatch sb) {
        System.out.println("FRAMECOUNT");
        System.out.println(blueSplash.getFrameCount());
        super.render(sb);
        //sb.draw(blueSplash.getTexture(), (float) blueSplash.getXPos(), (float) blueSplash.getYPos());
        //blueSplash.drawGameObject(sb);
        blueSplash.drawAnimation(sb);
        if(blueSplash.getFrameCount()>=3){
            blueSplash.drawGameObject(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}

