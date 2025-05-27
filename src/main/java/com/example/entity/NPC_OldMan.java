package com.example.entity;

import java.util.Random;

import com.example.main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 30;

        getImage();
    }

    public void getImage() {
        //load the player images
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // pickup a number from 1 to 100

            if(i <= 20){
                direction = "up";
            } 
            if (i > 20 && i <= 40){
                direction = "down";
            }
            if (i > 40 && i <= 60){
                direction = "left";
            }
            if (i > 60 && i <= 80){
                direction = "right";
            }
            if (i > 80 && i <= 100){
                
            }

            actionLockCounter = 0;
        }
    }
    
}
