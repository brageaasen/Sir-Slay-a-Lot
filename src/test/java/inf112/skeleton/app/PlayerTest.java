package inf112.skeleton.app;

import inf112.skeleton.app.Entity.Player;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    

    @Test
    void testHurt(){
        Player player = mock(Player.class, Mockito.CALLS_REAL_METHODS);
        assertFalse(player.isHurt());

        player.gotHurt(1);
        assertTrue(player.isHurt());
    }

    @Test
    void testDecreaseAndRegenHealth(){
        Player player = mock(Player.class, Mockito.CALLS_REAL_METHODS);
        when(player.getPlayerHealth()).thenReturn(new Health());
        
        player.getPlayerHealth().decreaseHP(100);
        assertEquals(0, player.getPlayerHealth().getHP());

        player.getPlayerHealth().regenHealth();;
        assertEquals(2, player.getPlayerHealth().getHP());
    }

}
