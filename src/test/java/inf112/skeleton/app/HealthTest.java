package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.skeleton.app.Entity.Health;

/**
 * Testing Health.java
 */
public class HealthTest {
    
    Health healthModel;

    @BeforeEach
    void initEach() {
        healthModel = new Health();
    }

    /**
     * Tests that the health is full
     */
    @Test
    void testFullHealth() {
        assertEquals(100, healthModel.getHP());
        healthModel.decreaseHP(50);
        assertEquals(50, healthModel.getHP());
        healthModel.fullHealth();
        assertEquals(100, healthModel.getHP());
    }

    /**
     * Tests decreasing health
     */
    @Test
    void testDecreaseHealth() {
        healthModel.decreaseHP(30);
        
        int health = healthModel.getHP();
        assertEquals(70, health);

        healthModel.decreaseHP(75);
        health = healthModel.getHP();
        assertEquals(0, health);
    }

    /**
     * Tests health regeneration
     */
    @Test
    void testHealthRegen() {
        healthModel.decreaseHP(30);

        int health = healthModel.getHP();
        assertEquals(70, health);

        healthModel.regenHealth();
        health = healthModel.getHP();
        assertEquals(72, health);
    }

    /**
     * Tests health regeneration when the missing health is less than the amount of regeneration
     */
    @Test
    void testHealthRegenAboveLostHealth() {
        healthModel.decreaseHP(1);

        int health = healthModel.getHP();
        assertEquals(99, health);

        healthModel.regenHealth();
        health = healthModel.getHP();
        assertEquals(100, health);
    }
}

