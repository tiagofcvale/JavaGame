package com.example.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.example.entity.Player;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    boolean checkDrawTime;

    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Debug
        if(code == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
        
        //TitleState
        if(gp.gameState == gp.titleState){
            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum <= -1){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum >= 3){
                        gp.ui.commandNum = 0;
                    }
                }
                //NEWGAME
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                //LoadGame (not implemented)
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 1) {
                    
                }
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 2) {
                    System.exit(0); //Quit
                }
            }
            else if (gp.ui.titleScreenState == 1) {
                if(code == KeyEvent.VK_RIGHT) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_LEFT) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                //Mati
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 0) {
                    gp.playerSelect = 0;
                    gp.player = new Player(gp, this); // recria o player com o novo valor
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                //Tinho
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 1) {
                    gp.playerSelect = 1;
                    gp.player = new Player(gp, this); // recria o player com o novo valor
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                //Back
                if (code == KeyEvent.VK_ENTER && gp.ui.commandNum == 2) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }

        //PlayState
        if (gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
                enterPressed = true;
            }
            //Pause
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
        }
        //PauseState
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }

        //DialogueState
        else if(gp.gameState == gp.dialogueState){
            if(code ==  KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_E){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
    
}
