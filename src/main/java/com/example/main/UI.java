package com.example.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;


public class UI {
    GamePanel gp;
    Font f8514oem, arial_80B;
//  BufferedImage keyImage;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

//  double playTime;
//  DecimalFormat dFormat = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));

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

        //Playstate
        if(gp.gameState == gp.playState){
            //Do playstate stuff later
        }

        //PauseState
        if(gp.gameState == gp.pauseState) {
            drawPausedScreen();
        }
        //DialogueState
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
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
