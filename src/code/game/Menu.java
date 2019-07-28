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
    public Spawner spawn;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

        spawn = new Spawner(game, handler);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(mouseOver(mx, my, Game.WIDTH/2-163, Game.HEIGHT/2 - 89, 303, 89)) {
            //Play game
            game.gameState = STATE.Game;

            spawn.spawnTestGame();
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
