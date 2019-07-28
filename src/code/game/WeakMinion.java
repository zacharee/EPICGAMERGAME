import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeakMinion extends GameObject {

    public static int WEAK_MINION_HEALTH = 100;
    Handler handler;
    private BufferedImage minionImage;

    public WeakMinion(int x, int y, ID id) {
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 0;

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 24, 24);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 140) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH - 42) velX *= -1;

    }

    public void render(Graphics g) {
        try {
            minionImage = ImageIO.read(new File("assets/WeakMinion.png"));
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        g.drawImage(minionImage, x, y, null);
    }

}
