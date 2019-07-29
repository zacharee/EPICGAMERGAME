public class Spawner {
    Game game;

    public Spawner(Game game) {
        this.game = game;
    }

    public void spawn() {
        //will be used to spawn in map loading and additional objects
    }

    public void spawnTestGame() {
        Handler handler = game.handler;

        handler.addObject(new Background(game));
        handler.addObject(new Ground(0, 460, ID.Ground, game));
        handler.addObject(new Platform(400, 350, ID.Platform, "assets/GrassPlatform.png", game));
        handler.addObject(game.player);
        handler.addObject(new WeakMinion(Game.WIDTH - 50, 460, ID.WeakMinion, game));
        handler.addObject(new DoubleJumpPowerup(Game.WIDTH / 2, 400, ID.DoubleJumpPowerup, game));
        for (int i = 600; i < 5601; i = i + 500) {
            handler.addObject(new HealthPowerUp(i, 400, ID.HealthPowerup, game));
        }
    }
}
