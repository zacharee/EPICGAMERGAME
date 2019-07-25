import java.awt.*;

public class Ground extends GameObject {

    public Ground(int x, int y, ID id) {
        super(x, y, id);
        isStandable=true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 720, 3);
    }

    public void tick() {

    }


    public void render(Graphics g) {

    }
}
