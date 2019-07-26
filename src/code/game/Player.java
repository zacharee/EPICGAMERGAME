import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {

    public static int PLAYER_HEALTH = 100, PLAYER_WIDTH=22, PLAYER_HEIGHT=54;
    Handler handler;
    public static boolean canDoubleJump=false, isFalling=false, rightAttackFade = false, leftAttackFade = false;
    public BufferedImage playerImage;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        Background.player=this;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y-PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);

        if(PLAYER_HEALTH <= 0) {
            System.out.println("You died!");
            handler.removeObject(this);
        }

        if(KeyInput.rightAttack) {
            System.out.println("Right attacking");
            rightAttack();
        }

        if(KeyInput.leftAttack) {
            System.out.println("left attacking");
            leftAttack();
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
                    if(WeakMinion.WEAK_MINION_HEALTH <= 0) {
                        System.out.println("You killed a weak minion");
                        handler.object.remove(tempObject);
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
            if(tempObject.getID()==ID.Platform) {
                Platform platform = (Platform) tempObject;
                if(getBounds().intersects(platform.getBounds())) {
                    if(getX()<platform.getX()&&getY()>platform.getY()+1) setVelX(0);
                    if(getX()>platform.getX()+118&&getY()>platform.getY()+1) setVelX(0);
                    if(getY()>platform.getY()+16) setVelY(1);
                    if(getY()<platform.getY()+16) {setVelY(0);setY(platform.getY());}
                }
            }
            if(tempObject.isStandable&&getBounds().intersects(tempObject.getBounds())) {
                isFalling=false;
                KeyInput.doubleJump=false;
                KeyInput.timer.cancel();
            }
            if(tempObject.getID() == ID.LighteningAttack) {
                if (handler.object.get(4).getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("Hit!");
                    WeakMinion.WEAK_MINION_HEALTH -= 2;
                    if(WeakMinion.WEAK_MINION_HEALTH <= 0) {
                        System.out.println("You killed a weak minion");
                        handler.object.remove(handler.object.get(4));
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        try {
            playerImage = ImageIO.read(new File("assets/character.png"));
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        if (x > 630) x = 631;
        if (x < 380) x = 379;
        if (this.velX < 0) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-playerImage.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            playerImage = op.filter(playerImage, null);
            g.drawImage(playerImage, x, y - PLAYER_HEIGHT, null);
        } else {
            g.drawImage(playerImage, x, y - PLAYER_HEIGHT, null);
        }
    }

    public void leftAttack() {
        handler.addObject(new LightningAttack(x-LightningAttack.range, y - 24, ID.LighteningAttack, handler));
        KeyInput.leftAttack = false;
    }

    public void rightAttack() {
        handler.addObject(new LightningAttack(x, y - 24, ID.LighteningAttack, handler));
        KeyInput.rightAttack = false;
    }
}