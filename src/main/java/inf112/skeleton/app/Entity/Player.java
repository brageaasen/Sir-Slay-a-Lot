package inf112.skeleton.app.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Weapons.Gun;
import inf112.skeleton.app.Weapons.Knife;
import inf112.skeleton.app.Animations.Animation;
import inf112.skeleton.app.Animations.AnimationHandler;
import inf112.skeleton.app.Back_end.*;


/*
 * This class is where the Player entity is created. It extends the GameEntity class.
 */
public class Player extends GameEntity {
    public static final int PRICE_GUN = 5; // Number of kills you need before unlocking the gun
    public static final int ANIM_FRAME_RATE_IDLE = 8;
    public static final int ANIM_FRAME_RATE_DEFAULT = 6;

    public enum PlayerState {
        Idle,
        Run,
        Hurt,
        Jump,
        Fall,
        Attack,
    }
    private final AnimationHandler<PlayerState> anim;

    public static final int PPM = 16; //Pixel Per Meter

    public int jumpCounter;
    private Direction facing;
    public Knife knifeObj;
    public Gun gun;
    public int killCount = 0;

    // Audio
    private final AudioManager audioManager = new AudioManager();

    // Combat
    private int attackDamage;
    private int knifeAttackRange, gunAttackRange;
    private boolean gotHurt;
    private int iframes = 0; // 'invincibility frames'

    private final Sprite knife;
    private final Sprite sprite;

    private Health playerHealth;
    private float expectedFallDamage = 0;

    private boolean attack = false;

    private final boolean playerTest;   // for testing, since we cannot use Gdx.graphics::getDeltaTime in tests.

    /**
     * Constructor method that creates a new Player object.
     * @param width the width of the player sprite
     * @param height the height of the player sprite
     * @param body a Body object that represents the physical body of the player in the Box2D world
     */
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 20f;   //?? Introduce constant?
        this.attackDamage = 50;
        this.knifeAttackRange = 40;
        this.gunAttackRange = 40;

        knifeObj = new Knife();

        this.jumpCounter = 0;
        this.facing = Direction.NONE;
        this.knife = new Sprite(new Texture("assets/Player/Weapons/knife.png"));

        playerHealth = new Health();

        this.gun = new Gun(700f, 20, 500, 0.5f, "assets/Player/Weapons/gunBullet.png", "assets/Player/Weapons/gun.png");

        anim = new AnimationHandler<>(PlayerState.Idle, new Animation("assets/Player/Idle/Idle%d.png", 4));
        anim.addAnimation(PlayerState.Run, new Animation("assets/Player/Run/Run%d.png", 8));
        anim.addAnimation(PlayerState.Hurt, new Animation("assets/Player/Hurt/Hurt%d.png", 1));
        anim.addAnimation(PlayerState.Jump, new Animation("assets/Player/Jump/Jump%d.png", 3));
        anim.addAnimation(PlayerState.Fall, new Animation("assets/Player/Fall/Fall%d.png", 3));
        anim.addAnimation(PlayerState.Attack, new Animation("assets/Player/Attack/Attack%d.png", 3));
        anim.setState(PlayerState.Idle);

