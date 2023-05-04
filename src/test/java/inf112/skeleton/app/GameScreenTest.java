package inf112.skeleton.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Screen.GameScreen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Set;

public class GameScreenTest {
    @Mock
    private SpriteBatch batch;

    private GameScreen gameScreen;

    @BeforeEach
    void setUp() {
        gameScreen = mock(GameScreen.class);
    }

    /**
     * Tests getter for GameScreen's Player
     */
    @Test
    void testGetPlayer() {
        Player p = mock(Player.class);
        when(gameScreen.getPlayer()).thenReturn(p);

        assertEquals(gameScreen.getPlayer(), p);
    }

    /**
     * Tests getter for set of Enemy objects in GameScreen
     */
    @Test
    void testGetEnemies() {
        Set<Enemy> enemies = mock(Set.class);

        when(gameScreen.getEnemies()).thenReturn(enemies);
        assertEquals(gameScreen.getEnemies(), enemies);
    }

    /**
     * Tests adding Enemies to Enemy set
     */
    @Test
    void testAddEnemies() {
        Enemy e = mock(Enemy.class);
        Enemy e2 = mock(Enemy.class);
        Set<Enemy> enemies = mock(Set.class);
        enemies.add(e);
        enemies.add(e2);

        verify(enemies).add(e);
        verify(enemies).add(e2);
    }
}