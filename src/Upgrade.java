package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Upgrade extends GameObject {

    private BufferedImage upgrade;

    public Upgrade(double x, double y, gameID id) {
        super(x, y, id);
        BackgroundLevel loader = new BackgroundLevel();
        upgrade = loader.loadImage("/heart.png");
    }

    @Override
    public void tick() {
    }
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(upgrade, (int)x, (int)y, 32, 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        graphics.drawImage(upgrade, (int)this.x / 8 + x, (int)this.y / 8 + y, 32 / 8, 32 / 8, null);
    }
}
