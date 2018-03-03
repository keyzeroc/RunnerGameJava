package main.object;

import java.awt.*;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

// score object, simple auto-incremented value object
public class ScoreObj extends GameObject{

    private int score;
    private int h;
    private Color color;
    //todo: color changes with level.

    public ScoreObj(int x, int y, ID id, Color color) {
        super(x, y, id);
        this.color=color;
    }

    @Override
    public void tick() {
        if(++h==DIFFICULTY_COEFFICIENT){
            score++;
            h=0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Courier", Font.BOLD,15));
        g.drawString("SCORE:",x-65,y);
        g.drawString(String.valueOf(score), x,y); // score draw
    }

    @Override
    public Rectangle getBounds() {
        return null;// no need
    }

    public int getScore() {
        return score;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
