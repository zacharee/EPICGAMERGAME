import java.awt.*;

public class Player extends GameObject {

    public static int PLAYER_HEALTH = 100;
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

        x = Game.clamp(x, 0, Game.WIDTH - 32);

        if(PLAYER_HEALTH <= 0) {
            handler.removeObject(this);
        }

        if(KeyInput.leftAttack) {
            leftAttack();
        }

        if(KeyInput.rightAttack) {
            rightAttack();
        }

        collision();
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++ ) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.WeakMinion) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    PLAYER_HEALTH -= 2;
                    WeakMinion.WEAK_MINION_HEALTH -=2;
                    if(PLAYER_HEALTH == 0) {
                        System.out.println("You died to a weak minion!");
                        handler.removeObject(this);
                    }
                    if(WeakMinion.WEAK_MINION_HEALTH <= 0) {
                        handler.removeObject(tempObject);
                        Player.PLAYER_HEALTH += 25;
                        System.out.println("You killed a weak minion - nice!");
                    }
                }
            }
            if(tempObject.getID() == ID.DoubleJumpPowerup) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    canDoubleJump=true;
                    handler.removeObject(tempObject);
                }
            }
            if(tempObject.getID() == ID.HealthPowerup) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    PLAYER_HEALTH += 25;
                    handler.removeObject(tempObject);
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

    public void leftAttack() {
        //handler.addObject(new LightningAttack(x, 400, ID.LighteningAttack));
        System.out.println("boom to the left");
    }

    public void rightAttack() {
        //handler.addObject(new LightningAttack(x, 400, ID.LightningAttack));
        System.out.println("boom to the right");
    }

}
