package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnifeTest {
    
    private Knife knife;

    @BeforeEach
    void initEach() {
        
        this.knife = new Knife();
    }

    @Test 
    void testHoldingKnife(){
        knife.setHoldKnife(false);
        assertFalse(knife.getHoldKnife());

        knife.setHoldKnife(true);
        assertTrue(knife.getHoldKnife());

    }


    @Test 
    void testDealingDamage(){
        knife.setDealingDamage(false);
        assertFalse(knife.getDealingDamage());

        knife.setDealingDamage(true);
        assertTrue(knife.getDealingDamage());

    }
}
