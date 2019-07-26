import java.awt.*;

public class LightningAttack extends GameObject {

    Handler handler;
    public static int range = 250;

    public LightningAttack(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        if(Player.rightAttackFade) {
            fade();
        } else if (Player.leftAttackFade) {
            fade();
        }
    }

    public void render(Graphics g) {

        g.setColor(new Color(52, 204, 255));
        g.fillRect(x, y, range, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, range, 24);
    }

    public void fade() {
        handler.object.remove(this);
        Player.rightAttackFade = false;
        Player.leftAttackFade = false;
    }

}
