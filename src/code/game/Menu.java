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
    public Spawner spawn;
    public static boolean playOutline = false, optionOutline = false, quitOutline = false;

    int mX;
    int mY;

    public Menu(Game game) {
        this.game = game;

        spawn = new Spawner(game);
    }

    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
//        System.out.println(mX);

        if (mouseOver(mX, mY, Game.WIDTH / 2 - 163, Game.HEIGHT / 2 - 89, 303, 89)) {
            playOutline = true;
            optionOutline = false;
            quitOutline = false;
        }

        if (mouseOver(mX, mY, Game.WIDTH / 2 - 131, Game.HEIGHT / 2 + 7, 234, 73)) {
            playOutline = false;
            optionOutline = true;
            quitOutline = false;
        }

        if (mouseOver(mX, mY, Game.WIDTH / 2 - 97, Game.HEIGHT / 2 + 86, 166, 53)) {
            playOutline = false;
            optionOutline = false;
            quitOutline = true;
        }

    }

    public void mousePressed(MouseEvent e) {
        System.out.println("MousePressed");

        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my, Game.WIDTH / 2 - 163, Game.HEIGHT / 2 - 89, 303, 89)) {
            //Play game
            game.gameState = STATE.Game;

            spawn.spawnTestGame();
        }

        if (mouseOver(mx, my, Game.WIDTH / 2 - 131, Game.HEIGHT / 2 + 7, 234, 73)) {
            //Options
        }

        if (mouseOver(mx, my, Game.WIDTH / 2 - 97, Game.HEIGHT / 2 + 86, 166, 53)) {
            //quit
            System.exit(1);
        }
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("MouseReleased");

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
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
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        try {
            menuImage = ImageIO.read(new File("assets/menuImage.png"));
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        g.drawImage(menuImage, 150, 0, null);

        if (Menu.playOutline) {
            System.out.println("playOutline");
            g.setColor(Color.green);
            g.drawRect(Game.WIDTH / 2 - 163, Game.HEIGHT / 2 - 89, 302, 88);
            g.drawRect(Game.WIDTH / 2 - 164, Game.HEIGHT / 2 - 88, 304, 88);
        } else if (Menu.optionOutline) {
            System.out.println("optionOutline");
            g.setColor(Color.green);
            g.drawRect(Game.WIDTH / 2 - 131, Game.HEIGHT / 2 + 7, 233, 72);
            g.drawRect(Game.WIDTH / 2 - 132, Game.HEIGHT / 2 + 8, 235, 72);
        } else if (Menu.quitOutline) {
            System.out.println("quitOutline");
            g.setColor(Color.green);
            g.drawRect(Game.WIDTH / 2 - 97, Game.HEIGHT / 2 + 86, 165, 52);
            g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 + 87, 167, 52);
        }

    }
}
