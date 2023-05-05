package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.g2d.Sprite;

import inf112.skeleton.app.Weapons.Gun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;


public class GunTest {

    /**
     * Simple testing of the gun that requires an actual gun (not mocked)
     */
    @Test
    void testGunFire() {
        Gun gun = new Gun(1f, 1, 10, 2f, new Sprite(), new Sprite());

        // Test that the gun doesn't just throw after initialization.
        assertDoesNotThrow(() -> gun.update(1f));

        // Simple firing test
        int bullets = gun.bulletsInChamber();
        assertEquals(0, gun.getBullets().size());
        assertDoesNotThrow(() -> gun.fire(new Vector2(0,0), new Vector2(1,0)));
        assertEquals(bullets - 1, gun.bulletsInChamber());
        assertEquals(1, gun.getBullets().size());   // fired once

        var bullet0pos = gun.getBullets().get(0).getPosition();
        assertDoesNotThrow(() -> gun.update(1f));
        assertEquals(bullet0pos.add(new Vector2(1,0)), gun.getBullets().get(0).getPosition());
        assertDoesNotThrow(() -> gun.update(1f));   // fire rate is 2f, need to wait out timer (update twice) to fire.
        assertEquals(bullet0pos.add(new Vector2(1,0)), gun.getBullets().get(0).getPosition());

        assertDoesNotThrow(() -> gun.fire(new Vector2(0,0), new Vector2(1,0)));
        assertEquals(2, gun.getBullets().size());   // fired twice

        assertEquals(8, gun.getBullets().get(0).getRange()); // update called twice after first fired bullet.
        assertEquals(10, gun.getBullets().get(1).getRange()); // update NOT called since second fired bullet.
        for (int i = 1; i < 8; i++) {
            assertDoesNotThrow(() -> gun.update(1f));
            assertEquals( 8 - i, gun.getBullets().get(0).getRange());
            assertEquals(bullet0pos.add(new Vector2(1,0)), gun.getBullets().get(0).getPosition());
            assertEquals(10 - i, gun.getBullets().get(1).getRange());
        }
        // Now, the first will be removed the next update.
        assertDoesNotThrow(() -> gun.update(1f));
        assertEquals(1, gun.getBullets().size());
        // Two more and the second bullet is also removed.
        assertDoesNotThrow(() -> gun.update(1f));
        assertDoesNotThrow(() -> gun.update(1f));
        assertTrue(gun.getBullets().isEmpty());

        // Test that the other methods work.
        assertDoesNotThrow(() -> gun.setPosition(new Vector2(1,2)));
        assertEquals(1, gun.getSprite().getX());
        assertEquals(2, gun.getSprite().getY());

        assertDoesNotThrow(gun::getSprite);
    }

    /**
     * Tests setter and getter for holding the Gun
     */
    @Test
    void testHoldGun(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        gun.setHoldGun(true);
        assertTrue(gun.getHoldGun());
    }

    /**
     * Tests setters and getters for ammunition in Gun
     */
    @Test
    void testBulletChamber() {
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        gun.setBulletsInChamber(20);
        assertEquals(20, gun.bulletsInChamber());
 
        gun.setBulletsInChamber(19);
        assertEquals(19, gun.bulletsInChamber());

        gun.setBulletsInChamber(gun.bulletsInChamber()-1);
        assertEquals(18, gun.bulletsInChamber());
    }

    /**
     * Tests extra ammunition PowerUp for Gun
     */
    @Test 
    void testPowerUp(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        gun.setBulletsInChamber(20);
        assertEquals(20, gun.bulletsInChamber());

        gun.bulletsPowerUp();
        assertEquals(40, gun.bulletsInChamber());
    }

    /**
     * Tests setter and getter for unlocking the Gun
     */
    @Test 
    void testUnlocked(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        assertFalse(gun.getUnlocked());

        gun.setUnlocked();
        assertTrue(gun.getUnlocked());
    }

    /**
     * Tests whether the Gun is firing
     */
    @Test 
    void testFire(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);

        assertFalse(gun.getFiring());
        gun.setFiring(true);
        assertTrue(gun.getFiring());
    }

}
