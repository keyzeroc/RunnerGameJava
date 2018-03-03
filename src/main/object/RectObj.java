package main.object;

import java.awt.*;

// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

// test obstacle object
public class RectObj extends GameObject {

    private Color color;

    public RectObj(int x, int y, ID id, Color color) {
        super(WINDOW_WIDTH+getRandomInt(200), y, id);
        this.velX=-2; //change to START_SPEED
        this.color=color;
    }

    @Override
    public void tick() {
        if(x<0){
            x=WINDOW_WIDTH+getRandomInt(200);
        }
        if(y<0){
            y=WINDOW_HEIGHT-40;
        }
        this.x+=velX;
        this.y+=velY;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,40,40);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,40,40);
    }
}
