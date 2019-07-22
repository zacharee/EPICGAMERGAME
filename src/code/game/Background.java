import java.awt.*;

public class Background extends GameObject {
    public Image backgroundImage, backgroundImage2;
    public int b1x=0, b2x;
    public Handler handler;
    public Background(Handler handler) {
        backgroundImage = Toolkit.getDefaultToolkit().createImage("assets/8-bit-background-4.jpg");
        backgroundImage2 = Toolkit.getDefaultToolkit().createImage("assets/8-bit-background-4.jpg");
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
        g.drawImage(backgroundImage, b1x, -400, null);
    }
    public Rectangle getBounds() {
        return null;
    }
    public void scrollRTL() {
        b1x--;
    }
}
