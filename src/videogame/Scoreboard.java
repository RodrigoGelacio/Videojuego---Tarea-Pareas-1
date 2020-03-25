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
class Scoreboard extends Item{
    private int score;
    
    public Scoreboard(int x, int y, int width, int height) {
        super(x, y, width, height);
        score=0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawString("Score: " + score, x, y);
    }
}
