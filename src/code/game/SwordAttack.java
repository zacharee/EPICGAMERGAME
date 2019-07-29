import java.awt.*;

public class SwordAttack extends GameObject {
    public static int range = 42;
    public static int swordDamage = 10;

    public SwordAttack(int x, int y, ID id, Game game) {
        super(x, y, id, game);
    }

    public void tick() {
        if (game.player.rightAttackFade) {
            fade();
        } else if (game.player.leftAttackFade) {
            fade();
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(52, 204, 255));
        if (KeyInput.leftDown) {
            g.fillRect(game.player.getX() - range, game.player.getY() - 35, range, 5);
        } else {
            g.fillRect(game.player.getX() + 20, game.player.getY() - 35, range, 5);
        }
    }

    public Rectangle getBounds() {
        if (KeyInput.leftDown) {
            return new Rectangle(game.player.getX() - range, game.player.getY() - 35, range, 24);
        } else {
            return new Rectangle(game.player.getX() + 20, game.player.getY() - 35, range, 24);
        }
    }

    public void fade() {
        game.handler.removeObject(this);
        game.player.rightAttackFade = false;
        game.player.leftAttackFade = false;
    }


}
