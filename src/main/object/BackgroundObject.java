package main.object;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.core.Tools.*;
// background object that moves with velX speed, creating feeling that our player is moving
public class BackgroundObject extends GameObject {

    private BufferedImage image;

    public BackgroundObject(int x, int y, ID id, BufferedImage image) {
        super(x, y, id);
        this.image=image;
        this.velX=-2;
    }

    @Override
    public void tick() {
        if(x<8-WINDOW_WIDTH){
            x=WINDOW_WIDTH;
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
        return null;
    }
}
