/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Rodrigo Gelacio
 */
public class Good extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private Animation animationFriend;
    
    public Good(int x, int y, int direction, int width, int height, Game game) {
        super(x, y,width,height);
        this.direction = direction;
        this.game = game;
        this.animationFriend = new Animation(Assets.moveFriend,100);
    }

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    


    @Override
    public void tick() {
        // moving player depending on flags
           setX(getX() - ((int) (Math.random() * 4) + 2));
           this.animationFriend.tick();
           //game.jump();
        
        // reset x position and y position if colision
        /*if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }*/
        /*if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }*/
        /*if (getY() <= -20) {
            setY(-20);
        }*/
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationFriend.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
