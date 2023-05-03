package inf112.skeleton.app;

import inf112.skeleton.app.Entity.Player;

import org.mockito.Mockito;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    Player player;

    @BeforeEach 
    void setUp(){
       player = mock(Player.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    void testDecreaseAndRegenHealth(){
        when(player.getPlayerHealth()).thenReturn(new Health());
        
        player.getPlayerHealth().decreaseHP(100);
        assertEquals(0, player.getPlayerHealth().getHP());

        player.getPlayerHealth().regenHealth();;
        assertEquals(2, player.getPlayerHealth().getHP());
    }

    @Test 
    void isDead(){
        Health health = new Health();
        player.setPlayerHealth(health);
        assertEquals(100, player.getPlayerHealth().getHP());
        player.getPlayerHealth().decreaseHP(100);

        assertEquals(0, player.getPlayerHealth().getHP());
        assertTrue(player.isDead());
    }

    @Test 
    void testGetKillCount(){
        assertEquals(0, player.getKillcount());
    }

    @Test 
    void testIsHurt(){
        assertFalse(player.isHurt());
    }

    @Test 
    void testGetGun(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        when(player.getGun()).thenReturn(gun);

        assertEquals(gun, player.getGun());
    }

    @Test 
    void testSetAndGetAttackDamage(){
        player.setAttackDamage(50);
        assertEquals(50, player.getAttackDamage());
    }

    @Test 
    void testSetAndGetKnifeAttackRange(){
        player.setKnifeAttackRange(40);
        assertEquals(40, player.getKnifeAttackRange());
    }

    @Test 
    void testSetAndGetGunAttackRange(){
        player.setGunAttackRange(40);
        assertEquals(40, player.getGunAttackRange());
    }

    
}
