package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Animation;
import inf112.skeleton.app.AnimationHandler;
import inf112.skeleton.app.Health;
import inf112.skeleton.app.KeyHandler;

public class Player extends GameEntity {
    public enum PlayerState {
        Idle,
        Run,
        Hurt,
        Jump,
        Fall,
    }
    private final AnimationHandler<PlayerState> anim;

    private static final int PPM = 16; //?? what does this mean???

    public boolean holdKnife;   //?? Set to private, change using API (e.g. 'slashKnife')
    public int jumpCounter;     //?? Set to private, change using API (e.g. 'Jump')
    private Direction facing;

    // Combat
    private int attackDamage;
    private int attackRange;
    private boolean gotHurt;

    private final Sprite knife;
    private final KeyHandler keyH;
    private final Sprite sprite;

    private final Health playerHealth;


    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;   //?? Introduce constant?
        this.attackDamage = 10;
        this.attackRange = 5;

        this.holdKnife = false;

        this.jumpCounter = 0;
        this.facing = Direction.NONE;
        this.sprite = new Sprite(new Texture("assets/Player/Idle/Idle1.png"));
        this.knife = new Sprite(new Texture("assets/knife.png"));
        this.keyH = new KeyHandler(this);   //?? Should the player class hold input handling?
        this.sprite.setScale(2);

        playerHealth = new Health();
        anim = new AnimationHandler<>(PlayerState.Idle, new Animation("assets/Player/Idle/Idle%d.png", 4));
        anim.addAnimation(PlayerState.Run, new Animation("assets/Player/Run/Run%d.png", 8));
        anim.addAnimation(PlayerState.Hurt, new Animation("assets/Player/Hurt/Hurt%d.png", 1));
        anim.addAnimation(PlayerState.Jump, new Animation("assets/Player/Jump/Jump%d.png", 3));
        anim.addAnimation(PlayerState.Fall, new Animation("assets/Player/Fall/Fall%d.png", 3));
        anim.setState(PlayerState.Idle);
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;

        updateSprite();
        keyH.checkUserInput();
        this.checkFallDamage();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx, dy);
        sprite.draw(batch);

        if (holdKnife) {
            knife.setPosition(dx + (facing == Direction.LEFT ? -width : width), dy);
            knife.draw(batch);
        }
    }

    public void updateSprite() {
        if (this.gotHurt) {
            anim.setState(PlayerState.Hurt);
            this.gotHurt = false;
        } else if (facing == Direction.NONE && getBody().getLinearVelocity().y == 0) {
            anim.setState(PlayerState.Idle);
        } else if (getBody().getLinearVelocity().y > 0) {  // Checking if player is jumping
            anim.setState(PlayerState.Jump);
        } else if (getBody().getLinearVelocity().y < 0) {  // Checking if player is falling
            anim.setState(PlayerState.Fall);
        } else {
            anim.setState(PlayerState.Run);
        }
        anim.update();

        sprite.setTexture(anim.getAnimTexture());
    }

    public void jump() {
        float force = body.getMass() * 10 * 2;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
        jumpCounter++;
    }

    public void flip() { // TODO?: replace unneeded texture?
        sprite.flip(true, false);
        knife.flip(true, false);
    }

    /**
     * Get direction player is facing
     * @return the direction
     */
    public Direction getDirection() {
        return this.facing;
    }

    /**
     * Get PPM of player entity
     * @return the PPM
     */
    public int getPPM() {
        return Player.PPM;
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case RIGHT -> {
                velX = 1;
                if (this.facing != Direction.RIGHT && sprite.isFlipX())
                    flip();
            }
            case LEFT -> {
                velX = -1;
                if (this.facing != Direction.LEFT && !sprite.isFlipX())
                    flip();
            }
            default -> velX = 0;
        }
        this.facing = direction; // Update afterwards, so we can change properties when they 'just happened' (e.g. 'just moved left')
    }


    /**
     * Getter method for using the instantiated Health object
     * @return the Health object
     */
    public Health getPlayerHealth() {
        return playerHealth;
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

        if (verticalSpeed < -37) {
            int damageScale = (int) ((Math.abs(verticalSpeed) - 10));

            playerHealth.decreaseHP(damageScale);
        }
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public int getAttackRange() {
        return this.attackRange;
    }

    public void gotHurt() {
        this.gotHurt = true;
    }

}