package inf112.skeleton.app;


public class Health {
    
    private int hitPoints;
    private final int regenPerSecond;

    public Health() {
        hitPoints = 100;
        regenPerSecond = 10;
    }

    /**
     * Returns the current health of the player
     * @return the health
     */
    public int getHP() {
        return hitPoints;
    }

    /**
     * Decreases the health of the player by a set quantity
     * @param quantity amount to decrease health by
     */
    public void decreaseHP(int quantity) {
        hitPoints -= quantity;

        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    /**
     * Regenerates health by a fixed amount
     */
    public void regenHealth() {
        if (hitPoints < 90) {
            hitPoints += regenPerSecond;
        } else {
            hitPoints = 100;
        }
    }

}

