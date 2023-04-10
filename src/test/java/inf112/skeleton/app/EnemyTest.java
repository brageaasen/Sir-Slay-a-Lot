package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.Player;

public class EnemyTest {

    @Test
    void testTakeDamage() {
        TileMapHelper tileMapHelper;
        Player player = new Player(32, 32, null, null);
        Enemy enemy = new Enemy(32, 32, null, player);

        Health maxHp = enemy.getMaxHealth();
        Health hp = enemy.getHealth();
    
        assertEquals(hp, maxHp);

        enemy.takeDamage();

        assertEquals(maxHp.getHP() - player.getAttackDamage(), hp);
    }
}