package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableBlock extends GameObject {

    private BufferedImage BreakableBlock;

    public BreakableBlock(double x, double y, gameID id, SpriteSheet ss) {
        super(x, y, id);
        BreakableBlock = ss.grabImage(2,1,32,32);
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(BreakableBlock,(int)x,(int) y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        graphics.drawImage(BreakableBlock, (int)this.x / 8 + x, (int)this.y / 8 + y, 32 / 8, 32 / 8, null);
    }
}
