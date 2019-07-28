import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends MouseAdapter {

    public BufferedImage menuImage;
    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        System.out.println(mx);
        System.out.println(my);

        if(mouseOver(mx, my, Game.WIDTH/2-163, Game.HEIGHT/2 - 89, 303, 89)) {
            //Play game
            game.gameState = STATE.Game;
            System.out.println("Active");
            handler.addObject(new Background(handler));
            handler.addObject(new Ground(0, 460, ID.Ground));
            handler.addObject(new Platform(400, 350, ID.Platform, "assets/GrassPlatform.png"));
            handler.addObject(Game.player);
            handler.addObject(new WeakMinion(Game.WIDTH - 50, 460, ID.WeakMinion));
            handler.addObject(new DoubleJumpPowerup(Game.WIDTH/2, 400, ID.DoubleJumpPowerup));
            for(int i = 600; i < 5601; i = i + 500) {
                handler.addObject(new HealthPowerUp(i, 400, ID.HealthPowerup));
            }
        }

        if(mouseOver(mx, my, Game.WIDTH/2-131, Game.HEIGHT/2 + 7, 234, 73)) {
            //Options
        }

        if(mouseOver(mx, my, Game.WIDTH/2-97, Game.HEIGHT/2 + 86, 166, 53)) {
            //quit
            System.exit(1);
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        try {
            menuImage = ImageIO.read(new File("assets/menuImage.png"));
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        g.drawImage(menuImage, 150, 0, null);

        g.setColor(Color.green);
    }
}
