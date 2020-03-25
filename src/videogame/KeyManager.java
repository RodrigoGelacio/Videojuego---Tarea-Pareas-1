/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
    public boolean northWest;      // flag to move up the player
    public boolean northEast;    // flag to move down the player
    public boolean southEast;    // flag to move left the player
    public boolean southWest;   // flag to move right the player
    private Game game;

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager(Game game) {
        keys = new boolean[256];
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        northWest = keys[KeyEvent.VK_A];
        southWest = keys[KeyEvent.VK_Z];
        southEast = keys[KeyEvent.VK_M];
        northEast = keys[KeyEvent.VK_K];
    }
}
