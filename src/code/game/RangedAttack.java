import java.awt.*;

public abstract class RangedAttack extends Attack {
    public RangedAttack(int x, int y, int dmg, int range, ID id) {
        super(x, y, dmg, range, id);

    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public Rectangle getBounds() {
        return null;
    }

}
