import java.awt.*;
import java.awt.image.BufferStrategy;

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
        //handler.addObject(new Ground(0, 0, ID.Ground));
        handler.addObject(new Player(50, 460, ID.Player, handler));
        handler.addObject(new WeakMinion(WIDTH-50, 452, ID.WeakMinion));
        handler.addObject(new DoubleJumpPowerup(WIDTH/2, 400, ID.DoubleJumpPowerup));
        handler.addObject(new HealthPowerUp(200, 450, ID.HealthPowerup));
        handler.addObject(new HealthPowerUp(WIDTH - 200, 425, ID.HealthPowerup));
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

        //g.setColor(Color.blue);
        //g.fillRect(0,0, WIDTH, HEIGHT);

        g.setFont(new Font("Verdanna", 1, 16));
        g.setColor(Color.GREEN);    //FPS counter colour
        g.drawString( fps+" FPS", WIDTH-100,40);

        handler.render(g);

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