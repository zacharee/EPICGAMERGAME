import java.awt.*;

public class DoubleJumpPowerup extends GameObject {
    private boolean visible=true;
    public void tick() {
        if(Player.canDoubleJump) {
            visible=false;
        }
    }
    public void render(Graphics g) {
        if(visible) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 10, 10);
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
    public DoubleJumpPowerup(int x, int y, ID id) {
        super(x, y, id);

    }

}
