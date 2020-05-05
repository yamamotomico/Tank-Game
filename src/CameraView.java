package src;

public class CameraView  {

    private float x, y;

    public CameraView(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void cameraTankOne(GameObject object){

        x += ((object.getX() - x) - 1000 / 4) * 0.05f;
        y += ((object.getY() - y) - 563 / 2) * 0.05f;

        if (x <= 0) x = 0;
        /*if (x >= 1065) x = 1065;*/
        if (x >= 1550) x = 1550;
        if (y <= 0) y = 0;
        if (y >= 1076) y = 1076;
    }

    public void cameraTankTwo(GameObject object){
        x += ((object.getX() - x) - 3 * 1000 / 4) * 0.05f;
        y += ((object.getY() - y) - 563 / 2) * 0.05f;

        if (x <= -510) x = -510;
        if (x >= 1063) x = 1063;
        if (y <= 0) y = 0;
        if (y >= 1076) y = 1076;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
