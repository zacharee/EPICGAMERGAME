import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Game game;
    public static boolean aDown = false, dDown = false, rightAttack = false, leftAttack = false, rightDown = false, leftDown = false;

    public KeyInput(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < game.handler.object.size(); i++) {
            GameObject tempObject = game.handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //Key events for the player

                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-3);
                    aDown = true;
                }

                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(+3);
                    dDown = true;
                }

                if (key == KeyEvent.VK_LEFT && !leftDown) {
                    leftAttack = true;
                    leftDown = true;
                }

                if (key == KeyEvent.VK_RIGHT && !rightDown) {
                    rightAttack = true;
                    rightDown = true;
                }

                if (key == KeyEvent.VK_SPACE) {
                    game.player.jump();
                }

                if (key == KeyEvent.VK_SHIFT) {
                    if (!game.player.isFalling && tempObject.getVelX() < 5 && tempObject.getVelX() > -5)
                        tempObject.setVelX(tempObject.getVelX() * 2);
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < game.handler.object.size(); i++) {
            GameObject tempObject = game.handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //Key events for the player

                if (key == KeyEvent.VK_A) {
                    if (dDown) tempObject.setVelX(+3);
                    else tempObject.setVelX(0);
                    aDown = false;
                }

                if (key == KeyEvent.VK_D) {
                    if (aDown) tempObject.setVelX(-3);
                    else tempObject.setVelX(0);
                    dDown = false;
                }

                if (key == KeyEvent.VK_LEFT) {
                    game.player.leftAttackFade = true;
                    leftDown = false;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    game.player.rightAttackFade = true;
                    rightDown = false;
                }

                if (key == KeyEvent.VK_SPACE) {
                    //ground level is 460, make jump for key release work plz xx
                }

                if (key == KeyEvent.VK_SHIFT) {
                    if (!game.player.isFalling) tempObject.setVelX(tempObject.getVelX() / 2);
                }
            }
        }
    }
}
