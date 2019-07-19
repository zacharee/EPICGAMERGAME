import java.awt.*;

public class Ground extends GameObject {

    public Ground(int x, int y, ID id) {
        super(x, y, id);

    }


    public void tick() {

    }


    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 476,Game.WIDTH, 140);
    }
}
