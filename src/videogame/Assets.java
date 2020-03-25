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
    }

}
