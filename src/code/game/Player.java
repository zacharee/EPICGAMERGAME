import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    static boolean canDoubleJump=false, isFalling=false;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 16);

        collision();
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++ ) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.WeakMinion) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    //Collision code oioi
                    System.out.println("bang ouch");
                    HUD.HEALTH -= 2;

                }
            }
            if(tempObject.getID() == ID.DoubleJumpPowerup) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    canDoubleJump=true;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 16, 16);
        g.setColor(Color.RED);
        g.fillRect((x+4), (y+4), 2, 2);
        g.fillRect((x+10), (y+4), 2, 2);
        g.fillRect((x+4), (y+10), 8, 2);
    }

}
