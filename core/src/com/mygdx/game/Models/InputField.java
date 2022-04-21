package com.mygdx.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class InputField extends GameObject {

    protected TextField textField;
    private TextField.TextFieldStyle style = new TextField.TextFieldStyle();
    private Label.LabelStyle cursor = new Label.LabelStyle();
    private Pixmap cursorColor;
    private Actor actor;

    public InputField(String title, Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        style.font=new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        style.fontColor=Color.BLACK;
        style.background=new Image(image).getDrawable();
        cursor.font=new BitmapFont();
        setCursor(cursor);
        style.cursor=new Image(new Texture(cursorColor)).getDrawable();

        this.textField = new TextField("", style);
        this.textField.setMessageText(title);
        this.textField.setWidth((float) (Gdx.graphics.getWidth()*0.5));
        this.textField.setHeight((float) (Gdx.graphics.getHeight()*0.07));
        this.textField.setAlignment(1);
    }

    private void setCursor(Label.LabelStyle cursorStyle){
        Label label = new Label("|", cursorStyle);
        cursorColor = new Pixmap((int)label.getWidth(),(int)label.getHeight(), Pixmap.Format.RGB888);
        cursorColor.setColor(Color.BLACK);
        cursorColor.fill();
    }

    public TextField getTextField(){
        return this.textField;
    }
}
