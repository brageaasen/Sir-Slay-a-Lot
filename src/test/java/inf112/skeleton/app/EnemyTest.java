package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import inf112.skeleton.app.Entity.Enemy;

public class EnemyTest {

    @Test
    void testHealth() {
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        when(enemy.getHealth()).thenReturn(new Health());
        assertEquals(100, enemy.getHealth().getHP());
    }

    @Test 
    void testEnemyDecreaseHP(){
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        when(enemy.getHealth()).thenReturn(new Health());

        assertEquals(100, enemy.getHealth().getHP());

        enemy.getHealth().decreaseHP(100);
        assertEquals(0, enemy.getHealth().getHP());
    }

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