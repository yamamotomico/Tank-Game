package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler gameHandler;

    public KeyInput(Handler gameHandler){

        this.gameHandler = gameHandler;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < gameHandler.object.size(); i++) {
            GameObject temp = gameHandler.object.get(i);

            //checks if its Tank One
            if(temp.getId() == gameID.Player1){
                if (key == KeyEvent.VK_W) gameHandler.setUpTankOne(true);
                if (key == KeyEvent.VK_S) gameHandler.setDownTankOne(true);
                if (key == KeyEvent.VK_A) gameHandler.setLeftTankOne(true);
                if (key == KeyEvent.VK_D) gameHandler.setRightTankOne(true);
                if (key == KeyEvent.VK_SPACE) gameHandler.setShootTankOne(true);
            }

            //checks if its Tank Two
            //using the arrow keys
            if(temp.getId() == gameID.Player2){
                if (key == KeyEvent.VK_UP) gameHandler.setUpTankTwo(true);
                if (key == KeyEvent.VK_DOWN) gameHandler.setDownTankTwo(true);
                if (key == KeyEvent.VK_LEFT) gameHandler.setLeftTankTwo(true);
                if (key == KeyEvent.VK_RIGHT) gameHandler.setRightTankTwo(true);
                if (key == KeyEvent.VK_ENTER) gameHandler.setShootTankTwo(true);
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < gameHandler.object.size(); i++) {
            GameObject temp = gameHandler.object.get(i);

            //checks if its Tank One
            if(temp.getId() == gameID.Player1){
                if (key == KeyEvent.VK_W) gameHandler.setUpTankOne(false);
                if (key == KeyEvent.VK_S) gameHandler.setDownTankOne(false);
                if (key == KeyEvent.VK_A) gameHandler.setLeftTankOne(false);
                if (key == KeyEvent.VK_D) gameHandler.setRightTankOne(false);
                if (key == KeyEvent.VK_SPACE) gameHandler.setShootTankOne(false);
            }

            //checks if its Tank Two
            //using the arrow keys
            if(temp.getId() == gameID.Player2){
                if (key == KeyEvent.VK_UP) gameHandler.setUpTankTwo(false);
                if (key == KeyEvent.VK_DOWN) gameHandler.setDownTankTwo(false);
                if (key == KeyEvent.VK_LEFT) gameHandler.setLeftTankTwo(false);
                if (key == KeyEvent.VK_RIGHT) gameHandler.setRightTankTwo(false);
                if (key == KeyEvent.VK_ENTER) gameHandler.setShootTankTwo(false);
            }
        }
    }



}
