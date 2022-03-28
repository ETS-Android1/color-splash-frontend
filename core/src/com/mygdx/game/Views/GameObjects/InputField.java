package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InputField extends GameObject{

    protected TextField textField;
    private TextField.TextFieldStyle style = new TextField.TextFieldStyle();

    public InputField(String title, Texture image, double xPos, double yPos, double scale, boolean senterHeight, boolean senterWidth) {
        super(image, xPos, yPos, scale, senterHeight, senterWidth);
        style.font=new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        style.background=new TextureRegionDrawable(image);
        style.fontColor=new Color(0);
        style.messageFontColor=new Color(255,255,255,100);
        this.textField = new TextField(title, style);
        this.textField.setMessageText(title);
    }
}
