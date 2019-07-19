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

        x = Game.clamp(x, 0, Game.WIDTH - 16);
        y = Game.clamp(y, 0, Game.HEIGHT - 140);
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x-50, y, 16, 16);
        g.setColor(Color.RED);
        g.fillRect((x-46), (y+4), 2, 2);
        g.fillRect((x-40), (y+4), 2, 2);
        g.fillRect((x-46), (y+10), 8, 2);
    }

}
