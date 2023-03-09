package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthTest {
    
    Health healthModel;

    @BeforeEach
    void initEach() {
        healthModel = new Health();
    }

    @Test
    void testFullHealth() {
        int health = healthModel.getHP();

        assertEquals(100, health);
        assertNotEquals(59, health);
    }

    @Test
    void testDecreaseHealth() {
        healthModel.decreaseHP(30);
        
        int health = healthModel.getHP();
        assertEquals(70, health);

        healthModel.decreaseHP(75);
        health = healthModel.getHP();
        assertEquals(0, health);
    }
}

