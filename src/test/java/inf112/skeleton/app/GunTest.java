package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito;

import inf112.skeleton.app.Weapons.Gun;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;


public class GunTest {

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
     * Tests extra ammunition powerup for Gun
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
