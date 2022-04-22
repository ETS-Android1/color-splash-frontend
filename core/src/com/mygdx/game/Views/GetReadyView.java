package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.GetReadyController;
import com.mygdx.game.Models.GameObject;

public class GetReadyView extends View{

    private BitmapFont font;
    private GameObject placeholder;
    private GameObject background;
    private float timer = 0;
    private GetReadyController controller;

    public GetReadyView(ViewManager vm) {
        super();
        controller = new GetReadyController(vm);
        background = new GameObject(new Texture(Gdx.files.internal("splash_orange.png")),1,0.15,5.3,false,true);
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
            controller.setSplashView();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        background.drawGameObject(sb);
        font.draw(sb, "Get \nReady...",(float)placeholder.getXPos(),(float)placeholder.getYPos());
        sb.end();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
    }
}
