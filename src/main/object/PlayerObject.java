package main.object;

import java.awt.*;
import java.awt.image.BufferedImage;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

// our player object
public class PlayerObject extends GameObject{

    private BufferedImage skin;
    private int startY;
    private int width;
    private int height;
    private boolean crouched; // todo: add feature

    public PlayerObject(int x, int y,ID id,BufferedImage skin, int width, int height) { // and bounds param
        super(x, y,id);
        this.skin = skin;
        this.startY=y;
        this.gravityY=FALL_GRAVITY;
        this.width=width;
        this.height=height;
    }

    @Override
    public void tick() {
        if(y>=startY){
            y=startY;
        }
        if(y<=WINDOW_HEIGHT-40-JUMP_LIMIT){
            setJumpOff(true);
            gravityY=FALL_GRAVITY-2;
        }else if(y>=startY){
            setJumpOff(false);
        }
        y+=velY+gravityY;
    }

    public BufferedImage getSkin() {
        return skin;
    }

    public void setSkin(BufferedImage skin) {
        this.skin = skin;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(skin,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }


}
