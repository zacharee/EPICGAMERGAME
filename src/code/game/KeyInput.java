import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    static boolean aDown=false, dDown=false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player) {
                //Key events for the player

                if(key == KeyEvent.VK_A) tempObject.setVelX(-5); aDown=true;

                if(key == KeyEvent.VK_D) tempObject.setVelX(+5); dDown=true;

                if(key == KeyEvent.VK_SPACE) {
                    tempObject.setVelY(-5);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (tempObject.getY() > 460) {
                                tempObject.setVelY(0);
                                tempObject.setY(460);
                                timer.cancel();
                            }
                            tempObject.setVelY(tempObject.getVelY() + 0.5);
                        }
                    }, 0, 50);

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

                if(key == KeyEvent.VK_A) {
                    if(dDown) tempObject.setVelX(+5);
                    else tempObject.setVelX(0);
                    aDown=false;
                }
                if(key == KeyEvent.VK_D) {
                    if(aDown) tempObject.setVelX(-5);
                    else tempObject.setVelX(0);
                    dDown=false;
                }
                if(key == KeyEvent.VK_SPACE) {
                    //ground level is 460, make jump for key release work plz xx
                }
            }
        }
    }
}
