package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;

public class PlayerModel extends GameEntity {

    private Health playerHealth;
    private int jumpCounter;
    private Direction facing;
    private boolean holdKnife;
    private Timer regenTimer;

    public PlayerModel(float width, float height, Body body) {
        super(width, height, body);
        playerHealth = new Health();
        jumpCounter = 0;
        this.facing = Direction.NONE;
        holdKnife = false;

        regenTimer = new Timer();
        regenTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                playerHealth.regenHealth();
            }
            
        }, 3, 3);
    }

    public Health getPlayerHealth() {
        return playerHealth;
    }

    public void update() {
        x = body.getPosition().x * 16;
        y = body.getPosition().y * 16;

        this.checkFallDamage();
        this.isDead();
    }

    public void jump(){
        float force = body.getMass() * 10 * 2;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(0,force), body.getPosition(), true);
        jumpCounter++;
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


    @Override
    public void move(Direction direction) {
        switch (direction) {
            case RIGHT -> {
                velX = 1;
                }
            case LEFT -> {
                velX = -1;
            }
            default -> velX = 0;
        }
        this.facing = direction; // Update afterwards, so we can change properties when they 'just happened' (e.g. 'just moved left')
    }

    public void setKnife(boolean b) {
        holdKnife = b;
    }

    public int getJump() {
        return jumpCounter;
    }

    public void setJump(int n) {
        jumpCounter = n;
    }

    public Direction getDir() {
        return facing;
    }
    
}
