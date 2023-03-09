package inf112.skeleton.app;


public class Health {
    
    private int hitPoints;
    private final int regenPerSecond;

    public Health() {
        hitPoints = 100;
        regenPerSecond = 10;
    }

    public int getHP() {
        return hitPoints;
    }

    public void decreaseHP(int quantity) {
        hitPoints -= quantity;

        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    public void regenHealth() {
        if (hitPoints < 90) {
            hitPoints += regenPerSecond;
        } else {
            hitPoints = 100;
        }
    }

}

