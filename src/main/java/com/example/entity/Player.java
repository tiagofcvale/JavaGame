package com.example.entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.example.main.GamePanel;
import com.example.main.KeyHandler;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

//  public int hasKey = 0;

    int standCounter = 0;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; //default direction

        //Player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        //load the player images
        mati = setup("/player/Walking_sprites/boy_down_1");
        tinho = setup("/player/Tinho/boy_down_1");
        if (gp.playerSelect == 0){
            up1 = setup("/player/Walking_sprites/boy_up_1");
            up2 = setup("/player/Walking_sprites/boy_up_2");
            down1 = setup("/player/Walking_sprites/boy_down_1");
            down2 = setup("/player/Walking_sprites/boy_down_2");
            left1 = setup("/player/Walking_sprites/boy_left_1");
            left2 = setup("/player/Walking_sprites/boy_left_2");
            right1 = setup("/player/Walking_sprites/boy_right_1");
            right2 = setup("/player/Walking_sprites/boy_right_2");
        } else if (gp.playerSelect == 1) {
            up1 = setup("/player/Tinho/boy_up_1");
            up2 = setup("/player/Tinho/boy_up_2");
            down1 = setup("/player/Tinho/boy_down_1");
            down2 = setup("/player/Tinho/boy_down_2");
            left1 = setup("/player/Tinho/boy_left_1");
            left2 = setup("/player/Tinho/boy_left_2");
            right1 = setup("/player/Tinho/boy_right_1");
            right2 = setup("/player/Tinho/boy_right_2");
        }
    }


    public void update() {
        //update the game state

        if (keyH.upPressed == true || keyH.downPressed == true || 
                    keyH.leftPressed == true || keyH.rightPressed == true) {
            
            if(keyH.upPressed == true) {
                direction = "up"; //set direction to up
            }
            if(keyH.downPressed == true) {
                direction = "down"; //set direction to down
            }
            if(keyH.leftPressed == true) {
                direction = "left"; //set direction to left
            }
            if(keyH.rightPressed == true) {
                direction = "right"; //set direction to right
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction) {
                    case "up":
                        worldY -= speed; //move up
                        break;
                    case "down":
                        worldY += speed; //move down
                        break;
                    case "left":
                        worldX -= speed; //move left
                        break;
                    case "right":
                        worldX += speed; //move right
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) { //change sprite every 10 frames
                if(spriteNum == 1) {
                    spriteNum = 2; //change to the next sprite
                } else if(spriteNum == 2) {
                    spriteNum = 1; //change to the previous sprite
                }
                spriteCounter = 0; //reset the counter
            }
            
        } else {
        // Se parou de andar para a esquerda ou direita, spriteNum volta a 1
            standCounter ++;

            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if(i != 999) {

        }
    }

    public void interactNPC(int i){
        if(i != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }   
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2) {
        //draw the game state

        //Color orange = new Color(255,99,71,100);
        //g2.setColor(orange);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize); //draw a rectangle at (x,y) with the size of a tile
        //g2.dispose(); //dispose of the Graphics2D object

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null); //draw the image at (x,y) with the size of a tile
    }
}
