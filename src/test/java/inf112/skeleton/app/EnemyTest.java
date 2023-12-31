package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.Back_end.BodyHelper;
import inf112.skeleton.app.Entity.GameEntity;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.Health;

public class EnemyTest {

    /**
     * Tests that the Enemy's health is returned with full health
     */
    @Test
    void testHealth() {
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        when(enemy.getHealth()).thenReturn(new Health());
        assertEquals(100, enemy.getHealth().getHP());
    }

    /**
     * Tests that Enemy's health decreases
     */
    @Test 
    void testEnemyDecreaseHP(){
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        when(enemy.getHealth()).thenReturn(new Health());

        assertEquals(100, enemy.getHealth().getHP());

        enemy.getHealth().decreaseHP(100);
        assertEquals(0, enemy.getHealth().getHP());
    }

    /**
     * Tests that Enemy's health goes to 0
     */
    @Test 
    void testEnemyHealthIsZero(){
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        Health health = new Health();
        enemy.setEnemyHealth(health);
        assertEquals(100, enemy.getHealth().getHP());
        enemy.getHealth().decreaseHP(100);

        assertEquals(0, enemy.getHealth().getHP());
        assertTrue(enemy.enemyHealthIsZero());
    }

}