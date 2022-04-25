package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.GetReadyController;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.dataClasses.DisplayColors;

public class GetReadyView extends View{

    private final BitmapFont font;
    private final GameObject placeholder;
    private final GameObject background;
    private final GetReadyController controller;

    public GetReadyView(ViewManager vm, DisplayColors colorInfo) {
        super();
        controller = new GetReadyController(vm, colorInfo);
        background = new GameObject(new Texture(Gdx.files.internal("splash_orange.png")),1,0.05,5.3,false,true);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        placeholder = new GameObject(new Texture(Gdx.files.internal("splash.png")),0.1,0.6,1,false,false);
        font.getData().setScale(4);
    }

    @Override
    public void update(float dt) {
        this.controller.addToTimer(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        background.drawGameObject(sb);
        font.draw(sb, "Get \nReady...",(float)placeholder.getXPos(),(float)placeholder.getYPos());
        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.getImage().dispose();
        placeholder.getImage().dispose();
        font.dispose();
    }
}
