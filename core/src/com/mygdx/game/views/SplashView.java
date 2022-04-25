package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controllers.SplashController;
import com.mygdx.game.views.gameObjects.Dots;
import com.mygdx.game.views.gameObjects.GameObject;
import com.mygdx.game.views.gameObjects.Splash;
import com.mygdx.game.models.DisplayColors;

import java.util.List;

public class SplashView extends View {
    private final Splash splash;
    private final GameObject textPlaceholder;
    private final BitmapFont font;
    private final String roundsString;
    private final Sound splashSound;

    private final Dots dots;

    private final SplashController controller;

    public SplashView(ViewManager vm, DisplayColors colorInfo) {
        super();

        this.controller = new SplashController(vm, colorInfo);
        int round = controller.getColorInfo().getRound();
        int totalRounds = controller.getColorInfo().getMaxRounds();
        this.roundsString = "Round "+ round +"/"+ totalRounds;
        this.splashSound = Gdx.audio.newSound(Gdx.files.internal("splash.mp3"));
        this.dots = new Dots(this.controller.getColorList());
        textPlaceholder = new GameObject(new Texture(Gdx.files.internal("splash.png")),0.33,0.1,1,false,false);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        font.getData().setScale((float)1.5);
        splash = new Splash(new Texture(Gdx.files.internal("splash_1_blue.png")),0.5,0.6,3,true,true);
        this.splash.setFilePath(splash.getSplashes().get(this.controller.getColorList().get(this.controller.getColorCounter())).get(this.controller.getFrameCounter()));
    }

    @Override
    public void update(float dt) {

        if (this.controller.playSound(dt)) {
            this.splashSound.play();
        }

        List<Integer> updateSplash = this.controller.updateSplash();
        if (updateSplash.size() > 0) {
            this.splash.setFilePath(splash.getSplashes().get(updateSplash.get(0)).get(updateSplash.get(1)));

        }

        int color = this.controller.changeDotColor();
        if (color >= 0) {
            this.dots.setDarkGreyDot(color);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        splash.drawGameObject(sb);
        dots.drawDots(sb);
        font.draw(sb, roundsString, (float)textPlaceholder.getXPos(), (float)textPlaceholder.getYPos());
        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        for (GameObject dot : this.dots.getDots()) {
            dot.getImage().dispose();
        }
        this.font.dispose();
        this.splash.getImage().dispose();
        this.textPlaceholder.getImage().dispose();
        this.splashSound.dispose();
    }
}

