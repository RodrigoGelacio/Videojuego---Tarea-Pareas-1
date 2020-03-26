/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationIdle;
    private int control;
    private Game game;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y,width,height);
        this.direction = direction;
        this.game = game;
        
        this.animationUp = new Animation(Assets.up, 100);
        this.animationDown = new Animation(Assets.down, 100);
        this.animationLeft = new Animation(Assets.left, 100);
        this.animationRight = new Animation(Assets.right, 100);
        this.animationIdle = new Animation(Assets.idle, 100);
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
   if(game.getKeyManager().right){
            this.animationRight.tick();
            setX(getX() + 3);
            control = 1;
        }
                if(game.getKeyManager().left){
            this.animationLeft.tick();
            setX(getX() - 3);
            control = 3;
        }
        if(game.getKeyManager().up){
            this.animationUp.tick();
            setY(getY() - 3);
            control = 2;
        }
        if(game.getKeyManager().down){
            this.animationDown.tick();
            setY(getY() + 3);
            control = 4;
        }
        if(game.getKeyManager().up == false && game.getKeyManager().down == false
        && game.getKeyManager().left == false && game.getKeyManager().right == false){
            animationIdle.tick();
            control = 0;
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
