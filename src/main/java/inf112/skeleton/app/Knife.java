package inf112.skeleton.app;

public class Knife {
    
    private boolean holdKnife;
    private boolean dealingDamage;

    public Knife() {
        holdKnife = false;
        dealingDamage = false;
    }

    public boolean isHoldKnife() {
        return this.holdKnife;
    }

    public boolean getHoldKnife() {
        return this.holdKnife;
    }

    public void setHoldKnife(boolean holdKnife) {
        this.holdKnife = holdKnife;
    }

    public boolean isDealingDamage() {
        return this.dealingDamage;
    }

    public boolean getDealingDamage() {
        return this.dealingDamage;
    }

    public void setDealingDamage(boolean dealingDamage) {
        this.dealingDamage = dealingDamage;
    }
}
