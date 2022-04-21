package com.mygdx.game.dataClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Views.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
