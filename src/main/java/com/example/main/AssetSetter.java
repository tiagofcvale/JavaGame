package com.example.main;

import com.example.entity.NPC_OldMan;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*20;
        gp.npc[0].worldY = gp.tileSize*20;
    }

}
