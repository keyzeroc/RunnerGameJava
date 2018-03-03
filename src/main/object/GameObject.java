package main.object;

import java.awt.*;

// simple game object, extendable
public abstract class GameObject {

    protected int x,y;
    protected int velX,velY;
    protected ID id; // replace with interfaces, (Player, Obstacle, ...)
    protected int gravityY;
    protected int speed;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getGravityY() {
        return gravityY;
    }

    public void setGravityY(int gravityY) {
        this.gravityY = gravityY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
