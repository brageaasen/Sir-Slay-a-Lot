package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Body;

public class PlayerModel {
    private Health playerHealth;
    private Body body;

    public PlayerModel(Body body) {
        this.body = body;
        playerHealth = new Health();
    }

    public Health getPlayerHealth() {
        return playerHealth;
    }

    public void update() {
        this.checkFallDamage();
        this.isDead();
    }

    /**
     * Checks if the player is dead by checking HP
     * @return true if the player is dead, false otherwise
     */
    public boolean isDead() {
        return playerHealth.getHP() <= 0;
    }

    /**
     * Checks if a player is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return body.getLinearVelocity().y == 0;
    }

    /**
     * Checks if the player has fallen from too high and how much damage is inflicted
     */
    public void checkFallDamage() {
        float verticalSpeed = body.getLinearVelocity().y;

        if (verticalSpeed < -10) {
            int damageScale = (int) ((Math.abs(verticalSpeed) - 10));

            playerHealth.decreaseHP(damageScale);
        }
    }
}
