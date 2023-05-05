package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.Back_end.BodyHelper;
import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    /**
     * Test that the `Enemy` class can perform the various `Entity` methods without failing.
     */
    @Test
    void testEntityEnemy() {
        var world = new World(new Vector2(0,-25f),false);
        var rectangle = new Rectangle(0,0,10,10);
        var body = BodyHelper.createEntityBody(
                rectangle.getX() + rectangle.getWidth()/2,
                rectangle.getY() + rectangle.getHeight()/2,
                rectangle.getWidth(), rectangle.getHeight(), false, world);
        GameEntity e = new Enemy(10,10, body);
        assertDoesNotThrow(e::update);
        assertDoesNotThrow(e::getPosition);
        assertDoesNotThrow(e::getVelocity);
        assertDoesNotThrow(e::getSpeed);
        assertDoesNotThrow(e::flip);
    }

    /**
     * Test that the `Player` class can perform the various `Entity` methods without failing.
     */
    @Test
    void testEntityPlayer() {
        var world = new World(new Vector2(0,-25f),false);
        var rectangle = new Rectangle(0,0,10,10);
        var body = BodyHelper.createEntityBody(
                rectangle.getX() + rectangle.getWidth()/2,
                rectangle.getY() + rectangle.getHeight()/2,
                rectangle.getWidth(), rectangle.getHeight(), false, world);
        GameEntity e = new Player(10,10, body, true);
        assertDoesNotThrow(e::update);
        assertDoesNotThrow(e::getPosition);
        assertDoesNotThrow(e::getVelocity);
        assertDoesNotThrow(e::getSpeed);
        assertDoesNotThrow(e::flip);
    }
}
