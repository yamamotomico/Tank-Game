package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class imageBackground {

    private BufferedImage background;
    private BufferedImage loadBackground;
    Graphics2D g2D;

    imageBackground(){
        BackgroundLevel loader = new BackgroundLevel();
        loadBackground = loader.loadImage("/background3.png");
        background = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
        g2D = (Graphics2D)background.getGraphics();
        addBackground();
    }

    public void addBackground(){
        for(int x = 0; x < 1000; x += 300){
            for(int y = 0; y < 500; y += 240){
                g2D.drawImage(loadBackground, x, y, null);
            }
        }
    }

    public BufferedImage getBackground(){
        return background;
    }
}
