package com.example.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

import com.example.entity.Entity;
import com.example.entity.Player;
import com.example.object.SuperObject;
import com.example.tile.TileManager;


public class GamePanel extends JPanel implements Runnable {
    //Screen setings

    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //3x scale

    public final int tileSize = originalTileSize * scale; //48x48 tile

    public final int maxScreenCol = 16; //16 tiles
    public final int maxScreenRow = 12; //12 tiles
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //World settings 
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60; //frames per second

    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this); //Key handler for keyboard input
    Sound music = new Sound();
    Sound se = new Sound();
    public ColisionChecker cChecker = new ColisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread; //Thread for the game loop

    //Entity and Obj
    public Player player = new Player(this, keyH); //Player object
    public SuperObject obj[] = new SuperObject[15];
    public Entity npc[] = new Entity[10];

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    //PlayerSelect
    public int playerSelect = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();

//      playMusic(0);

        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //start the thread
    }



    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //time between frames in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval; //next time to draw the screen

        while(gameThread != null) {
            
            //1 UPDATE: update information such as character position
            update();
            
            //2 DRAW: draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //time remaining until the next draw
                remainingTime /= 1000000; //convert to milliseconds

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; //set the next draw time
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void update() {
        //update the game state
        if(gameState == playState){
            player.update(); //update the player
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
            stopMusic();
        }
        
    }

    public void paintComponent(Graphics g) {
        //draw the game state
        super.paintComponent(g); //call the parent class's paintComponent method

        Graphics2D g2 = (Graphics2D)g; //create a Graphics2D object

        //Debug
        long drawStart = 0;

        if (keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }

        //Title Screeen
        if (gameState == titleState){
            ui.draw(g2);
        }
        
        //Others
        else{
            //Tile
            tileM.draw(g2);

            //Object
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            //NPC 
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            player.draw(g2);

            //UI
            ui.draw(g2);
        }

        //Debug
        if (keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(ui.f8514oem.deriveFont(32F));
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed,10,400);
            // System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }
    
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
