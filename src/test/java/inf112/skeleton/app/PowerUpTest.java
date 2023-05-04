package inf112.skeleton.app;

import inf112.skeleton.app.Entity.Player;

import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PowerUpTest {
    
    Player player;
    PowerUp powerUp;

    @BeforeEach 
    void setUp(){
       player = mock(Player.class, Mockito.CALLS_REAL_METHODS);
       powerUp = mock(PowerUp.class, Mockito.CALLS_REAL_METHODS);
    }

    /**
     * Tests if ammo powerup gets set to active when it should
     */
    @Test 
    void testAmmoPowerUp(){
        PowerUp powerUp = new PowerUp(player, new Vector2(15, 15), new Sprite(), 1);
        player.setKillCount(10);
        powerUp.checkForPowerup();
        assertEquals(true, powerUp.getAmmoActive());
    }

    /**
     * Tests whether the Player is dead when health is 0
     */
    @Test 
    void testHealthPowerUp(){
        PowerUp powerUp = new PowerUp(player, new Vector2(15, 15), new Sprite(), 2);
        player.setKillCount(15);
        powerUp.checkForPowerup();
        assertEquals(true, powerUp.getHealthActive());
    }

    /**
     * Tests setter and getter for ammo active
     */
    @Test 
    void testSetAndGetAmmoActive(){
        powerUp.setAmmoActive(true);
        assertEquals(true, powerUp.getAmmoActive());
    }

    /**
     * Tests setter and getter for health active
     */
    @Test 
    void testSetAndGetHealthActive(){
        powerUp.setHealthActive(true);
        assertEquals(true, powerUp.getHealthActive());
    }



}
