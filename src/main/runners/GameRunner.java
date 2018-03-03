package main.runners;

import main.core.Game;
import static main.core.Tools.*;

// RUN GAME HERE
public class GameRunner {
    //Tools.class has more options to configure game
    public static void main(String[] args) {
        new Game("Runner",WINDOW_WIDTH,WINDOW_HEIGHT).start();
    }
}
