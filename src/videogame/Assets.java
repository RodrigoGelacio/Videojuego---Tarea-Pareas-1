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
    public static BufferedImage enemy;
    public static BufferedImage good;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip jump;
    public static BufferedImage over;
    public static BufferedImage sprite;
    public static BufferedImage up[];
    public static BufferedImage down[];
    public static BufferedImage left[];
    public static BufferedImage right[];
    public static BufferedImage idle[];

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background2.png");
        good = ImageLoader.loadImage("/images/good.png");
        player = ImageLoader.loadImage("/images/player.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        jump = new SoundClip("/sounds/jump.wav");
        over = ImageLoader.loadImage("/images/over.png");
        sprite = ImageLoader.loadImage("/images/link.png");
        Spritesheet spritesheet = new Spritesheet(sprite);
        idle = new BufferedImage[3];
        up = new BufferedImage[10];
        down = new BufferedImage[10];
        left = new BufferedImage[10];
        right = new BufferedImage[10];
        
         for(int i=0; i < 3; i++){
               idle[i] = spritesheet.crop(i * 96, 0, 96, 96);    
        }
        for (int i=0; i < 10; i++){
            up[i] = spritesheet.crop(i * 96, 624, 96, 96);
            down[i] = spritesheet.crop(i * 96, 416, 96, 96);
            right[i] = spritesheet.crop(i * 96, 728, 96, 96); 
            left[i] = spritesheet.crop(i * 96, 520, 96, 96);
        }
    }

}
