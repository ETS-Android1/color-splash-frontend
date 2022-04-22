package com.mygdx.game.dataClasses;

public class Player {

    public String name;
    private int avatarIndex;

    public int getAvatarIndex() {
        return this.avatarIndex;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", avatarIndex=" + avatarIndex +
                '}';
    }
}
