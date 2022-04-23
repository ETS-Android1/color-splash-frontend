package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Models.InputField;
import com.mygdx.game.Controllers.CreateGameController;


public class CreateGameView extends View{

    private Button createButton;
    private Button cancelButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private GameObject avatar;
    private BitmapFont font;
    private InputField nickname;
    private String checkedRoundButton = "3";
    private String checkedDifficultyButton = "easy";
    private CreateGameController controller;

    public CreateGameView(ViewManager vm) {
        super();
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        controller = new CreateGameController(vm);
        createButton = new Button(new Texture("button_create.png"), 0.92, 0.08, 3,false, false, vm);
        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.08, 3,false,false, vm);
        threeButton = new Button(new Texture("button_unselected.png"), 0.2, 0.5, 3,false,false, vm);
        fourButton = new Button(new Texture("button_unselected.png"), 0.2, 0.45, 3,false,false, vm);
        fiveButton = new Button(new Texture("button_unselected.png"), 0.2, 0.4, 3,false,false, vm);
        easyButton = new Button(new Texture("button_unselected.png"), 0.6, 0.5, 3,false,false, vm);
        mediumButton = new Button(new Texture("button_unselected.png"), 0.6, 0.45, 3,false,false, vm);
        hardButton = new Button(new Texture("button_unselected.png"), 0.6, 0.4, 3,false,false, vm);
        avatar = new GameObject(new Texture("avatar_orange.png"), 0.08, 0.7, 1,false,false);
        nickname = new InputField("Nickname", new Texture(Gdx.files.internal("textfield_light.png")), 0.95,0.75,2,false,false);
        nickname.getTextField().setMaxLength(12);
        stage.addActor(nickname.getTextField());
        threeButton.setChecked(true);
        easyButton.setChecked(true);
    }

    @Override
    protected void handleInput() {
        super.handleInput();
        if (Gdx.input.justTouched()) {
            if(this.createButton.isObjectClicked() && this.nickname.getTextField().getText()==""){
                setError("Please choose a nickname");
            }
            if (this.createButton.isObjectClicked() && this.nickname.getTextField().getText()!="") {
                int rounds;
                if (this.threeButton.getIsChecked()) {
                    rounds = 3;
                } else if (this.fourButton.getIsChecked()) {
                    rounds = 4;
                } else {
                    rounds = 5;
                }
                String difficulty;
                if (this.easyButton.getIsChecked()) {
                    difficulty = "easy";
                } else if (this.mediumButton.getIsChecked()) {
                    difficulty = "medium";
                } else {
                    difficulty = "hard";
                }
                this.controller.createGame(this.nickname.getTextField().getText(), rounds, difficulty, 4);
                dispose();
                controller.setGameLobbyView();
            }
            if (this.cancelButton.isObjectClicked()) {
                dispose();
                controller.setMainMenuView();
            }
            if (this.easyButton.isObjectClicked()) {
                this.checkedDifficultyButton = "easy";
                this.easyButton.setChecked(true);
                this.mediumButton.setChecked(false);
                this.hardButton.setChecked(false);
            }
            if (this.mediumButton.isObjectClicked()) {
                this.checkedDifficultyButton = "medium";
                this.mediumButton.setChecked(true);
                this.easyButton.setChecked(false);
                this.hardButton.setChecked(false);
            }
            if (this.hardButton.isObjectClicked()) {
                this.checkedDifficultyButton = "hard";
                this.hardButton.setChecked(true);
                this.easyButton.setChecked(false);
                this.mediumButton.setChecked(false);
            }
            if (this.threeButton.isObjectClicked()) {
                this.checkedRoundButton = "three";
                this.threeButton.setChecked(true);
                this.fourButton.setChecked(false);
                this.fiveButton.setChecked(false);
            }
            if (this.fourButton.isObjectClicked()) {
                this.checkedRoundButton = "four";
                this.fourButton.setChecked(true);
                this.threeButton.setChecked(false);
                this.fiveButton.setChecked(false);
            }
            if (this.fiveButton.isObjectClicked()) {
                this.checkedRoundButton = "five";
                this.fiveButton.setChecked(true);
                this.fourButton.setChecked(false);
                this.threeButton.setChecked(false);
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        createButton.drawGameObject(sb);
        cancelButton.drawGameObject(sb);
        avatar.drawGameObject(sb);
        threeButton.drawGameObject(sb);
        fourButton.drawGameObject(sb);
        fiveButton.drawGameObject(sb);
        easyButton.drawGameObject(sb);
        mediumButton.drawGameObject(sb);
        hardButton.drawGameObject(sb);
        font.draw(sb, "Rounds:", (float) threeButton.getXPos(), (float) (threeButton.getYPos()+150));
        font.draw(sb, "3", (float) threeButton.getXPos()+100, (float) (threeButton.getYPos()+60));
        font.draw(sb, "4", (float) fourButton.getXPos()+100, (float) (fourButton.getYPos()+60));
        font.draw(sb, "5", (float) fiveButton.getXPos()+100, (float) (fiveButton.getYPos()+60));
        font.draw(sb, "Difficulty:", (float) easyButton.getXPos(), (float) (easyButton.getYPos()+150));
        font.draw(sb, "Easy", (float) easyButton.getXPos()+100, (float) (easyButton.getYPos()+60));
        font.draw(sb, "Medium", (float) mediumButton.getXPos()+100, (float) (mediumButton.getYPos()+60));
        font.draw(sb, "Hard", (float) hardButton.getXPos()+100, (float) (hardButton.getYPos()+60));
        sb.end();
        nickname.drawStage(nickname.getTextField());
        super.renderStage();
    }

    @Override
    public void dispose() {
            createButton.getImage().dispose();
            cancelButton.getImage().dispose();

    }
}
