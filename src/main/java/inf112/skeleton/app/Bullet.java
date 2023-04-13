package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private final Vector2 position;
    private final Vector2 velocity;
    private int damage;
    private int range;
    private final Sprite sprite;
    private boolean bulletHit;

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

    public void update(float delta) {
        // Move the bullet based on its velocity
        position.add(velocity.x * delta, velocity.y * delta);

        // Check if the bullet has exceeded its range
        range--;
    }

    public void render(SpriteBatch batch) {
        // Render the bullet sprite at its current position
        if (!bulletHit){
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }

    public Vector2 getPosition() {
        return new Vector2(position);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean getBulletHit(){
        return bulletHit;
    }

    public void setBulletHit(boolean hit){
        this.bulletHit = hit;
    }

    public Vector2 getVelocity(){
        return this.velocity;
    }
}
