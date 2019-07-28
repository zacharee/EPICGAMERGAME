public class Spawner {

    Game game;
    Handler handler;

    public Spawner(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void spawn() {
        //will be used to spawn in map loading and additional objects
    }

    public void spawnTestGame() {
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
}
