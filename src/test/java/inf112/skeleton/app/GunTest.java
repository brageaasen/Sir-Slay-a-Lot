package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GunTest {
    
    private Gun gun;

    @BeforeEach
    void initEach() {
        
        this.gun = new Gun(10f, 10, 100, 0.5f, "gunBullet.png", "gun.png");
    }

    @Test
    void testHoldGun(){
        gun.setHoldGun(false);
        assertFalse(gun.getHoldGun());
        gun.setHoldGun(true);
        assertTrue(gun.getHoldGun());
    }

    @Test
    public void testFire() {
        // Test that firing the gun adds a bullet to the list of bullets
        gun.fire(new Vector2(0, 0), new Vector2(1, 0));
        List<Bullet> bullets = gun.getBullets();
        assertEquals(1, bullets.size());
    }



}
