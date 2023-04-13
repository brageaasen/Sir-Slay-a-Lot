package inf112.skeleton.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BulletTest {
    

    @Test 
    void testSetAndGetDamage(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setDamage(10);
        assertEquals(10, bullet.getDamage());
        
        bullet.setDamage(20);
        assertEquals(20, bullet.getDamage());
    }

    @Test 
    void testSetAndGetRange(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setRange(10);
        assertEquals(10, bullet.getRange());

        bullet.setRange(20);
        assertEquals(20, bullet.getRange());

    }

    @Test 
    void testSetAndGetBulletHit(){
        Bullet bullet = mock(Bullet.class, Mockito.CALLS_REAL_METHODS);
        bullet.setBulletHit(false);
        assertFalse(bullet.getBulletHit());

        bullet.setBulletHit(true);
        assertTrue(bullet.getBulletHit());

    }
}

