package inf112.skeleton.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.skeleton.app.Weapons.Knife;

import static org.junit.jupiter.api.Assertions.*;

public class KnifeTest {
    
    private Knife knife;

    @BeforeEach
    void initEach() {
        this.knife = new Knife();
    }

    /**
     * Tests whether the Knife is being held
     */
    @Test 
    void testHoldingKnife(){
        knife.setHoldKnife(false);
        assertFalse(knife.getHoldKnife());

        knife.setHoldKnife(true);
        assertTrue(knife.getHoldKnife());

    }


    /**
     * Tests whether the Knife can deal damage
     */
    @Test 
    void testDealingDamage(){
        knife.setDealingDamage(false);
        assertFalse(knife.getDealingDamage());

        knife.setDealingDamage(true);
        assertTrue(knife.getDealingDamage());

    }

    @Test
    void testNextAttack() {
        knife.setNextAttack();
        assertEquals(50, knife.getNextAttack());
    }
}
