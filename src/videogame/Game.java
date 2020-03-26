/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Graphics j;
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Scoreboard scoreboard;
    private Vidas vidas;
    private LinkedList<Enemy> lista;   //use enemy
    private KeyManager keyManager;  // to manage the keyboard
    private int counter;
    private LinkedList<Good> listaGood;
    //private GameOver gameover;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager(this);
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        scoreboard = new Scoreboard(10, getHeight() - 10, 100, 100);
//        gameover = new GameOver(getWidth()/2, getHeight()/2,100,100);
        counter = 0;
        vidas = new Vidas(10, getHeight() - 30, 100, 100);
        player = new Player(getWidth() / 2 - 50, getHeight() / 2 - 50, 1, 80, 80, this);
        lista = new LinkedList<Enemy>();
        listaGood = new LinkedList<Good>();
        int azar = ((int) Math.random() * 8) + 6;
        int azar2 = ((int) Math.random() * 15) + 10;
        for (int i = 1; i <= azar; i++) {
            //lol[i] = new Enemy(getWidth() - 100, (int) (Math.random() * getHeight()), 1, 100, 100, this);
            Enemy enemy = new Enemy((int) (Math.random() * 1) - 100, (int) (Math.random() * getHeight()) - 100, 1, 80, 80, this);
            for (Enemy e : lista) {
                while (enemy.getY() >= e.getY() && enemy.getY() <= e.getY() + 200) {
                    enemy = new Enemy((int) (Math.random() * 1) - 100, (int) (Math.random() * getHeight()) - 100, 1, 80, 80, this);
                }
            }
            lista.add(enemy);
        }
        for (int i = 1; i <= azar2; i++) {
            //lol[i] = new Enemy(getWidth() - 100, (int) (Math.random() * getHeight()), 1, 100, 100, this);
            Good good = new Good((int) (Math.random() * getWidth()+100) + getWidth(), (int) (Math.random() * getHeight()) - 100, 1, 80, 80, this);
            for (Good g : listaGood) {
                while (good.getY() >= g.getY() && good.getY() <= g.getY() + 200) {
                    g = new Good((int) (Math.random() * getWidth()+100) + getWidth(), (int) (Math.random() * getHeight()) - 100, 1, 80, 80, this);
                }
            }
            listaGood.add(good);
        }
        display.getJframe().addKeyListener(keyManager);
        Assets.backSound.setLooping(true);
        Assets.backSound.play();
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void jump() {
        Assets.jump.play();
    }

    public void beep() {
        Assets.gunShot.play();
    }

    private void tick() {
        keyManager.tick();
        // avancing player with colision
        player.tick();
        for (Enemy e : lista) {
            e.tick();
            if (e.getX() + 80 >= getWidth()) {
                e.setX((int) (Math.random() * 1) - 100);
                e.setY((int) (Math.random() * getHeight()) - 100);
            }
            if (player.collision(e)) {
                counter++;
                if(counter == 3){
                    vidas.setVidas(vidas.getVidas() - 1);
                    counter =0;
                }
                if(vidas.getVidas() == 0){
                    beep();
                    stop();
                }
              e.setX((int) (Math.random() * 1) - 100);
              e.setY((int) (Math.random() * getHeight()) - 100);
            }
        }   
        for(Good l: listaGood){
            l.tick();
            if(player.collision(l)){
                scoreboard.setScore(scoreboard.getScore() + 10);
                l.setY((int) (Math.random() * getHeight()) - 100);
                l.setX((int) (Math.random() * getWidth()+100) + getWidth());
                jump();
            }
            if(l.getX() < 0){
                 l.setX((int) (Math.random() * getWidth()+100) + getWidth());
                 l.setY((int) (Math.random() * getHeight()) - 100);
            }
        }
        scoreboard.tick();
        vidas.tick();
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            scoreboard.render(g);
            vidas.render(g);
            player.render(g);
            for (Enemy e : lista) {
                e.render(g);
            }
            for(Good l: listaGood){
                l.render(g);
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
