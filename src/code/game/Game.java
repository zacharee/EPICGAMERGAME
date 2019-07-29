import java.awt.*;
import java.awt.image.BufferStrategy;

//TODO
// fix it so that you can't go left further than where you spawned
// Fix movement - double jump inconsistencies and velocity changes
// Make meilueghghg sword attacks be 1 quick swipe instead of a constant one*/

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1280, HEIGHT = 720;
    public static Player player;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private int fps;
    private Menu menu;

    public STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        menu = new Menu(this, handler);
        hud = new HUD();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);

        new Window(WIDTH, HEIGHT, "BudgetScrolls", this);

        player = new Player(410, 250, ID.Player, handler);

        System.out.println("Yes");
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
        double amountOfTicks = 50.0;
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

        if(gameState == STATE.Game) {
            hud.tick();
        } else if (gameState == STATE.Menu){
            menu.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        handler.render(g);

        if(gameState == STATE.Game) {
            hud.render(g);
            g.setFont(new Font("Verdana", 1, 16));
            g.setColor(Color.GREEN);    //FPS counter colour
            g.drawString( fps+" FPS", WIDTH-128,40);
        } else if (gameState == STATE.Menu){
            menu.render(g);

            //Theory number 2
//            if(Menu.playOutline) {
//                System.out.println("playOutline");
//                g.setColor(Color.green);
//                g.drawRect(Game.WIDTH/2-163, Game.HEIGHT/2 - 89, 303, 89);
//            } else if (Menu.optionOutline) {
//                System.out.println("optionOutline");
//                g.setColor(Color.green);
//                g.drawRect(Game.WIDTH/2-131, Game.HEIGHT/2 + 7, 234, 73);
//            } else if (Menu.quitOutline) {
//                System.out.println("quitOutline");
//                g.setColor(Color.green);
//                g.drawRect(Game.WIDTH/2-97, Game.HEIGHT/2 + 86, 166, 53);
//            }
        }

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