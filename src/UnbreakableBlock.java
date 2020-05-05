package src;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UnbreakableBlock extends GameObject {

    private BufferedImage unbreakableBlock;

    public UnbreakableBlock(double x, double y, gameID id, SpriteSheet ss) {
        super(x, y, id);
        unbreakableBlock = ss.grabImage(3,1,32,32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
       graphics.drawImage(unbreakableBlock,(int)x,(int) y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        graphics.drawImage(unbreakableBlock, (int)this.x / 8 + x, (int)this.y / 8 + y, 32 / 8, 32 / 8, null);
    }
}
