package inf112.skeleton.app.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.Back_end.AudioManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Class for the gun used by the player. Has a fire method and rendering methods. 
 */
public class Gun {
    private final float bulletSpeed;
    private final int damage;
    private final int range;
    private final float fireRate;
    private float fireTimer;

    private final Sprite bulletSprite;
    private final Sprite gunSprite;
    private final List<Bullet> bullets;
    private Integer bulletChamber;

    private boolean isUnlocked;
    private boolean holdGun;
    private boolean isFiring;
    private final AudioManager audioManager;

    /**
     * Create a new Gun object
     *
     * @param bulletSpeed the speed of the bullets fired from the gun
     * @param damage the amount of damage each bullet causes
     * @param range the maximum distance a bullet can travel before being destroyed
     * @param fireRate the amount of time in seconds between each shot fired from the gun
     * @param bulletTexturePath the file path for the texture used for the bullets
     * @param gunTexturePath the file path for the texture used for the gun
     *
     * Constructor for the Gun class that initializes the gun's attributes and textures.
     */
    public Gun(float bulletSpeed, int damage, int range, float fireRate, String bulletTexturePath, String gunTexturePath) {
        this(bulletSpeed, damage, range, fireRate, new Sprite(new Texture(bulletTexturePath)), new Sprite(new Texture(gunTexturePath)));
    }

    /**
     * Create a new Gun object
     * (Used for testing)
     * @param bulletSpeed the speed of the bullets fired from the gun
     * @param damage the amount of damage each bullet causes
     * @param range the maximum distance a bullet can travel before being destroyed
     * @param fireRate the amount of time in seconds between each shot fired from the gun
     * @param bulletSprite the sprite used for the bullets of the gun
     * @param gunSprite the sprite used for the gun
     *
     * Constructor for the Gun class that initializes the gun's attributes and textures.
     */
    public Gun(float bulletSpeed, int damage, int range, float fireRate, Sprite bulletSprite, Sprite gunSprite) {
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
        this.fireTimer = 0;
        this.bulletChamber = 20;
        this.audioManager = new AudioManager();
        this.bulletSprite = bulletSprite;
        this.isUnlocked = false;
        this.gunSprite = gunSprite;
        this.bulletSprite.setOrigin(this.gunSprite.getOriginX() - 20, this.gunSprite.getOriginY() - 5);
        this.bullets = new ArrayList<>();

        this.holdGun = false;
        this.isFiring = false;
    }
    
    /**
     * @param delta the time elapsed since the last frame
     * Updates the timer for firing the gun and updates each bullet in the list, removing any that have exceeded their range.
     */

    public void update(float delta) {
        // Update the timer for firing the gun
        fireTimer -= delta;

        // Update each bullet in the list
        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            bullet.update(delta);

            // Remove bullets that have exceeded their range
            if (bullet.getRange() <= 0) {
                iter.remove();
            }
        }
    }

    /**
     * @param position the position from where the bullet is being fired
     * @param direction the direction in which the bullet is being fired
     * 
     * Fires a bullet from the gun in the given direction and adds it to the list of bullets if 
     * the gun's fire timer has expired and if the bullet chamber is not empty.
     */
    public void fire(Vector2 position, Vector2 direction) {
        // Only fire the gun if the fire timer has expired
        if (fireTimer <= 0) {
            if(bulletChamber != 0)
            {
                // Create a new bullet entity and add it to the list
                Bullet bullet = new Bullet(position, direction, bulletSpeed, damage, range, bulletSprite);
                bullets.add(bullet);
                this.audioManager.play("Shoot");

                bulletChamber--;

                // Reset the fire timer
                fireTimer = fireRate;
            }
        }
    }

    /**
     * @param batch the sprite batch used for rendering
     * 
     * Renders the gun sprite.
     */
    public void renderGun(SpriteBatch batch) {
        gunSprite.draw(batch);
    }

    /**
     * @param batch the sprite batch used for rendering
     * Renders each bullet in the list of bullets.
     */
    public void renderBullets(SpriteBatch batch) {
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    /**
     * @return List<Bullet> a copy of the list of bullets
     * Returns a copy of the list of bullets for encapsulation.
     */
    public List<Bullet> getBullets(){
        return new ArrayList<>(bullets);
    }

    /**
     * @param vector the position where the gun sprite should be placed
     * Sets the position of the gun sprite to the given position.
     */
    public void setPosition(Vector2 vector){
        gunSprite.setPosition(vector.x, vector.y);
    }

    /**
     * @return the gun sprite
     * Returns the gun sprite.
     */
    public Sprite getSprite(){
        return gunSprite;
    }

    /**
     * @param isFiring true if the gun is currently firing, false otherwise
     * Sets the firing state of the gun.
     */
    public void setFiring(boolean isFiring){
        this.isFiring = isFiring;
    }

    /**
     * @return true if the gun is currently firing, false otherwise
     * Returns the firing state of the gun.
     */
    public boolean getFiring(){
        return this.isFiring;
    }

    /**
     * @return true if the gun is currently held, false otherwise
     * Returns the state of the gun being held.
     */
    public boolean getHoldGun() {
        return this.holdGun;
    }

    /**
     * @param holdGun true if the gun is currently held, false otherwise
     * Sets the state of the gun
     */
    public void setHoldGun(boolean holdGun) {
        this.holdGun = holdGun;
    }

    /**
     * @return number of bullets in chamber
     */
    public Integer bulletsInChamber(){
        return bulletChamber;
    }

    public void setBulletsInChamber(Integer numBullets){
        this.bulletChamber = numBullets;
    }

    public void bulletsPowerUp(){
        this.bulletChamber += 20;
    }

    public void setUnlocked(){
        this.isUnlocked = true;
    }

    public boolean getUnlocked(){
        return this.isUnlocked;
    }
}
