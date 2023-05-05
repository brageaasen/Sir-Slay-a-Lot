package inf112.skeleton.app.Weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/*
 * Class for the bullet used/shot by the gun. 
 */
public class Bullet {
    private final Vector2 position;
    private final Vector2 velocity;
    private int damage;
    private int range;
    private final Sprite sprite;
    private boolean bulletHit;

    /**
     * @param position The initial position of the bullet as a Vector2 object.
     * @param direction The direction that the bullet is fired in, as a Vector2 object.
     * @param speed The speed at which the bullet moves.
     * @param damage The amount of damage that the bullet deals when it hits a target.
     * @param range The maximum distance that the bullet can travel before disappearing.
     * @param sprite The sprite used to render the bullet.
     */
    public Bullet(Vector2 position, Vector2 direction, float speed, int damage, int range, Sprite sprite) {
        this.position = new Vector2(position);
        this.velocity = new Vector2(direction).nor().scl(speed);
        this.damage = damage;
        this.range = range;
        this.sprite = sprite;
        this.bulletHit = false;
        
        this.sprite.setRotation(direction.angleDeg());
        this.sprite.setScale(2);
    }

    /**
     * This method is called to update the position of the bullet based on its velocity and the amount of time that has passed.
     * @param delta The time that has elapsed since the last update.
     */
    public void update(float delta) {
        // Move the bullet based on its velocity
        position.add(velocity.x * delta, velocity.y * delta);

        // Check if the bullet has exceeded its range
        range--;
    }

    /**
     * This method is called to render the bullet sprite at its current position.
     * @param batch The SpriteBatch used to render the sprite.
     */
    public void render(SpriteBatch batch) {
        // Render the bullet sprite at its current position
        if (!bulletHit){
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }

    /**
     * This method returns the current position of the bullet.
     * @return A Vector2 object representing the current position of the bullet.
     */
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    /**
     * This method returns the amount of damage that the bullet deals.
     * @return An integer representing the damage dealt by the bullet.
     */
    public int getDamage() {
        return damage;
    }

    
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**     
     * This method returns the remaining range of the bullet, which is the maximum distance it can travel before disappearing.
     * @return An integer representing the remaining range of the bullet.
     */
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    /**
     * This method returns a boolean value indicating whether or not the bullet has hit its target.
     * @return true if the bullet has hit its target, false otherwise.
     */
    public boolean getBulletHit(){
        return bulletHit;
    }

    /**
     * This method is used to set the bulletHit variable to true or false.
     * @param hit boolean value indicating whether or not the bullet has hit its target.
     * If true, the bullet has hit its target. If false, the bullet has not hit its target.
     */
    public void setBulletHit(boolean hit){
        this.bulletHit = hit;
    }
}
