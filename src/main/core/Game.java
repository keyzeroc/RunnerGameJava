package main.core;

import main.object.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
// IMPORTED TOOLS STATIC CONTENT
import static main.core.Tools.*;

public class Game implements Runnable{

    private String title;

    private Thread threadGameLoop;
    private Thread threadHitBox;
    private boolean running;

    private BufferStrategy bs;
    private Display display;
    private Graphics g;

    private GameObjectHandler gameObjectHandler;

    private BufferedImage imgBackground;
    private BufferedImage imgGameOver;
    private BufferedImage imgPlayerSkin;
    private BufferedImage imgFenceObstacle;

    public Game(String title,int width,int height) {
        this.title=title;
        running=false;
        setup(width,height);
    }
    // method that calls chain of setup methods
    private void setup(int width, int height){
        display=new Display(title,width,height);
        gameObjectHandler = GameObjectHandler.getInstance();
        loadImages();
        setupGameObjects();
        display.getCanvas().addKeyListener(new KeyInput(gameObjectHandler));
        System.out.println("Successfully ended setup!");
    }
    // loading all the images from res path
    private void loadImages(){
        imgPlayerSkin=loadImage("/images/player/skin/ghost60x67.png");
        imgGameOver=loadImage("/images/main menu/gameover2.png");
        imgBackground=loadImage("/images/main menu/background_no_moon.png");
        imgFenceObstacle=loadImage("/images/obstacle/obstacle3.png");
        System.out.println("Successfully loaded images!");
    }

    private void setupGameObjects(){
        //background objects creation
        gameObjectHandler.addObject(new BackgroundObject(
                0,0,ID.Background,imgBackground
        ));
        gameObjectHandler.addObject(new BackgroundObject(
                WINDOW_WIDTH,0,ID.Background,imgBackground
        ));
        // player object creation
        gameObjectHandler.addObject(new PlayerObject(
                20,
                WINDOW_HEIGHT-80,
                ID.Player,
                imgPlayerSkin,
                60,
                65
        ));
        // fence obstacle object creation
        gameObjectHandler.addObject(new FenceObstacleObject(
                0, // no need, overridden in constructor
                WINDOW_HEIGHT-70,
                ID.Obstacle,
                imgFenceObstacle,
                55,
                65
        ));
        // score object creation
        gameObjectHandler.addObject(new ScoreObj(
                Tools.WINDOW_WIDTH/2,20,ID.Score,Color.BLACK
        ));
        if (threadHitBox == null) {
            createHitBoxThread();
        }
        System.out.println("Successfully loaded objects!");
    }
    // thread that checks collisions of player and obstacles
    private void createHitBoxThread(){
        threadHitBox=new Thread(()->{
            while(running) {
                if(!RESTART_GAME) {
                    Rectangle playerPos = gameObjectHandler.getObject(ID.Player).getBounds();
                    for (GameObject obj : gameObjectHandler.getObjects()) {
                        if (obj.getId().equals(ID.Obstacle)) {
                            if (Tools.hitBox(playerPos, obj.getBounds())) {
                                setFailed(true);
                            }
                        }
                    }
                }else{

                }
            }
        });
    }
    // to start the game call this from main()
    public void start(){
        if(running) return;

        running=true;
        threadGameLoop=new Thread(this);
        threadGameLoop.start();
        threadHitBox.start();
    }

    public void stop(){
        if(!running) return;

        running = false;
        try{
            threadGameLoop.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // restarts the game
    private void restart(){
        gameObjectHandler.clearAll();
        setupGameObjects();
        setFailed(false);
        setJumpOff(false);
        setRestartGame(false);
    }

    // game loop
    @Override
    public void run() {
        display.getCanvas().requestFocus();
        long jvmLastTime = System.nanoTime();
        long time = System.currentTimeMillis();
        double jvmPartTime = 1_000_000_000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while(running){
            long jvmNow = System.nanoTime();
            delta += (jvmNow-jvmLastTime);
            jvmLastTime=jvmNow;
            if(delta >= jvmPartTime){
                tick();
                updates++;
                delta=0;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - time > 1000){
                time+=1000;
                System.out.println("FPS: "+frames+", UPDATES: "+updates);
                frames=0;
                updates=0;
            }
        }
        stop();
    }

    // update stuff
    private void tick(){
        if(RESTART_GAME){
            restart();
        }
        if(!FAILED) {
            gameObjectHandler.tick();
        }
    }

    // draws to the screen
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g=bs.getDrawGraphics();
        g.clearRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);

        //////////////////////////////////////////////////

        if(!FAILED) {
            gameObjectHandler.render(g);
        }else if(FAILED){
            drawFailScreen();
        }else{// if in main menu

        }

        //////////////////////////////////////////////////
        g.dispose();
        bs.show();

    }
    private void drawFailScreen(){
        g.clearRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.drawImage(imgGameOver,0,0,null);
        g.setFont(new Font("Courier", Font.BOLD,30));
        g.drawString(String.valueOf(gameObjectHandler.getScore()),WINDOW_WIDTH/2-20,WINDOW_HEIGHT/2+30);//draw end score
    }
}