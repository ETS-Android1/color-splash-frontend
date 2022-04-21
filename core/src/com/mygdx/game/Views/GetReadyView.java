package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Models.GameObject;

public class GetReadyView extends View{

    private BitmapFont font;
    private GameObject placeholder;
    private float timer = 0;

    protected GetReadyView(ViewManager vm) {
        super(vm);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        placeholder = new GameObject(new Texture(Gdx.files.internal("splash.png")),0.1,0.6,1,false,false);
        font.getData().setScale(4);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        this.timer+=dt;
        if(this.timer>=2){
            dispose();
            vm.set(new SplashView(vm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        font.draw(sb, "Get \nReady...",(float)placeholder.getXPos(),(float)placeholder.getYPos());
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
