package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.ScoreBoardView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;
import com.mygdx.game.dataClasses.ScoreBoardInfo;

import java.util.ArrayList;
import java.util.List;

import io.socket.emitter.Emitter;

public class AnswerController{
    private final DisplayColors colorInfo;
    private boolean isLoading;
    private ScoreBoardInfo scoreBoardInfo;
    private final ViewManager viewManager;
    private Integer timer;
    private float timeCount;
    private int buttonCount = 0;
    private int localScore = 0;
    private final List<Integer> playerAnswer = new ArrayList<>();
    private final List<Integer> correctColors;
    private String feedback = "";

    private boolean playerFinished = false;


    public AnswerController(ViewManager viewManager, DisplayColors colorInfo) {
        this.viewManager = viewManager;
        timer = 8;
        timeCount = 0;
        this.isLoading = true;
        this.colorInfo = colorInfo;
        correctColors = colorInfo.getNumber();
        this.roundFinished();
    }

    private void addPlayerAnswer(int answer) {
        this.playerAnswer.add(answer);
    }

    public List<Integer> getCorrectColors() {
        return correctColors;
    }

    public boolean isPlayerFinished() {
        return playerFinished;
    }

    private void setPlayerFinished() {
        this.playerFinished = true;
    }

    private void countDown() {
        timer--;
    }

    private int getButtonCount() {
        return buttonCount;
    }

    private void incrementButtonCount() {
        buttonCount++;
    }

    private List<Integer> getPlayerAnswer() {
        return playerAnswer;
    }

    public int getLocalScore() {
        return localScore;
    }

    private void incrementLocalScore() {
        localScore++;
    }

    private void resetLocalScore() {
        this.localScore = 0;
    }

    private float getTimeCount() {
        return timeCount;
    }

    private void resetTimeCount() {
        this.timeCount = 0;
    }

    private void addToTimeCount(float dt) {
        timeCount+=dt;
    }

    public Integer getTimer() {
        return timer;
    }

    private void playerFinished(int gameId, List<Integer> answer) {
        ColorSplash.socketManager.playerFinished(gameId, answer);
    }

    private void setScoreBoardView() {
        while (isLoading) {
        }
        viewManager.set(new ScoreBoardView(viewManager, scoreBoardInfo));
    }

    private DisplayColors getColorInfo() {
        return colorInfo;
    }

    private void roundFinished() {
        ColorSplash.socketManager.createListener(EventsConstants.endRound, scoreBoardListener());
    }

    private Emitter.Listener scoreBoardListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isLoading = true;
                Gson gson = new Gson();
                scoreBoardInfo = gson.fromJson(args[0].toString(), ScoreBoardInfo.class);
                isLoading = false;
            }
        };
    }

    private void receiveFeedback() {
        double finishedTime = this.getTimer();
        if(finishedTime > 6){
            feedback = "Too fast..?";
        }
        else if(finishedTime > 4){
            feedback = "  Speedy!!";
        }
        else {
            feedback =  "Too slow..?";
        }
    }

    public String getFeedback() {
        return feedback;
    }

    public int redButtonClicked() {
        int count = this.getButtonCount();
        this.addPlayerAnswer(1);
        this.incrementButtonCount();
        return count;
    }

    public int greenButtonClicked() {
        int count = this.getButtonCount();
        this.addPlayerAnswer(2);
        this.incrementButtonCount();
        return count;
    }

    public int blueButtonClicked() {
        int count = this.getButtonCount();
        this.addPlayerAnswer(0);
        this.incrementButtonCount();
        return count;
    }

    public int yellowButtonClicked() {
        int count = this.getButtonCount();
        this.addPlayerAnswer(3);
        this.incrementButtonCount();
        return count;
    }

    public void setPlayerFinished(int numberOfAnswers) {
        if(this.getButtonCount()==numberOfAnswers){
            if(!this.isPlayerFinished()){
                this.playerFinished(this.getColorInfo().getGameId(),this.getPlayerAnswer());
            }
            this.setPlayerFinished();
            this.receiveFeedback();
        }
    }

    public void updateTimeCount(float dt) {
        this.addToTimeCount(dt);
        if (this.getTimeCount() >= 1) {
            this.countDown();
            this.resetTimeCount();
        }
        if(this.getTimer()==0){
            if(!this.isPlayerFinished()){
                this.playerFinished(getColorInfo().getGameId(),this.getPlayerAnswer());
            }
            this.setPlayerFinished();
            this.setTimesUp();
        }
        if(this.getTimer()==-3){
            this.setScoreBoardView();
        }
    }

    public List<Boolean> setAnswers() {
        List<Boolean> answers = new ArrayList<>();
        this.resetLocalScore();
        for (int i = 0; i < this.getCorrectColors().size(); i++) {
            try {
                if (this.getCorrectColors().get(i).equals(this.getPlayerAnswer().get(i))) {
                    this.incrementLocalScore();
                    answers.add(true);
                } else {
                    answers.add(false);
                }
            }
            catch (IndexOutOfBoundsException e) {
                answers.add(false);
            }
        }

        return answers;
    }


    private void setTimesUp() {
        this.feedback = "Time's up!";
    }
}
