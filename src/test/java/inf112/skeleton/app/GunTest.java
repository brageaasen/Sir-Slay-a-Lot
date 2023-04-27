package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GunTest {
    
   

    @BeforeEach
    void initEach() {
        
    }

    @Test
    void testHoldGun(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        gun.setHoldGun(true);
        assertTrue(gun.getHoldGun());
    }

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

    @Test 
    void testPowerUp(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        gun.setBulletsInChamber(20);
        assertEquals(20, gun.bulletsInChamber());

        gun.bulletsPowerUp();
        assertEquals(40, gun.bulletsInChamber());
    }

    @Test 
    void testUnlocked(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);
        assertFalse(gun.getUnlocked());

        gun.setUnlocked();
        assertTrue(gun.getUnlocked());
    }

    @Test 
    void testFire(){
        Gun gun = mock(Gun.class, Mockito.CALLS_REAL_METHODS);

        assertFalse(gun.getFiring());
        gun.setFiring(true);
        assertTrue(gun.getFiring());
    }


}
