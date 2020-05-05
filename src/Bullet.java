package src;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    Handler gameHandler;

    private int rateOfFire;
    private double xBullet;
    private double yBullet;
    private int size;

    private BufferedImage bullet;

    private gameID enemyTank;

    public Bullet(double x, double y, double a, int fireRate, gameID id, gameID tank, Handler gameHandler) {
        super(x, y, id);

        angle = a;
        rateOfFire = fireRate;
        this.gameHandler = gameHandler;

        BackgroundLevel loader = new BackgroundLevel();
        BufferedImage strip = loader.loadImage("/BulletStrip.png");
        size = strip.getWidth() / 60;
        bullet = strip.getSubimage( 0* size, 0, size, size);

        xBullet = Math.cos(angle) * rateOfFire;
        yBullet = Math.sin(angle) * rateOfFire;

        if (tank == gameID.Player1) {
            enemyTank = gameID.Player2;
        } else {
            enemyTank = gameID.Player1;
        }

    }

    @Override
    public void tick() {
        x += xBullet;
        y += yBullet;
        for (int i = 0; i < gameHandler.object.size(); i++) {
            GameObject temp = gameHandler.object.get(i);

            if (temp.getId() == gameID.UnbreakableBlock || temp.getId() == gameID.BreakableBlock ||
                    temp.getId() == enemyTank) {
                if (getBounds().intersects(temp.getBounds())) {
                    gameHandler.removeObject(this);

                    if (temp.getId() == gameID.BreakableBlock) {
                        gameHandler.removeObject(temp);
                    }
                    if (temp.getId() == enemyTank) {
                        temp.setHealthBar(temp.getHealthBar() - 25);
                    }
                }
            }

        }
    }

    @Override
    public void render(Graphics graphics) {
        /*graphics.setColor(Color.black);
        graphics.fillOval((int) x,(int) y, 8, 8);*/
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform old = g.getTransform();
        g.rotate(angle, x + 15 / 3 , y + 15 / 3 );
        g.drawImage(bullet, (int)x, (int)y, 16, 16, null);
        g.setTransform(old);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 16, 16);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        /*graphics.fillOval((int)this.x / 8 + x, (int)this.y / 8 + y,  1, 1);*/
        graphics.drawImage(bullet, (int)this.x / 8 + x, (int)this.y / 8 + y, 2, 2, null);

    }
}
