package inf112.skeleton.app.Weapons;

/*
 * Class for the knife used by the player. 
 */
public class Knife {
    
    private boolean holdKnife;
    private boolean dealingDamage;
    public int nextAttack;
    private int nextAttackFrames = 50;

    /**
     * Creates a new instance of the Knife class with default values.
     */
    public Knife() {
        holdKnife = false;
        dealingDamage = false;
        nextAttack = 0;
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
     * @param holdKnife boolean indicating whether the player is currently holding the knife.
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

     /**
     * This method returns the current ammount of frames before you can attack again.
     * @return An int indicating the ammount of frames before you can attack again.
     */
    public int getNextAttack(){
        //System.out.println(this.nextAttack);
        return this.nextAttack;
    }

    /**
     * This method sets the frames to next attack.
     */
    public void setNextAttack(){
        if(this.nextAttack == 0){
            this.nextAttack = nextAttackFrames;
        }
    }
}
