package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Views.GameObjects.Dots;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.GameObjects.Splash;

import java.util.Arrays;
import java.util.List;

public class SplashView extends View{
    private Splash splash;
    private float splashTimer = 0;
    private float colorTimer = 0;
    private int colorCounter = 0;
    private int frameCounter = 0;
    //list for testing, should be replaced with real backend data
    private List<Integer> backend = Arrays.asList(0,1,2,3,0,1);

    private Dots dots;

    protected SplashView(ViewManager vm) {
        super(vm);
        splash = new Splash(new Texture(Gdx.files.internal("splash_1_blue.png")),0.5,0.6,3,true,true);
        this.splash.setFilePath(splash.getSplashes().get(backend.get(this.colorCounter)).get(this.frameCounter));
        this.dots = new Dots();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        this.splashTimer+=dt;
        this.colorTimer+=dt;

        if (this.splashTimer>0.05 && this.frameCounter<4){
            this.frameCounter++;
            this.splash.setFilePath(splash.getSplashes().get(backend.get(this.colorCounter)).get(this.frameCounter));
            this.splashTimer=0;
        }
        if (colorTimer>3 && this.colorCounter<(backend.size()-1)){
            this.dots.setDarkGreyDot(colorCounter + 1);
            this.colorCounter++;
            this.colorTimer=0;
            this.frameCounter=0;
        }
        if (colorTimer>3 && this.colorCounter==(backend.size()-1)){
            vm.set(new GameLobbyView(vm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        splash.drawGameObject(sb);
        dots.drawDots(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}

