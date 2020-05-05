package src;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    static LinkedList<GameObject> object = new LinkedList<GameObject>();

    //Tank One
    private boolean upTankOne = false;
    private boolean downTankOne = false;
    private boolean rightTankOne = false;
    private boolean leftTankOne = false;
    private boolean shootTankOne = false;

    //Tank Two
    private boolean upTankTwo = false;
    private boolean downTankTwo = false;
    private boolean rightTankTwo = false;
    private boolean leftTankTwo = false;
    private boolean shootTankTwo = false;

    public void tick(){
        //tick all game objects
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i); //storing

            temp.tick(); // calls
        }

    }

    public void renderTankOne(Graphics graphics, CameraView cam){
        for (int i = 0; i < object.size(); i++) {
        GameObject temp = object.get(i); //storing
            if(temp.getX() < cam.getX() + 481){
        temp.render(graphics);
            } // calls

    }
}
    public void renderTankTwo(Graphics graphics, CameraView cam) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i); //storing
            if (temp.getX() > cam.getX() + 481) {
                temp.render(graphics);
            } // calls
        }
    }
    public static void miniMap(Graphics graphics, int x, int y) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.miniMap(graphics, x, y);
        }
    }

    public void addObject(GameObject x){
        object.add(x);
    }

    public void removeObject(GameObject x){
        object.remove(x);
    }

    public boolean isUpTankOne() {
        return upTankOne;
    }

    public void setUpTankOne(boolean upTankOne) {
        this.upTankOne = upTankOne;
    }

    public boolean isDownTankOne() {
        return downTankOne;
    }

    public void setDownTankOne(boolean downTankOne) {
        this.downTankOne = downTankOne;
    }

    public boolean isRightTankOne() {
        return rightTankOne;
    }

    public void setRightTankOne(boolean rightTankOne) {
        this.rightTankOne = rightTankOne;
    }

    public boolean isLeftTankOne() {
        return leftTankOne;
    }

    public void setLeftTankOne(boolean leftTankOne) {
        this.leftTankOne = leftTankOne;
    }

    public boolean isShootTankOne() {
        return shootTankOne;
    }

    public void setShootTankOne(boolean shootTankOne) {
        this.shootTankOne = shootTankOne;
    }

    public boolean isUpTankTwo() {
        return upTankTwo;
    }

    public void setUpTankTwo(boolean upTankTwo) {
        this.upTankTwo = upTankTwo;
    }

    public boolean isDownTankTwo() {
        return downTankTwo;
    }

    public void setDownTankTwo(boolean downTankTwo) {
        this.downTankTwo = downTankTwo;
    }

    public boolean isRightTankTwo() {
        return rightTankTwo;
    }

    public void setRightTankTwo(boolean rightTankTwo) {
        this.rightTankTwo = rightTankTwo;
    }

    public boolean isLeftTankTwo() {
        return leftTankTwo;
    }

    public void setLeftTankTwo(boolean leftTankTwo) {
        this.leftTankTwo = leftTankTwo;
    }

    public boolean isShootTankTwo() {
        return shootTankTwo;
    }

    public void setShootTankTwo(boolean shootTankTwo) {
        this.shootTankTwo = shootTankTwo;
    }
}
