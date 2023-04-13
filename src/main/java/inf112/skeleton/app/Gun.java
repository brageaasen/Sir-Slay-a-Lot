package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gun {
    private final float bulletSpeed;
    private final int damage;
    private final int range;
    private final float fireRate;
    private float fireTimer;

    private final Texture bulletTexture;
    private final Sprite bulletSprite;

    private final Sprite gunSprite;
    private final List<Bullet> bullets;
    private Integer bulletChamber;

    private boolean holdKnife;
    private boolean dealingDamage;
    private boolean isFiring;

    // Auido
    private AudioManager audioManager = new AudioManager();

    public Gun(float bulletSpeed, int damage, int range, float fireRate, String bulletTexturePath, String gunTexturePath) {
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
        this.fireTimer = 0;
        this.bulletChamber = 20;

        this.bulletTexture = new Texture(bulletTexturePath);
        this.bulletSprite = new Sprite(bulletTexture);

        this.gunSprite = new Sprite(new Texture(gunTexturePath));
        this.bulletSprite.setOrigin(gunSprite.getOriginX() - 20, gunSprite.getOriginY() - 5);
        this.bullets = new ArrayList<>();

        this.holdKnife = false;
        this.dealingDamage = false;
        this.isFiring = false;
    }

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

    public void fire(Vector2 position, Vector2 direction) {
        // Only fire the gun if the fire timer has expired
        if (fireTimer <= 0) {
            if(bulletChamber != 0)
            {
                // Create a new bullet entity and add it to the list
                Bullet bullet = new Bullet(position, direction, bulletSpeed, damage, range, bulletSprite);
                bullets.add(bullet);
                this.audioManager.Play("Shoot");

                bulletChamber--;

                // Reset the fire timer
                fireTimer = fireRate;
            }
        }
    }

    public void renderGun(SpriteBatch batch) {
       
        gunSprite.draw(batch);
        
    }

    public void renderBullets(SpriteBatch batch) {
       
        // Render each bullet in the list
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    public List<Bullet> getBullets(){
        return bullets;
    }

    public void setPosition(Vector2 vector){
        gunSprite.setPosition(vector.x, vector.y);
    }

    public Sprite getSprite(){
        return gunSprite;
    }

    public void setFiring(boolean isFiring){
        this.isFiring = isFiring;
    }

    public boolean getFiring(){
        return this.isFiring;
    }

    public boolean isHoldGun() {
        return this.holdKnife;
    }

    public boolean getHoldGun() {
        return this.holdKnife;
    }

    public void setHoldGun(boolean holdKnife) {
        this.holdKnife = holdKnife;
    }

}
