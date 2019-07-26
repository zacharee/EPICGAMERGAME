import java.awt.*;
import java.awt.image.BufferStrategy;

//TODO
// fix falling through floor
// fix it so that you can't go left further than where you spawned

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1280, HEIGHT = 720;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private int fps;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "BudgetScrolls", this);

        hud = new HUD();

        //X currently not needed - Y is weird, need fixing/reworking. ID is important though
        handler.addObject(new Background(handler));
        handler.addObject(new Ground(0, 460, ID.Ground));
        handler.addObject(new Platform(690, 350, ID.Platform, "assets/GrassPlatform.png"));
        handler.addObject(new Player(50, 460, ID.Player, handler));
        handler.addObject(new WeakMinion(WIDTH-50, 420, ID.WeakMinion));
        handler.addObject(new DoubleJumpPowerup(WIDTH/2, 400, ID.DoubleJumpPowerup));
        for(int i = 600; i < 5601; i = i + 500) {
            handler.addObject(new HealthPowerUp(i, 400, ID.HealthPowerup));
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //Sometimes it reaches the while(running) before running is set to true, idk why. The sleep fixes it for now
        try {
            thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(running) {
            requestFocus();
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {
        handler.tick();
        hud.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        handler.render(g);

        g.setFont(new Font("Verdanna", 1, 16));
        g.setColor(Color.GREEN);    //FPS counter colour
        g.drawString( fps+" FPS", WIDTH-100,40);


        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp (int var, int min, int max) {
        if(var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}