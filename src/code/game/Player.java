import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

        //Set player attributes here - velocity (possibly even in game attributes later)
        velX = 0;
        velY = 0;
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 200, 200);
        g.setColor(Color.RED);
        g.fillRect((x+64), (y+64), 16, 16);
        g.fillRect((x+150), (y+64), 16, 16);
        g.fillRect((x+60), (y+128), 112, 16);
    }

}
