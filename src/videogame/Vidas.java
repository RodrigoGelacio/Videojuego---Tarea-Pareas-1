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
class Vidas extends Item{
    private int vidas;
    
    public Vidas(int x, int y, int width, int height) {
        super(x, y,width,height);
        vidas=((int) Math.random() * 7) + 5;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawString("Vidas: " + vidas, x, y);
    }
}