        this.sprite = new Sprite(anim.getAnimTexture());
        this.sprite.setScale(2);
        flip();
        playerTest = false;
    }


    /**
     * Constructor method that creates a new Player object.
     * @param width the width of the player sprite
     * @param height the height of the player sprite
     * @param body a Body object that represents the physical body of the player in the Box2D world
     */
    public Player(float width, float height, Body body, boolean test) {
        super(width, height, body);
        this.speed = 20f;
        this.attackDamage = 50;
        this.knifeAttackRange = 40;
        this.gunAttackRange = 40;

        knifeObj = new Knife();

        this.jumpCounter = 0;
        this.facing = Direction.NONE;
        this.knife = new Sprite();

        playerHealth = new Health();

        this.gun = new Gun(700f, 20, 500, 0.5f, new Sprite(), new Sprite());

        anim = new AnimationHandler<>(PlayerState.Idle, new Animation(4));
        anim.addAnimation(PlayerState.Run, new Animation(8));
        anim.addAnimation(PlayerState.Hurt, new Animation(1));
        anim.addAnimation(PlayerState.Jump, new Animation(3));
        anim.addAnimation(PlayerState.Fall, new Animation(3));
        anim.addAnimation(PlayerState.Attack, new Animation(3));
        anim.setState(PlayerState.Idle);

        this.sprite = new Sprite();
        this.sprite.setScale(2);
        flip();

        playerTest = true;
    }


    /**
     * This method updates the player object every game loop.
     * It updates the sprite based on the player's current state,
     * updates the player's position and velocity, updates the gun object,
     * checks user input using the KeyHandler object, and checks for fall damage.
     */
    @Override
    public void update() {
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;

        updateSprite();
        gun.update(playerTest ? 1/60f : Gdx.graphics.getDeltaTime());
        checkFallDamage();
        unlockGun();
        updateFrames();
    }

    /**
     * This method renders the player object to the screen using a SpriteBatch.
     * It positions and draws the player's sprite, and also draws the knife or gun if the player is attacking.
     */
    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx, dy);
        sprite.draw(batch);

        if (knifeObj.getHoldKnife()) {
            this.attack = true;
            knife.setPosition(dx + (sprite.isFlipX() ? -width : width), dy);
            knife.draw(batch);
        }

        if (gun.getHoldGun()){
            gun.setPosition(new Vector2(dx + (sprite.isFlipX() ? -width-5 : width+5), dy + 5));
            gun.renderBullets(batch);

            if (gun.getFiring()){
                this.attack = true;
                gun.fire(new Vector2(dx + (sprite.isFlipX() ? -width + 40 : width), dy + (sprite.isFlipX() ? 3 : 19)), (sprite.isFlipX() ? new Vector2(-10,0) : new Vector2(10,0)) );
                gun.renderGun(batch);
            }
        }
    }

    /**
     *  This method updates the player's sprite based on the player's current state.
     */
    public void updateSprite() {
        if (attack) {
            if (anim.getState() != PlayerState.Attack)
                anim.reset();
            anim.setState(PlayerState.Attack);
        } else if (gotHurt) {
            anim.setState(PlayerState.Hurt);
            gotHurt = false;
        } else if (facing == Direction.NONE && getBody().getLinearVelocity().y == 0) {
            anim.setState(PlayerState.Idle);
        } else if (getBody().getLinearVelocity().y > 0) {  // Checking if player is jumping
            anim.setState(PlayerState.Jump);
        } else if (getBody().getLinearVelocity().y < 0) {  // Checking if player is falling
            anim.setState(PlayerState.Fall);
        } else {
            anim.setState(PlayerState.Run);
        }

        if (anim.getState() == PlayerState.Attack && anim.getCurrFrame() == 3) {
            this.attack = false;
        }

        anim.update(anim.getState() == PlayerState.Idle ? ANIM_FRAME_RATE_IDLE : ANIM_FRAME_RATE_DEFAULT);
        sprite.setTexture(anim.getAnimTexture());
    }

    /**
     * This method makes the player entity jump.
     * It first calculates the force required to make the player jump by multiplying the player's mass by 10 (gravity) and 2 (jump strength).
     * Then it sets the player's y-velocity to 0 and applies the calculated force as a linear impulse to the player's body.
     * Finally, it increments the jump counter.
     */
    public void jump() {
        float force = body.getMass() * 21;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
    }

    /**
     * Flip the player
     */
    @Override
    public void flip() {
        sprite.flip(true, false);
        knife.flip(true, false);
        gun.getSprite().flip(true,false);
    }

    /**
     * This method is used to move the player entity.
     * It takes a direction and sets the player's x-velocity depending on the direction.
     * @param direction the direction the player is going.
     */
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
     * Setter method for setting the Health object
     * @param health new health
     */
    public void setPlayerHealth(Health health) {
        this.playerHealth = health;
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
     * Checks and apply fall damage if the player has fallen from too high
     */
    public void checkFallDamage(){
        float multiplier = 1.0f;
        if(isGrounded() && expectedFallDamage > 37){
            int damageScale = (int)(expectedFallDamage * multiplier);
            System.out.println("Fall damage: "+damageScale);
            playerHealth.decreaseHP(damageScale);
        }

        expectedFallDamage = Math.abs(body.getLinearVelocity().y);
    }

    /**
     * This method returns the player's attack damage.
     * @return the attack damage of the player
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * For testing purposes
     */
    public void setAttackDamage(int damage) {
        this.attackDamage = damage;
    }

    /**
     * This method returns the range of the player's knife attack.
     * @return the range of the player's knife attack
     */
    public int getKnifeAttackRange() {
        return this.knifeAttackRange;
    }

    /**
     * Testing purposes
     */
    public void setKnifeAttackRange(int range) {
        this.knifeAttackRange = range;
    }

    /**
     * This method returns the range of the player's gun attack.
     * @return the range of the player's gun attack
     */
    public int getGunAttackRange() {
        return this.gunAttackRange;
    }

    /**
     * Testing purposes
     */
    public void setGunAttackRange(int range) {
        this.gunAttackRange = range;
    }

    /**
     * This method is called when the player is hurt by an enemy.
     */
    public void gotHurt(int damage) {
        if(iframes == 0) {
            if (!playerTest)
                this.audioManager.play("Hurt");
            this.gotHurt = true;

            playerHealth.decreaseHP(damage);

            iframes = 30;
        }
    }

    /**
     * Update the 'frames left' of certain variables.
     *
     * Decreases both 'iframe timer' and the knife's 'next attack frame timer'.
     */
    private void updateFrames(){
        if(iframes > 0){
            iframes--;
        }
        if(knifeObj.nextAttack > 0){
            knifeObj.nextAttack--;
        }
    }

    /**
     * TESTING ONLY: WITHOUT TIMER OR SOUND
     */
    public void gotHurtTest() {
        this.gotHurt = true;
    }

    /**
     * This method returns the player's Gun object.
     * @return the player's Gun object.
     */
    public Gun getGun(){
        return gun;
    }

    /**
     * This method returns whether the player is currently hurt.
     * @return a bool that tells whether the player is currently hurt
     */
    public boolean isHurt(){
        return gotHurt;
    }

    /**
     * Unlock the gun.
     */
    public void unlockGun(){
        if (killCount >= PRICE_GUN){
            this.gun.setUnlocked();
        }
    }

    /**
     * Currently used for testing
     */
    public AudioManager getAudio() {
        return this.audioManager;
    }

    /**
     * Currently used for testing
     */
    public Sprite getSprite() {
        return this.sprite;
    }
    
    /**
     * Currently used for testing
     */
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    /**
     * Get the current kill count.
     * @return the total amount of enemies killed.
     */
    public int getKillCount(){
        return this.killCount;
    }

    /**
     * Get the attack state of player.
     * @return the attack state.
     */
    public boolean getAttack(){
        return this.attack;
    }

    /**
     * Set the attack state of player.
     */
    public void setAttack(boolean attack){
        this.attack = attack;
    }

    /**
     * Get the attack state of player.
     * @return the attack state.
     */
    public AnimationHandler<PlayerState> getAnimationHandler(){
        return this.anim;
    }

}