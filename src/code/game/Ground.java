import java.awt.*;

public class Ground extends GameObject {
    public Ground(int x, int y, ID id, Game game) {
        super(x, y, id, game);
        isStandable = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 80000, 5);
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }
}
