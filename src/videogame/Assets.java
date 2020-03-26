/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage good;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip jump;
    public static BufferedImage over;
    public static BufferedImage spriteHero;
    public static BufferedImage spriteEnemy;
    public static BufferedImage spriteFriend;
    public static BufferedImage upH[];
    public static BufferedImage downH[];
    public static BufferedImage leftH[];
    public static BufferedImage rightH[];
    public static BufferedImage idle[];
    public static BufferedImage moveEnemy[];
    public static BufferedImage moveFriend[];

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background2.png");
        good = ImageLoader.loadImage("/images/good.png");
        player = ImageLoader.loadImage("/images/player.png");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        jump = new SoundClip("/sounds/jump.wav");
        over = ImageLoader.loadImage("/images/over.png");
        spriteHero = ImageLoader.loadImage("/images/link.png");
        spriteEnemy = ImageLoader.loadImage("/images/slime.png");
        spriteFriend = ImageLoader.loadImage("/images/slimeGood.png");
        Spritesheet spritesheetEnemy = new Spritesheet(spriteEnemy);
        Spritesheet spritesheetFriend = new Spritesheet(spriteFriend);
        Spritesheet spritesheet = new Spritesheet(spriteHero);
        idle = new BufferedImage[3];
        upH = new BufferedImage[10];
        downH = new BufferedImage[10];
        leftH = new BufferedImage[10];
        rightH = new BufferedImage[10];
        moveEnemy = new BufferedImage[4];
        moveFriend = new BufferedImage[4];
        
         for(int i=0; i < 3; i++){
               idle[i] = spritesheet.crop(i * 96, 0, 96, 96);    
        }
        for (int i=0; i < 10; i++){
            upH[i] = spritesheet.crop(i * 96, 624, 96, 96);
            downH[i] = spritesheet.crop(i * 96, 416, 96, 96);
            rightH[i] = spritesheet.crop(i * 96, 728, 96, 96); 
            leftH[i] = spritesheet.crop(i * 96, 520, 96, 96);
        }
        for (int i=0; i < 4; i++){
            moveEnemy[i] = spritesheetEnemy.crop(i * 32, 32, 32, 32);
        }
        for (int i=0; i < 4; i++){
            moveFriend[i] = spritesheetFriend.crop(i * 32, 96, 32, 32);
        }
    }

}
