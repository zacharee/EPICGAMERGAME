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
                if(tempObject.getX()>630&&tempObject.getVelX()>0) {
                    scrollLTR();
                }
                if(tempObject.getX()<380&&tempObject.getVelX()<0) {
                    scrollRTL();
                }
            }
        }
    }
    public void render(Graphics g) {
        g.drawImage(backgroundImage, b1x, -400, null);
        g.drawImage(backgroundImage2, b2x, -400, null);
    }
    public Rectangle getBounds() {
        return null;
    }
    public void scrollLTR() {
        if(b1x<-600) b2x=b1x+1920;
        if(b2x<-600) b1x=b2x+1920;
        b1x-=3;
        b2x-=3;
        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
                if(tempObject.getID()!=ID.Player) {
                    tempObject.setX(tempObject.getX()-3);
                }
        }
    }
    public void scrollRTL() {
        if(b1x>-10) b2x=b1x-1920;
        if(b2x>-10) b1x=b2x-1920;
        b1x+=3;
        b2x+=3;
        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID()!=ID.Player) {
                tempObject.setX(tempObject.getX()+3);
            }
        }
    }
}
