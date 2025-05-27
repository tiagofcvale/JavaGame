package com.example.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.example.main.GamePanel;
import com.example.main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[99];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world02.txt");
    }

    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String map) {
        try{
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void getTileImage() {

            //Placeholder
            setup(0, "old_v/grass", false);
            setup(1, "old_v/wall", true);
            setup(2, "old_v/water", true);
            setup(3, "road00", false);
            setup(4, "old_v/earth", false);
            setup(5, "old_v/tree", true);
            setup(6, "old_v/sand", false);
            setup(7, "old_v/sand", false);
            setup(8, "old_v/sand", false);
            setup(9, "old_v/sand", false);

            //real thing
            setup(10, "old_v/grass", false);
            setup(11, "wall", true);
            setup(12, "water00", true); //full
            setup(13, "road00", false);
            setup(14, "earth", false);
            setup(15, "tree", true);
            setup(16, "old_v/sand", false);
            setup(17, "water01", true);
            setup(18, "water02", true);
            setup(19, "water03", true);
            setup(20, "water04", true);
            setup(21, "water05", true);
            setup(22, "water06", true);
            setup(23, "water07", true);
            setup(24, "water08", true);
            setup(25, "water09", true);
            setup(26, "water10", true);
            setup(27, "water11", true);
            setup(28, "water12", true);
            setup(29, "water13", true);
            
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
