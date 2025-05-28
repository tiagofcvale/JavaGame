package com.example.entity;

import java.util.Random;

import com.example.main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
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

    public void setDialogue(){
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you come to this island to find the \ntreausure?";
        dialogues[2] = "Why did the chicken crossed the road? \nTo run away from the nig... My lawyer \nadvised me not to finish that joke.";
        dialogues[3] = "My name is Yoshikage Kira. I'm 33 years \nold. My house is in the northeast \nsection of Morioh, where all the villas \nare, and I am not married. I work as an \nemployee for the Kame Yu department \nstores, and I get home every day by 8 PM at the latest. I don't smoke, but I occasionally drink. I'm in bed by 11 PM, and make sure I get eight hours of sleep, no matter what. After having a glass of warm milk and doing about twenty minutes of stretches before going to bed, I usually have no problems sleeping until morning. Just like a baby, I wake up without any fatigue or stress in the morning. I was told there were no issues at my last check-up. I'm trying to explain that I'm a person who wishes to live a very quiet life. I take care not to trouble myself with any enemies, like winning and losing, that would cause me to lose sleep at night. That is how I deal with society, and I know that is what brings me happiness. Although, if I were to fight I wouldn't lose to anyone.";

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
    
    public void speak() {
        super.speak();
    }
}
