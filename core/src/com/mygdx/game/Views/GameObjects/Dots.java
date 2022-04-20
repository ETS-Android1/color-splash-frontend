package com.mygdx.game.Views.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dots {
    private List<Integer> backend = Arrays.asList(0,3,2,0,1,3);
    private List<GameObject> dots = new ArrayList<>();
    private Texture lightGreyDot = new Texture(Gdx.files.internal("circle_lightgrey.png"));
    private Texture darkGreyDot = new Texture(Gdx.files.internal("circle_darkgrey.png"));

    public Dots() {
        this.makeList();
    }

    public void makeList() {
        int scale = 2;
        //double screen = Gdx.graphics.getWidth()/(backend.size()*100);
        double name = ((Gdx.graphics.getWidth()/(backend.size()+1))*0.001);
        double screen = 0.2;
        double count = 0.8;
        double width = this.lightGreyDot.getWidth();
        if (backend.size()<5){
            scale=3;
        }
        System.out.println("HELLO HERE WE ARE");
        System.out.println(name);
        System.out.println(this.lightGreyDot.toString());
        System.out.println("END");
        for (int dot:backend) {
            GameObject newDot = new GameObject(this.lightGreyDot,name*count,0.88,scale,false,false);
            System.out.println("POSITION DOT");
            System.out.println(name*count);
            this.dots.add(newDot);
            count+=0.8;
        }
    }

    public void drawDots(SpriteBatch sb) {
        for (GameObject dot : this.dots) {
            dot.drawGameObject(sb);
        }
    }

}
