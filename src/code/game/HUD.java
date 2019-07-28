import java.awt.*;

public class HUD {

    public int greenValue = 255;

    public void tick() {

        Player.PLAYER_HEALTH = Game.clamp(Player.PLAYER_HEALTH, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = Player.PLAYER_HEALTH * 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, Player.PLAYER_HEALTH * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(15, 15, 200, 32);
    }

}
