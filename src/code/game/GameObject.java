import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected double velX, velY;
    public boolean isStandable=false;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public GameObject() {}

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setID(ID id) {
        this.id = id;
    }
    public ID getID() {
        return id;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }
}
