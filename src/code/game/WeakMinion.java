import java.awt.*;

public class WeakMinion extends GameObject{

    public WeakMinion(int x, int y, ID id) {
        super(x, y, id);

        velX = 1;
        velY = 0;

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 24, 24);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 140) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 24, 24);
    }

}
