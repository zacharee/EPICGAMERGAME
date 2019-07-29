import java.awt.*;

public class DoubleJumpPowerup extends GameObject {
    private boolean visible = true;

    public DoubleJumpPowerup(int x, int y, ID id, Game game) {
        super(x, y, id, game);
    }

    public void tick() {
        if (game.player.canDoubleJump) {
            visible = false;
        }
    }

    public void render(Graphics g) {
        if (visible) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 10, 10);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
