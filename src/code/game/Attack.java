import java.awt.*;

public abstract class Attack {
    protected int x;
    protected int y;
    protected int dmg;
    protected int range;
    protected ID id;

    public Attack(int x, int y, int dmg, int range, ID id) {
        this.x = x;
        this.y = y;
        this.dmg = dmg;
        this.range = range;
        this.id = id;
    }

    public Attack() {
    }

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

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDmg() {
        return dmg;
    }

    public int getRange() {
        return range;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }
}
