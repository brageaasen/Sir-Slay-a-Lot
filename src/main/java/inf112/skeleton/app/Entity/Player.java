package inf112.skeleton.app.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.app.AudioManager;
import inf112.skeleton.app.Gun;
import inf112.skeleton.app.Health;
import inf112.skeleton.app.KeyHandler;
import inf112.skeleton.app.Knife;
import inf112.skeleton.app.*;


/*
 * This class is where the Player entity is created. It extends the GameEntity class.
 */
public class Player extends GameEntity {

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

    private static final int PPM = 16; //Pixel Per Meter

    public int jumpCounter;
    private Direction facing;
    public Knife knifeObj;
    public Gun gun;
    public int killCount = 0;

    // Audio
    private AudioManager audioManager = new AudioManager();

    // Combat
    private int attackDamage;
    private int knifeAttackRange, gunAttackRange;
    private boolean gotHurt;
    private int iframes = 0; //invinsibility frames

    private Timer timer;
    private final Sprite knife;
    private final KeyHandler keyH;
    private final Sprite sprite;

    private Health playerHealth;
    private float expectedFallDamage = 0;

    private boolean attack = false;

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
        this.keyH = new KeyHandler(this);   //?? Should the player class hold input handling?

        playerHealth = new Health();

        this.gun = new Gun(700f, 20, 500, 0.5f, "assets/Player/Weapons/gunBullet.png", "assets/Player/Weapons/gun.png");

        this.timer = new Timer();

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
        gun.update(Gdx.graphics.getDeltaTime());
        keyH.checkUserInput();
        this.checkFallDamage();
        this.unlockGun();
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
     * Checks and apply falldamage if the player has fallen from too high
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
     * Testing purposes
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
        if(iframes == 0){
            this.audioManager.Play("Hurt");
            this.gotHurt = true;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
            }        
        }, 1);


        playerHealth.decreaseHP(damage);

        iframes = 30;
        }

    }

    private void updateFrames(){
        if(iframes > 0){
            iframes--;
        }
        if(knifeObj.nextAttack > 0){
            knifeObj.nextAttack--;
        }
    }

    /**
     * TESTING ONLY: WITHOUT TIMER
     */
    public void gotHurtTest() {
        this.getAudio().Play("Hurt");
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
        if (killCount >= 5){
            this.gun.setUnlocked();
        }
    }

    /**
     * Get the current kill count.
     * @return the total amount of enemies killed.
     */
    public int getKillcount(){
        return this.killCount;
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
    public int getKillCount() {
        return this.killCount;
    }
    
    /**
     * Currently used for testing
     */
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }
}