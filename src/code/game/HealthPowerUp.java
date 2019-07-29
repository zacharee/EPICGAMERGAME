import java.awt.*;

public class HealthPowerUp extends GameObject {
    private boolean visible = true;

    public HealthPowerUp(int x, int y, ID id, Game game) {
        super(x, y, id, game);
    }

    public void tick() {
    }

    public void render(Graphics g) {
        if (visible) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 10, 10);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}