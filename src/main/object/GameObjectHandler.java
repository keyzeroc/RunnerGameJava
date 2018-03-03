package main.object;

import java.awt.*;
import java.util.LinkedList;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

// handles all the game objects
public class GameObjectHandler{ // singleton pattern

    private int score;

    private LinkedList<GameObject> objects = new LinkedList<>();

    private static GameObjectHandler gameObjectHandler = new GameObjectHandler();

    private GameObjectHandler(){}

    public static GameObjectHandler getInstance(){
        return gameObjectHandler;
    }

    public void render(Graphics g){
        for (GameObject object: objects) {
            object.render(g);
        }
    }

    public void tick(){
        for (GameObject object: objects) {
            if (object.getId().equals(ID.Score)) {
                ScoreObj scoreObj = (ScoreObj)object;
                score=scoreObj.getScore();
            }
            if(object.getId().equals(ID.Background)){
                switchSpeed(object);
            }
            if(object.getId().equals(ID.Obstacle)){
                switchSpeed(object);
            }
            object.tick();
        }
    }

    private void switchSpeed(GameObject object){
        if(score==0) return;
        if(score%SWITCH_SPEED_BY==0&&score<1200){
            increaseObjectVelX(object, -1);
        }
    }
    private void increaseObjectVelX(GameObject object, int by){
        object.setVelX(object.getVelX()+by);
    }
    public LinkedList<GameObject> getObjects() {
        return objects;
    }
    public GameObject getObject(ID id){ // returns first found object
        return objects.stream().filter(o->o.getId().equals(id)).findFirst().get();
    }
    public void addObject(GameObject object){
        objects.add(object);
        System.out.println("added:"+object);
    }
    public void clearAll(){
        objects.clear();
        System.out.println("cleared objs: "+objects);

    }

    public int getScore() {
        return score;
    }
}
