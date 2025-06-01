package com.example.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.example.object.OBJ_Heart;
import com.example.object.SuperObject;

//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;


public class UI {
    GamePanel gp;
    public Font f8514oem, arial_80B;
//  BufferedImage keyImage;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first Screen, 1: Second Screen
//  double playTime;
//  DecimalFormat dFormat = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));
    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/f8514oem.otf");
            f8514oem = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        arial_80B = new Font("8514oem Regular", Font.BOLD, 80);

//      OBJ_Key key = new OBJ_Key(gp);
//      keyImage = key.image;

        //Create HUD OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(f8514oem);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //Title State
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //Playstate
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }

        //PauseState
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPausedScreen();
        }
        //DialogueState
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {
        
//      gp.player.life = 6;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //Draw Blank Heart
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //Draw Current Life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        
        if (titleScreenState == 0) {
            g2.setColor(new Color(50,120,80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //Title Name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,58F));
            String text = "Java Adventure Game"; //por agora
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            //Shadow
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //Main Color
            Color c = new Color(255,255,197);
            g2.setColor(c);
            g2.drawString(text, x, y);

            //Mati Image
            x = gp.screenWidth/2 - (gp.tileSize);
            y += gp.tileSize*1.5;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2 ,gp.tileSize*2,null);

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1) {
            g2.setColor(new Color(50,120,80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //Class Selection Screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select Your character!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Mati";
            x = gp.tileSize*4;
            y += gp.tileSize*5;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            //Mati Image
            int dy = gp.tileSize*5;
            g2.drawImage(gp.player.mati, x, dy, gp.tileSize*2 ,gp.tileSize*2,null);
            
            text = "Tinho";
            x += gp.tileSize*5.5;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            //Tinho Image
            int dx = gp.tileSize * 11 - (gp.tileSize);
            g2.drawImage(gp.player.tinho, dx, dy, gp.tileSize*2 ,gp.tileSize*2,null);

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }

    public void drawDialogueScreen() {
        
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize*7 + (gp.tileSize/2);
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20));
        x += gp.tileSize;
        y += gp.tileSize + (gp.tileSize/6);
        
        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawPausedScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));

        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String s) {
        int lenght = (int)g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
}
