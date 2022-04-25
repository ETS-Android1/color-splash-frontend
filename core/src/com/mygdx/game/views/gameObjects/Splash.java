package com.mygdx.game.views.gameObjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splash extends GameObject {
    private final Map<Integer, List<String>> splashes = new HashMap<>();

    public Splash(Texture image, double xPos, double yPos, double scale, boolean centerHeight, boolean centerWidth) {
        super(image, xPos, yPos, scale, centerHeight, centerWidth);
        List<String> blue_splash = Arrays.asList("splash_1_blue.png", "splash_2_blue.png", "splash_3_blue.png", "splash_4_blue.png", "splash_5_blue.png");
        splashes.put(0, blue_splash);
        List<String> red_splash = Arrays.asList("splash_1_red.png", "splash_2_red.png", "splash_3_red.png", "splash_4_red.png", "splash_5_red.png");
        splashes.put(1, red_splash);
        List<String> green_splash = Arrays.asList("splash_1_green.png", "splash_2_green.png", "splash_3_green.png", "splash_4_green.png", "splash_5_green.png");
        splashes.put(2, green_splash);
        List<String> yellow_splash = Arrays.asList("splash_1_yellow.png", "splash_2_yellow.png", "splash_3_yellow.png", "splash_4_yellow.png", "splash_5_yellow.png");
        splashes.put(3, yellow_splash);
    }

    public Map<Integer, List<String>> getSplashes(){
        return this.splashes;
    }

}
