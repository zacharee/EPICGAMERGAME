import java.awt.*;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 800, HEIGHT = 600;
    private Thread thread;
    private boolean running = false;

    public Game() {

        new Window(WIDTH, HEIGHT, "BudgetScrolls", this);

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void run() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}