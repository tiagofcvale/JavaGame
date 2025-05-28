package com.example.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        //Debug
        if(code == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
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
