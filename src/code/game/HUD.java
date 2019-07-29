import java.awt.*;

public class HUD {
    //Green value is a spectrum from green to red in relation to health
    public int greenValue = 255;

    private Game game;

    public HUD(Game game) {
        this.game = game;
    }

    public void tick() {
        Player player = game.player;

        player.playerHealth = Game.clamp(player.playerHealth, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = player.playerHealth * 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, game.player.playerHealth * 2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(15, 15, 200, 32);
    }
}
