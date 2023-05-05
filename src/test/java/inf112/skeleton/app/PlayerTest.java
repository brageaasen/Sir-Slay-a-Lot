package inf112.skeleton.app;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.Back_end.BodyHelper;
import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Health;
import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Entity.Player.PlayerState;
import inf112.skeleton.app.Weapons.Gun;

import org.mockito.Mockito;

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

    void setRealPlayer() {
        var world = new World(new Vector2(0,-25f),false);
        var rectangle = new Rectangle(0,0,10,10);
        var body = BodyHelper.createEntityBody(
                rectangle.getX() + rectangle.getWidth()/2,
                rectangle.getY() + rectangle.getHeight()/2,
                rectangle.getWidth(), rectangle.getHeight(), false, world);
        player = new Player(10,10, body, true);
    }

    @Test
    void testDirection() {
        setRealPlayer();

        player.move(GameEntity.Direction.LEFT);
        assertEquals(-1, player.getVelocity().x);
        player.move(GameEntity.Direction.RIGHT);
        assertEquals(1, player.getVelocity().x);
        player.move(GameEntity.Direction.NONE);
        assertEquals(0, player.getVelocity().x);
    }

    /**
     * Tests health decrease and subsequent regeneration for Player
     */
    @Test
    void testDecreaseAndRegenHealth(){
        when(player.getPlayerHealth()).thenReturn(new Health());
        
        player.getPlayerHealth().decreaseHP(100);
        assertEquals(0, player.getPlayerHealth().getHP());

        player.getPlayerHealth().regenHealth();
        assertEquals(2, player.getPlayerHealth().getHP());

        setRealPlayer();    // Default health for real player is 99

        player.gotHurt(10);
        assertEquals(90, player.getPlayerHealth().getHP());
    }

    /**
     * Tests whether the Player is dead when health is 0
     */
    @Test 
    void isDead(){
        Health health = new Health();
        player.setPlayerHealth(health);
        assertEquals(100, player.getPlayerHealth().getHP());
        player.getPlayerHealth().decreaseHP(100);

        assertEquals(0, player.getPlayerHealth().getHP());
        assertTrue(player.isDead());
    }

    /**
     * Test getter for kill count
     */
    @Test 
    void testGetKillCount(){
        assertEquals(0, player.getKillCount());
    }

    /**
     * Tests whether the player has taken damage
     */
    @Test 
    void testIsHurt(){
        assertFalse(player.isHurt());
    }

    /**
     * Tests getter for the Gun
     */
    @Test 
    void testGetGun(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        when(player.getGun()).thenReturn(gun);

        assertEquals(gun, player.getGun());
    }

    /**
     * Tests setter and getter for attack damage
     */
    @Test 
    void testSetAndGetAttackDamage(){
        player.setAttackDamage(50);
        assertEquals(50, player.getAttackDamage());
    }

    /**
     * Tests setter and getter for knife attack range
     */
    @Test 
    void testSetAndGetKnifeAttackRange(){
        player.setKnifeAttackRange(40);
        assertEquals(40, player.getKnifeAttackRange());
    }

    /**
     * Tests setter and getter for gun attack range
     */
    @Test 
    void testSetAndGetGunAttackRange(){
        player.setGunAttackRange(40);
        assertEquals(40, player.getGunAttackRange());
    }

    /**
     * Tests setter and getter for killcount
     */
    @Test 
    void testSetAndGetKillcount(){
        player.setKillCount(10);
        assertEquals(10, player.getKillCount());
    }

    /**
     * Tests setter and getter for attack state
     */
    @Test 
    void testSetAndGetAttack(){
        player.setAttack(true);
        assertEquals(true, player.getAttack());
    }

    /**
     * Tests getter for PPM
     */
    @Test 
    void testPlayerGotHurt(){
        player.gotHurtTest();
        assertEquals(true, player.isHurt());
    }

    
}
