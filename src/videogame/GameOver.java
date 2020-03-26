/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Rodrigo
 */
class GameOver extends Item {

    public GameOver(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.over, getX(), getY(), getWidth(), getHeight(), null);

    }
}
