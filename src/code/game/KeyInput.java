import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    public static boolean aDown=false, dDown=false, doubleJump=false, rightAttack = false, leftAttack = false, rightDown = false, leftDown = false;
    public static Timer timer = new Timer();

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0 ; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player) {
                //Key events for the player

                if(key == KeyEvent.VK_A) {
                    tempObject.setVelX(-3);
                    aDown = true;
                }

                if(key == KeyEvent.VK_D) {
                    tempObject.setVelX(+3);
                    dDown = true;
                }

                if(key == KeyEvent.VK_LEFT&&!leftDown) {
                    leftAttack = true;
                    leftDown=true;
                }

                if(key == KeyEvent.VK_RIGHT&&!rightDown) {
                    rightAttack = true;
                    rightDown=true;
                }

                if(key == KeyEvent.VK_SPACE) {
                    Game.player.jump();
                }
                if(key == KeyEvent.VK_SHIFT) {
                    if(!Player.isFalling&&tempObject.getVelX()<5&&tempObject.getVelX()>-5) tempObject.setVelX(tempObject.getVelX()*2);
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
                    if(dDown) tempObject.setVelX(+3);
                    else tempObject.setVelX(0);
                    aDown=false;
                }
                if(key == KeyEvent.VK_D) {
                    if(aDown) tempObject.setVelX(-3);
                    else tempObject.setVelX(0);
                    dDown=false;
                }

                if(key == KeyEvent.VK_LEFT) {
                    Player.leftAttackFade = true;
                    leftDown=false;
                }

                if(key == KeyEvent.VK_RIGHT) {
                    Player.rightAttackFade = true;
                    rightDown=false;
                }

                if(key == KeyEvent.VK_SPACE) {
                    //ground level is 460, make jump for key release work plz xx
                }
                if(key == KeyEvent.VK_SHIFT) {
                    if(!Player.isFalling) tempObject.setVelX(tempObject.getVelX()/2);
                }
            }
        }
    }

    public void jump(GameObject tempObject) {
        if(Player.canDoubleJump&&Player.isFalling&&!doubleJump) {
            doubleJump=true;
            timer.cancel();
            Player.isFalling = true;
            tempObject.setVelY(-5);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (tempObject.getY() > 460) {
                        tempObject.setVelY(0);
                        tempObject.setY(460);
                        Player.isFalling = false;
                        doubleJump=false;
                        timer.cancel();
                    }
                    tempObject.setVelY(tempObject.getVelY() + 0.5);
                }
            }, 0, 50);
        }
        else if(!Player.isFalling) {
            Player.isFalling = true;
            tempObject.setVelY(-5);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (tempObject.getY() > 460) {
                        tempObject.setVelY(0);
                        tempObject.setY(460);
                        Player.isFalling = false;
                        timer.cancel();
                    }
                    tempObject.setVelY(tempObject.getVelY() + 0.5);
                }
            }, 0, 50);
        }
    }
}
