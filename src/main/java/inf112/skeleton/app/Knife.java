package inf112.skeleton.app;

/*
 * Class for the knife used by the player. 
 */
public class Knife {
    
    private boolean holdKnife;
    private boolean dealingDamage;

    /**
     * Creates a new instance of the Knife class with default values.
     */
    public Knife() {
        holdKnife = false;
        dealingDamage = false;
    }

    /**
     * This method returns the value of the holdKnife boolean field.
     * @return A boolean indicating whether the player is currently holding the knife.
     */
    public boolean getHoldKnife() {
        return this.holdKnife;
    }

    /**
     * This method sets the value of the holdKnife boolean field to the value passed in as a parameter.
     * @param holdKnifeA boolean indicating whether the player is currently holding the knife.
     */
    public void setHoldKnife(boolean holdKnife) {
        this.holdKnife = holdKnife;
    }

    /**
     * This method returns the value of the dealingDamage boolean field.
     * @return A boolean indicating whether the knife can deal damage.
     */
    public boolean getDealingDamage() {
        return this.dealingDamage;
    }

    /**
     * This method sets the value of the dealingDamage boolean field to the value passed in as a parameter.
     * @param dealingDamage A boolean indicating whether the knife can deal damage.
     */
    public void setDealingDamage(boolean dealingDamage) {
        this.dealingDamage = dealingDamage;
    }
}
