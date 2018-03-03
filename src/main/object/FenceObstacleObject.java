package main.object;

import java.awt.*;
import java.awt.image.BufferedImage;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

// obstacle which moves with velX speed
public class FenceObstacleObject extends GameObject {

    private BufferedImage image;
    private int width;
    private int height;

    public FenceObstacleObject(int x, int y, ID id, BufferedImage image,int width,int height) {
        super(WINDOW_WIDTH+getRandomInt(200), y, id);
        this.velX=-2; //change to START_SPEED
        this.image=image;
        this.width=width;
        this.height=height;
    }

    @Override
    public void tick() {
        if(x<0-width){
            x=WINDOW_WIDTH+getRandomInt(200);
        }
        x+=velX;
        y+=velY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);  // todo: edit sizes!
    }
}
