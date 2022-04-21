package com.mygdx.game.dataClasses;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Views.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    public String name;
    public int avatarIndex;
    public GameObject avatar;
    private List<String> avatars = new ArrayList<>(Arrays.asList("avatar_orange.png", "avatar_green.png", "avatar_pink.png", "avatar_purple.png"));

    public Player() {
        this.avatar = new GameObject(new Texture(avatars.get(avatarIndex)), 0.2, 0.6, 1,false,false);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", avatarIndex=" + avatarIndex +
                '}';
    }
}
