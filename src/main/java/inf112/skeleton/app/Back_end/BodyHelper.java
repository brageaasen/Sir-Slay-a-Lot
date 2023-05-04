package inf112.skeleton.app.Back_end;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/*
 * The BodyHelper class contains utility methods for creating Box2D Body objects, which represent physical bodies in a Box2D physics simulation.
 */
public class BodyHelper {

    private static final int PPM = 16;
    
    /**
     * Creates a Box2D body with the given position, dimensions, static/dynamic state, and world.
     * @param x the x position of the body in pixels
     * @param y the y position of the body in pixels
     * @param width the width of the body in pixels
     * @param height the height of the body in pixels
     * @param isStatic true if the body should be static, false if it should be dynamic
     * @param world the Box2D world that the body should be added to
     * @return
     */
    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2 / PPM, height/2/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    /**
     * Creates a Box2D body for an entity (such as a player or enemy) with the given position, dimensions, static/dynamic state, and world.
     * This is almost identical to createBody() above, but this has a different groupIndex so the Player and enemies don't collide.
     * @param x the x position of the body in pixels
     * @param y the y position of the body in pixels
     * @param width the width of the body in pixels
     * @param height the height of the body in pixels
     * @param isStatic true if the body should be static, false if it should be dynamic
     * @param world the Box2D world that the body should be added to
     * @return the created Box2D body
     */
    public static Body createEntityBody(float x, float y, float width, float height, boolean isStatic, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2 / PPM, height/2/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.groupIndex = -1;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

}
