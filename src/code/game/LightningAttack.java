import java.awt.*;

public class LightningAttack extends RangedAttack {

    public LightningAttack(int x, int y, int dmg, int range, ID id) {
        super(x, y, dmg, range, id);
    }

    public void tick() {
    }

    public void render(Graphics g) {

        g.setColor(Color.blue);
        g.fillRect(x, y, 1000, 5);
    }

    public Rectangle getBounds() {
        return null;
    }
}
