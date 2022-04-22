package com.mygdx.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Controllers.ErrorController;

public class ErrorDialog {
    private Window.WindowStyle style = new Window.WindowStyle();
    private BitmapFont font;
    private Dialog dialog;
    private com.badlogic.gdx.scenes.scene2d.ui.Button button;
    private Button.ButtonStyle btnStyle = new Button.ButtonStyle();

    public ErrorDialog(String message) {
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        style.titleFont = font;
        style.titleFontColor = Color.BLACK;
        style.background = new Image(new Texture(Gdx.files.internal("dialog_background.png"))).getDrawable();
        btnStyle.down = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        btnStyle.up = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        btnStyle.checked = new Image(new Texture(Gdx.files.internal("button_ok.png"))).getDrawable();
        button = new Button(btnStyle);
        if(message.length()>20){
            message=message.substring(0,24)+"\n   "+message.substring(24);
        }
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
