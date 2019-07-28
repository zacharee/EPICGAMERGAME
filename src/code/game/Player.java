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
    public static boolean canDoubleJump=false, isFalling=false, isStanding=true, doubleJump=false, rightAttackFade = false, leftAttackFade = false;
    public BufferedImage playerImage;
    private Timer timer;
    public static boolean canLightningAttack = true, canSwordAttack = false;

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
            rightAttack();
        }

        if(KeyInput.leftAttack) {
            leftAttack();
        }

        collision();
    }

    private void collision() {
        isStanding=false;
        for(int i = 0; i < handler.object.size(); i++ ) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.WeakMinion) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    //Player health declines when in contact with the minion
                    PLAYER_HEALTH -= 2;
                    //WeakMinion.WEAK_MINION_HEALTH -=2;
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
            if(tempObject.isStandable) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    if(getY()<tempObject.getY()+16) {setVelY(0);setY(tempObject.getY()+1);isFalling=false;isStanding=true;}
                    else if(getX()<tempObject.getX()&&getY()>tempObject.getY()+1) setVelX(0);
                    else if(getX()>tempObject.getX()+118&&getY()>tempObject.getY()+1) setVelX(0);
                    else if(getY()>tempObject.getY()+16) setVelY(getVelY()*-1);
                }
            }
            if(tempObject.getID() == ID.LightningAttack) {
                if (handler.object.get(4).getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("Hit!");
                    WeakMinion.WEAK_MINION_HEALTH -= LightningAttack.lightningDamage;
                    if(WeakMinion.WEAK_MINION_HEALTH <= 0) {
                        System.out.println("You killed a weak minion");
                        handler.object.remove(handler.object.get(4));
                    }
                }
            }
            if(tempObject.getID() == ID.SwordAttack) {
                if (handler.object.get(4).getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("Hit!");
                    WeakMinion.WEAK_MINION_HEALTH -= SwordAttack.swordDamage;
                    if(WeakMinion.WEAK_MINION_HEALTH <= 0) {
                        System.out.println("You killed a weak minion");
                        handler.object.remove(handler.object.get(4));
                    }
                }
            }
        }
        if (!isStanding && !isFalling) {
            isFalling = true;
            isStanding = true;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!isFalling) {
                        doubleJump = false;
                        setVelY(0);
                        timer.cancel();
                    } else {
                        setVelY(getVelY() + 0.5);
                    }
                }
            }, 0, 50);
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


    public void jump() {
        if(isFalling&&canDoubleJump&&!doubleJump) {
            doubleJump=true;
            timer.cancel();
            timer = new Timer();
            setVelY(-5);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if(!isFalling) {
                        isStanding=true;
                        doubleJump=false;
                        setVelY(0);
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
                    if(!isFalling) {
                        isStanding=true;
                        setVelY(0);
                        timer.cancel();
                    }
                    else {
                        setVelY(getVelY()+0.5);
                    }
                }
            }, 0, 50);
        }
    }
    public void leftAttack() {
        if(canLightningAttack) {
            handler.addObject(new LightningAttack(x-LightningAttack.range, y - 24, ID.LightningAttack, handler));
        } else if (canSwordAttack) {
            handler.addObject(new SwordAttack(x-SwordAttack.range, y - 24, ID.SwordAttack, handler));
        } else {
            System.out.println("You cannot attack at this time");
        }
        KeyInput.leftAttack = false;
    }

    public void rightAttack() {
        if(canLightningAttack) {
            handler.addObject(new LightningAttack(x, y - 24, ID.LightningAttack, handler));
        } else if (canSwordAttack) {
            handler.addObject(new SwordAttack(SwordAttack.range, y - 24, ID.SwordAttack, handler));
        } else {
            System.out.println("You cannot attack at this time");
        }
        KeyInput.rightAttack = false;
    }
}