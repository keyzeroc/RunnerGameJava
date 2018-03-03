package main.core;

import main.object.GameObject;
import main.object.GameObjectHandler;
import main.object.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

public class KeyInput  extends KeyAdapter {

    private GameObjectHandler handler;

    public KeyInput(GameObjectHandler handler){
        this.handler=handler;
    }
    // called when key pressed
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(!isControl(key)) return;

        if (key==KeyEvent.VK_W) {
            if(!JUMP_OFF) {
                GameObject player=handler.getObject(ID.Player);
                player.setGravityY(JUMP_GRAVITY);
            }
        }

        if(key==KeyEvent.VK_R){
            if(FAILED) setRestartGame(true);
        }
    }
    // called when key released
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(!isControl(key)) return;

        if (key==KeyEvent.VK_W){
            GameObject player=handler.getObject(ID.Player);
            player.setGravityY(FALL_GRAVITY);
            setJumpOff(true);
        }

    }
}
