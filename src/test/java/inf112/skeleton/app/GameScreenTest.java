package inf112.skeleton.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.Player;

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

    @Test
    void testGetPlayer() {
        Player p = mock(Player.class);
        when(gameScreen.getPlayer()).thenReturn(p);

        assertEquals(gameScreen.getPlayer(), p);
    }

    @Test
    void testGetEnemies() {
        Set<Enemy> enemies = mock(Set.class);

        when(gameScreen.getEnemies()).thenReturn(enemies);
        assertEquals(gameScreen.getEnemies(), enemies);
    }

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

