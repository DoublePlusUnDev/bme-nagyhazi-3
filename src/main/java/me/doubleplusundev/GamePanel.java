package me.doubleplusundev;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import me.doubleplusundev.map.MapHandler;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.util.Vector2;

public class GamePanel extends JPanel implements IUpdatable {
    private BufferedImage grass;
    private BufferedImage seaDeep;
    private BufferedImage seaShore;
    private BufferedImage lake;

    public GamePanel() {
        setFocusable(true);
        try{
            grass = ImageIO.read(getClass().getResource("/textures/grass.png"));
            seaDeep = ImageIO.read(getClass().getResource("/textures/sea_deep.png"));
            seaShore = ImageIO.read(getClass().getResource("/textures/sea_shore.png"));
            lake = ImageIO.read(getClass().getResource("/textures/lake.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        UpdateManager.getInstance().register(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D)g;

        Vector2 playerPosition = PlayerController.getInstance().getPosition();
        System.out.println("hey");
        double leftXCoord = playerPosition.x;
        double topYCoord = playerPosition.y;

        System.out.println(leftXCoord + " " + topYCoord);

        int tileSize = 40;
 
        int leftXFloor = (int)Math.floor(leftXCoord);
        int topYFloor = (int)Math.floor(topYCoord);

        for (int x = leftXFloor; (x - leftXFloor - 1) * tileSize < getWidth(); x++){
            for (int y = topYFloor; (y - topYFloor - 1) * tileSize < getHeight(); y++){
                TileType type = MapHandler.getInstance().getTile(x, y);

                int drawX = (int) Math.floor((x - leftXCoord) * tileSize);
                int drawY = (int) Math.floor((y - topYCoord ) * tileSize);

                if (type == TileType.GRASS)
                    graphics.drawImage(grass, drawX, drawY, tileSize, tileSize, null);
                else if (type == TileType.SEA_DEEP)
                    graphics.drawImage(seaDeep, drawX, drawY, tileSize, tileSize, null);
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }
}