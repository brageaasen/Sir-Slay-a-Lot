package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/*
 * A factory for creating a GameEntity
 */
public abstract class GameEntity{
    public enum Direction {
        NONE,
        LEFT,
        RIGHT,
    }
    
    protected float x,y,velX,velY,speed;
    protected float width,height;

    protected Body body;

    /**
     * This is the constructor for the GameEntity class
     * @param width The width of the entity
     * @param height The height of the entity
     * @param body Box2D body
     */
    public GameEntity(float width, float height, Body body){
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width=width;
        this.height=height;
        this.body=body;
        this.velX=0;
        this.velY=0;
        this.speed=0;
        
    }

    /*
     * This is an abstract method that is used to update the state of the entity every frame.
     */
    public abstract void update();

    /*
     * This is an abstract method that is used to draw the entity every frame.
     */
    public abstract void render(SpriteBatch batch);

    /*
     * This method returns the Box2D body associated with the entity.
     */
    public Body getBody(){
        return body;
    }

    /**
     * Get the Position of the entity.
     * @return The position as a Vector2
     */
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    /**
     * Get the velocity of the entity.
     * @return The velocity as a Vector2
     */
    public Vector2 getVelocity() {
        return new Vector2(velX, velY);
    }

    /**
     * Get the speed of the entity.
     * @return The speed as a floating point number.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Move the entity in the specified direction.
     * @param direction The direction to move in.
     */
    public abstract void move(Direction direction);
}