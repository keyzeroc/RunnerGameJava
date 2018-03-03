package main.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tools {

    // CONSTANTS
    public static final int JUMP_LIMIT=150;    // how far player can jump till jump is disabled
    public static final int JUMP_GRAVITY=-4;   // gravityY when player jumps
    public static final int FALL_GRAVITY=3;    // gravityY when player falls
    public static final int WINDOW_WIDTH=600;  // window width
    public static final int WINDOW_HEIGHT=350; // window height
    public static final int SWITCH_SPEED_BY=300; // changes the speed of objects each 300 meters
    public static final int DIFFICULTY_COEFFICIENT = 2; // lower = faster game

    // GAME STATES, TRIGGERS
    public static boolean RESTART_GAME=false; // game state - restart game
    public static boolean FAILED=false; // game state - failed
    public static boolean JUMP_OFF=false; // jump is available, when true - jump disabled

    // game controls list
    private static Set<Integer> controls = new HashSet<Integer>(){{
       add(KeyEvent.VK_W); //up(jump)
       add(KeyEvent.VK_S); // todo: crouch
       add(KeyEvent.VK_R); //restart
    }};
    // checks if the parameter is one of game controls, called from @KeyInput.class
    public static boolean isControl(Integer keyNum){
        return controls.contains(keyNum);
    }

    public static synchronized void setRestartGame(boolean value){
        RESTART_GAME=value;
    }
    public static synchronized void setFailed(boolean value) {
        FAILED = value;
    }
    public static synchronized void setJumpOff(boolean value){
        JUMP_OFF=value;
    }

    // returns random int in @bound diapason
    public static int getRandomInt(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
    // checks hitbox
    public static synchronized boolean hitBox(Rectangle out, Rectangle in){
       return out.intersects(in);
    }
    // method that tries to load image and returns BufferedImage if success, null if not, if not exist crashes program
    public static synchronized BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Tools.class.getResource(path)); // new File
        }catch (Exception e) {
            System.out.println("IMAGE NOT EXISTS:  " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
