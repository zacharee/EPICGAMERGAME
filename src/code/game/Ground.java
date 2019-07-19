import java.awt.*;

public class Ground extends GameObject {

    public Ground(int x, int y, ID id) {
        super(x, y, id);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 0, 0);
    }

    public void tick() {

    }


    public void render(Graphics g) {
        g.setColor(new Color(144, 108, 63));
        g.fillRect(0, 496, Game.WIDTH, 140);
        g.setColor(Color.green);
        g.fillRect(0, 476, Game.WIDTH, 20);
    }
}
