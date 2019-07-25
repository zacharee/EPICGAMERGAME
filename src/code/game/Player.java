import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObject {

    public static int PLAYER_HEALTH = 100, PLAYER_WIDTH=22, PLAYER_HEIGHT=54;
    Handler handler;
    static boolean canDoubleJump=false, isFalling=false, doubleJump=false;
    public BufferedImage playerImage;
    public static Timer timer;

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
            handler.removeObject(this);
        }

        if(KeyInput.leftAttack) {
            leftAttack();
        } else {
            handler.removeAttack(new LightningAttack(x, 425, 100, 500, ID.LighteningAttack));
        }

        if(KeyInput.rightAttack) {
            rightAttack();
        }else {
            handler.removeAttack(new LightningAttack(x, 425, 100, 500, ID.LighteningAttack));
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
            if(tempObject.getID()==ID.Platform) {
                Platform platform = (Platform) tempObject;
                if(getBounds().intersects(platform.getBounds())) {
                    if(getX()<platform.getX()&&getY()>platform.getY()+1) setVelX(0);
                    if(getX()>platform.getX()+118&&getY()>platform.getY()+1) setVelX(0);
                    if(getY()>platform.getY()+16) setVelY(1);
                    if(getY()<platform.getY()+16) {setVelY(0);setY(platform.getY());}
                }
            }
        }
    }

    public void render(Graphics g) {
        try {
            playerImage = ImageIO.read(new File("assets/character.png"));
        }
        catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        if(x>630) x=631;
        if(x<380) x=379;
        if(this.velX<0) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-playerImage.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            playerImage = op.filter(playerImage, null);
            g.drawImage(playerImage, x, y-PLAYER_HEIGHT, null);
        }
        else {
            g.drawImage(playerImage, x, y-PLAYER_HEIGHT, null);
        }
    }

    public void leftAttack() {
        handler.addAttack(new LightningAttack(x, 425, 100, 500, ID.LighteningAttack));
        System.out.println("boom to the left");
    }

    public void rightAttack() {
        handler.addAttack(new LightningAttack(x, 425, 100, 500, ID.LighteningAttack));
        System.out.println("boom to the right");
    }

    public GameObject isStanding() {
        for(int i=0; i<handler.object.size(); i++) {
            GameObject platform = handler.object.get(i);
            if(platform.isStandable&&getBounds().intersects(platform.getBounds())) {
                System.out.println("true");
                return platform;
            }
        }
        return null;
    }
    public void jump() {
        if(isFalling&&canDoubleJump&&!doubleJump) {
            doubleJump=true;
            timer.cancel();
            timer = new Timer();
            setVelY(-5);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    GameObject temp = isStanding();
                    if(temp!=null) {
                        isFalling=false;
                        doubleJump=false;
                        setVelY(0);
                        setY(temp.getY());
                        timer.cancel();
                    }
                    else {
                        setVelY(getVelY()+0.5);
                    }
                }

            }, 0, 50);
        }
        else if(!isFalling) {
            isFalling=true;
            setVelY(-5);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    GameObject temp = isStanding();
                    if(temp!=null) {
                        isFalling=false;
                        setVelY(0);
                        setY(temp.getY());
                        timer.cancel();
                    }
                    else {
                        setVelY(getVelY()+0.5);
                    }
                }
            }, 0, 50);
        }
    }
}
