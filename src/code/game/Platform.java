import java.awt.*;

public class Platform extends GameObject {

    public Image texture;

    public Platform(int x, int y, ID id, String textureLocation, Game game) {
        super(x, y, id, game);

        texture = Toolkit.getDefaultToolkit().createImage(textureLocation);
        isStandable = true;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
        //g.fillRect(x,y,128,16);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 128, 16);
    }
}
