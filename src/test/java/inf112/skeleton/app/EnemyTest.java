package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}