package inf112.skeleton.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static org.mockito.Mockito.*;

public class GameScreenTest {
    @Mock
    private SpriteBatch batch;

    private GameScreen gameScreen;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        OrthographicCamera camera = mock(OrthographicCamera.class);
        GameScreenLauncher game = mock(GameScreenLauncher.class);
        gameScreen = new GameScreen(camera, game);
    }

    @Test
    public void testGetPlayer() {
        verify(gameScreen).getPlayer();
    }
}

