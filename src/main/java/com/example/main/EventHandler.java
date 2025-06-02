package com.example.main;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canToutchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {

        //check if the player is more than 1 tile away from the last Event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canToutchEvent = true;
        }

        if (canToutchEvent == true) {
            //DamagePit
            if(hit(25, 21, "right") == true) {damagePit(25, 21, gp.dialogueState);}
            //Healing pool
            if(hit(23,19,"up") == true) {healingPool(23, 19, gp.dialogueState);}
            //Teleport
            if(hit(21, 21, "left") == true) {teleport(gp.dialogueState, 35, 45);}
            if(hit(39, 45, "right") == true) {teleport(gp.dialogueState, 21, 21);}
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[eventCol][eventRow].x = eventCol*gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow*gp.tileSize + eventRect[eventCol][eventRow].y;

        if(gp.player.solidArea.intersects(eventRect[eventCol][eventRow]) && eventRect[eventCol][eventRow].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
//      eventRect[col][row].eventDone = true;
        canToutchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. \nYour life has been recovered";
            gp.player.life = gp.player.maxLife;
            canToutchEvent = false;
        }
    }

    public void teleport(int gameState, int x , int y) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * x;
        gp.player.worldY = gp.tileSize * y;
        canToutchEvent = false;
    }
}
