package inf112.skeleton.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import inf112.skeleton.app.Weapons.Bullet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BulletTest {
    
    /**
     * Tests setter and getters for Bullet damage
     */
    @Test 
    void testSetAndGetDamage(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setDamage(10);
        assertEquals(10, bullet.getDamage());
        
        bullet.setDamage(20);
        assertEquals(20, bullet.getDamage());
    }

    /**
     * Tests setter and getter for Bullet range
     */
    @Test 
    void testSetAndGetRange(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setRange(10);
        assertEquals(10, bullet.getRange());

        bullet.setRange(20);
        assertEquals(20, bullet.getRange());

    }

    /**
     * Tests setter and getter for Bullet hits
     */
    @Test 
    void testSetAndGetBulletHit(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setBulletHit(false);
        assertFalse(bullet.getBulletHit());

        bullet.setBulletHit(true);
        assertTrue(bullet.getBulletHit());

    }
}

