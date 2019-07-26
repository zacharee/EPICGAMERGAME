import java.awt.*;

public class Ground extends GameObject {

    public Ground(int x, int y, ID id) {
        super(x, y, id);
        isStandable=true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 80000, 5);
    }

    public void tick() {

    }


    public void render(Graphics g) {

    }
}
