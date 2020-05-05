package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperUpgrade extends GameObject {

    private BufferedImage superUpgrade;

    public SuperUpgrade(double x, double y, gameID id) {
        super(x, y, id);

        BackgroundLevel loader = new BackgroundLevel();
        superUpgrade = loader.loadImage("/heart.png");
    }

    @Override
    public void tick() {

    }
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(superUpgrade, (int)x, (int)y, 64, 64, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        graphics.drawImage(superUpgrade, (int)this.x / 8 + x, (int)this.y / 8 + y, 64 / 8, 64 / 8, null);
    }
}
