package inf112.skeleton.app;

import inf112.skeleton.app.Entity.Player;

import org.mockito.Mockito;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

import javax.swing.text.Position;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    

    @Test
    void testDecreaseAndRegenHealth(){
        Player player = mock(Player.class, Mockito.CALLS_REAL_METHODS);
        when(player.getPlayerHealth()).thenReturn(new Health());
        
        player.getPlayerHealth().decreaseHP(100);
        assertEquals(0, player.getPlayerHealth().getHP());

        player.getPlayerHealth().regenHealth();;
        assertEquals(2, player.getPlayerHealth().getHP());
    }

    @Test
    void flipSpriteTest()
    {
        Sprite sprite = new Sprite();
        sprite.flip(false, false);
        assertTrue(!sprite.isFlipX());
    }

}
