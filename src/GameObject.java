package src;

import java.awt.*;

public abstract class GameObject {


    protected double angle;
    protected int height;
    protected int width;
    protected double x, y;
    protected float velocityX = 0, velocityY = 0;
    protected gameID id;

    protected int healthBar;
    protected int lives;

    public GameObject(double x, double y, gameID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
    public abstract Rectangle getBounds();

    public gameID getId() {
        return id;
    }

    public void setId(gameID id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public int getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(int healthBar) {
        this.healthBar = healthBar;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public abstract void miniMap(Graphics graphics, int x, int y);
}
