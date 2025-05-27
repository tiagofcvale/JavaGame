package com.example.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
//  BufferedImage keyImage;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

//      OBJ_Key key = new OBJ_Key(gp);
//      keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            //Do playstate stuff later
        }
        if(gp.gameState == gp.pauseState) {
            drawPausedScreen();
        }
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
