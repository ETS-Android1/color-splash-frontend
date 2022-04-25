package com.mygdx.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class ErrorDialog {
    private final Dialog dialog;
    private final Button button;

    public ErrorDialog(String message) {
        Window.WindowStyle style = new Window.WindowStyle();
        Button.ButtonStyle btnStyle = new Button.ButtonStyle();
        style.titleFont = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        style.titleFontColor = Color.BLACK;
        style.background = new Image(new Texture(Gdx.files.internal("dialog_background.png"))).getDrawable();
        btnStyle.down = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        btnStyle.up = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        btnStyle.checked = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        button = new Button(btnStyle);
        dialog = new Dialog("\n\n\n   "+message, style);
        dialog.button(button);
    }

    public Button getButton() {
        return button;
    }

    public Dialog getDialog() {
        return dialog;
    }

}
