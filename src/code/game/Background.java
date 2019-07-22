import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends GameObject {
    public BufferedImage backgroundImage, backgroundImage2;
    public int b1x=0, b2x;
    public Handler handler;
    public Background(Handler handler) {
        try {
            backgroundImage = ImageIO.read(new File("8-bit-background-4.jpg"));
            backgroundImage2 = ImageIO.read(new File("8-bit-background-4.jpg"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.handler=handler;
    }

    public void tick() {
        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID()==ID.Player) {
                if(tempObject.getX()<630&&tempObject.getVelX()>0) {
                    scrollRTL();
                }
            }
        }
    }
    public void render(Graphics g) {
        g.drawImage(backgroundImage, b1x, 720, null);
    }
    public Rectangle getBounds() {
        return null;
    }
    public void scrollRTL() {
        b1x--;
    }
}
