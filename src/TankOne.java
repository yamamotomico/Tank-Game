package src;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TankOne extends GameObject {

    Handler gameHandler;
    private double tempAngle;
    private double fireRate;
    private double angleX = 0;
    private double angleY = 0;
    private int size;

    private BufferedImage tankObj;

    public TankOne(double x, double y,double a, gameID id, Handler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;

        angle = Math.toRadians(a);
        tempAngle = a;
        healthBar = 100;
        lives = 2;

        BackgroundLevel loader = new BackgroundLevel();
        BufferedImage strip = loader.loadImage("/TankStrip.png");
        size = strip.getWidth() / 60;
        tankObj = strip.getSubimage(0 * size, 0, size, size);

        fireRate = 0;
    }

    @Override
    public void tick() {
        x += angleX * 4;
        y += angleY * 4;

        angle = Math.toRadians(tempAngle);
        if (healthBar <= 0) {
            if (lives > 0) {
                lives--;
                if (lives != 0) {
                    healthBar = 100;
                }
            } else {
                gameHandler.removeObject(this);
            }
        }

        collision();

        /*if(gameHandler.isUpTankOne()) velY = -5;
        else if (!gameHandler.isDownTankOne()) velY = 0;

        if(gameHandler.isDownTankOne()) velY = 5;
        else if (!gameHandler.isUpTankOne()) velY = 0;

        if(gameHandler.isRightTankOne()) velX = 5;
        else if (!gameHandler.isLeftTankOne()) velX = 0;

        if(gameHandler.isLeftTankOne()) velX = -5;
        else if (!gameHandler.isRightTankOne()) velX = 0;*/

        if (gameHandler.isUpTankOne()) {
            angleX = Math.cos(angle);
            angleY = Math.sin(angle);
        } else if (!gameHandler.isDownTankOne()) {
            angleX = 0;
            angleY = 0;
        }

        if (gameHandler.isDownTankOne()) {
            angleX = -(Math.cos(angle));
            angleY = -(Math.sin(angle));
        } else if (!gameHandler.isUpTankOne()) {
            angleX = 0;
            angleY = 0;
        }

        if (gameHandler.isRightTankOne()) {
            tempAngle += 2;
        } else if (!gameHandler.isLeftTankOne()) {
            tempAngle += 0;
        }

        if (gameHandler.isLeftTankOne()) {
            tempAngle -= 2;
        } else if (!gameHandler.isRightTankOne()) {
            tempAngle -= 0;
        }

        if (gameHandler.isShootTankOne()) {
            if (fireRate == 0) {
                gameHandler.addObject(new Bullet(x + 81 / 3 , y + 81 / 3 , angle, 8, gameID.Bullet, this.getId(), gameHandler));
                fireRate = 5;
            } else {
                fireRate -= 1;
            }
        }
        if (tempAngle > 360) {
            tempAngle = 0;
        } else if (tempAngle < 0) {
            tempAngle = 360;
        }

    }

    private void collision() {
        for (int i = 0; i < gameHandler.object.size(); i++) {
            GameObject temp = gameHandler.object.get(i);

            if (temp.getId() == gameID.UnbreakableBlock || temp.getId() == gameID.BreakableBlock ||
                    temp.getId() == gameID.Player2) {
                if (getBounds().intersects(temp.getBounds())) {
                    x += angleX * -4;
                    y += angleY * -4;
                }
            }
            if (temp.getId() == gameID.Upgrade) {
                if (getBounds().intersects(temp.getBounds())) {
                    if (healthBar >= 100){
                        lives += 1;
                        gameHandler.removeObject(temp);
                    }
                    if (healthBar < 100){
                        healthBar += 25;
                        gameHandler.removeObject(temp);
                    }
                }
            }
            if (temp.getId() == gameID.SuperUpgrade) {
                if (getBounds().intersects(temp.getBounds())) {
                    lives += 2;
                    gameHandler.removeObject(temp);
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform old = g.getTransform();
        g.rotate(angle, x + 64 / 2 , y + 64 / 2 );
        g.drawImage(tankObj, (int)x, (int)y, 64, 64, null);
        g.setTransform(old);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

    @Override
    public void miniMap(Graphics graphics, int x, int y) {
        graphics.drawImage(tankObj, (int)this.x / 8 + x, (int)this.y / 8 + y, 64 / 8, 64 / 8, null);
    }
}
