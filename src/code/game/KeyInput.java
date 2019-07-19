import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player) {
                //Key events for the player

                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);

                if(key == KeyEvent.VK_D) tempObject.setVelX(+5);

                if(key == KeyEvent.VK_SPACE) {
                    //ground level is 460 for Y, make jump work plz xx
                    //you can use tempObject.getY(), tempObject.getVelY() (and setters too)
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player) {
                //Key events for the player

                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE) {
                    //ground level is 460, make jump for key release work plz xx
                }
            }
        }
    }
}
