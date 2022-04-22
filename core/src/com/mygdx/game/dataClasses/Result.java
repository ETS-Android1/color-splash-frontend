package com.mygdx.game.dataClasses;

public class Result {

    private String nickname;
    private String playerId;
    private int totalScore;
    private int avatarIndex;

    public Result (String nickname, String playerId, int totalScore, int avatarIndex) {
        this.nickname = nickname;
        this.playerId = playerId;
        this.totalScore = totalScore;
        this.avatarIndex = avatarIndex;
    }

    @Override
    public String toString() {
        return "ScoreBoardInfo{" +
                "nickname='" + nickname + '\'' +
                ", playerId='" + playerId + '\'' +
                ", totalScore=" + totalScore +
                ", avatarIndex=" + avatarIndex + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getAvatarIndex() {
        return avatarIndex;
    }





}