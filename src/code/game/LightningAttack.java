import java.awt.*;

public class LightningAttack extends GameObject {

    Handler handler;

    public LightningAttack(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.blue);
        g.fillRect(x, y, 400, 5);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 400, 24);
    }

}
