package inf112.skeleton.app.Entity;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.app.Animations.AnimationHandler;
import inf112.skeleton.app.Back_end.AudioManager;
import inf112.skeleton.app.Weapons.Bullet;

/*
 * This class is where the Enemy entity is created. It extends the GameEntity class.
 */
public class Enemy extends GameEntity {

    private static final int ANIM_FRAME_RATE = 10;

    public enum EnemyState {
        Run,
        Jump,
        Fall,
        Attack,
        Dead,
        Hit,
    }

    private final AnimationHandler<EnemyState> anim;
    private int jumpCounter;
    private final Sprite sprite;
    private static final int PPM = 16;

    private long startTime = 0;
    private float playerPositionX, playerPositionY;
    private float enemyPositionX, enemyPositionY;
    public static float enemyPos;
    private float lastPos;
    private final Player player;
    private Health enemyHealth;
    private Direction facing;

    private boolean gotHit;
    private boolean canMove = true;

    private int attackRange;
    private int attackDamage;
    private boolean attack = false;
    private boolean dead = false;

    // Audio
    private final AudioManager audioManager = new AudioManager();

    // Sprite field variables
    private int attackTimer;

    /**
     * The constructor of the Enemy class
     * @param width the width of the enemy.
     * @param height the height of the enemy.
     * @param body a Body object representing the Box2D body of the enemy.
     * @param player a Player object representing the player.
     */
    public Enemy(float width, float height, Body body, Player player) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        this.player = player;
        this.attackRange = 40;
        this.attackDamage = 10;

        anim = new AnimationHandler<>(EnemyState.Run, "assets/Enemy/Run/Run%d.png", 6);
        anim.addAnimation(EnemyState.Jump, "assets/Enemy/Jump/Jump%d.png", 1);
        anim.addAnimation(EnemyState.Fall, "assets/Enemy/Fall/Fall%d.png", 1);
        anim.addAnimation(EnemyState.Attack, "assets/Enemy/Attack/Attack%d.png", 6);
        anim.addAnimation(EnemyState.Dead, "assets/Enemy/Dead/Dead%d.png", 6);
        anim.addAnimation(EnemyState.Hit, "assets/Enemy/Hit/Hit%d.png", 3);
        this.sprite = new Sprite(anim.getAnimTexture());
        this.sprite.setScale(2);
        enemyHealth = new Health();

        new Timer();
    }

    /**
     * This method updates the state of the Enemy object.
     */
    @Override
    public void update() {
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;

        if (!this.attack && this.canMove) {
            move(null);
        } else {
            body.setLinearVelocity(0, body.getLinearVelocity().y);    // We will still allow it to fall, otherwise it will hover in the air.
            attackTimer++;
        }

        updateSprite();
        takeDamage();
        dealDamage();
    }

    /**
     * This method renders the Enemy object on the screen.
     * @param batch a SpriteBatch object representing the batch used to draw the enemy.
     */
    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width - 15;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);
    }

    /**
     * This method updates the position of the enemy based on the position of the player.
     * It sets the velX field to 1 if the enemy is to the left of the player, -1 if the enemy is to the right of the player, and 0 otherwise.
     */
    @Override
    public void move(Direction direction) {
        if (enemyHealthIsZero() || player == null) {
            System.out.println("health is zero");
            body.setLinearVelocity(0,0);
            return;
        }

        velX = 0;
        playerPositionX = player.getPosition().x;
        enemyPositionX = body.getPosition().x * PPM + 5;

        if (enemyPositionX < playerPositionX - PPM){
            if (this.facing != Direction.RIGHT && sprite.isFlipX()) // Flip sprite if facing wrong way
                flip();
            this.facing = Direction.RIGHT;
            velX = 1;
        } else if (enemyPositionX > playerPositionX + PPM){
            if (this.facing != Direction.LEFT && !sprite.isFlipX()) // Flip sprite if facing wrong way
                flip();
            this.facing = Direction.LEFT;
            velX = -1;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
        enemyPos = body.getPosition().x;

        if((lastPos == enemyPositionX || body.getLinearVelocity().x == 0) && jumpCounter > 0 && isGrounded()){
            startTime = System.currentTimeMillis();
            float force = body.getMass() * 30 * 2;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            jumpCounter--;
        }
        lastPos = enemyPositionX;

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        if(this.isGrounded() && elapsedTime >= 250){
            jumpCounter = 1;
        }
    }

    /**
     * This method updates the sprite of the enemy based on its current state
     */
    public void updateSprite() {
        if (enemyHealthIsZero() && !enemyIsDead()) {
            anim.setState(EnemyState.Dead);
        } else if (this.gotHit) {
            anim.setState(EnemyState.Hit);
        } else if (this.getBody().getLinearVelocity().y != 0 && this.isGrounded()) {
            anim.setState(EnemyState.Run);
        } else if (this.getBody().getLinearVelocity().y > 0) {  // Checking if enemy is jumping
            anim.setState(EnemyState.Jump);
        } else if (this.getBody().getLinearVelocity().y < 0) {  // Checking if enemy is falling
            anim.setState(EnemyState.Fall);
        } else if (this.attack) {
            anim.setState(EnemyState.Attack);
        }
        else {
            anim.setState(EnemyState.Run);
        }

        if (anim.getState() == EnemyState.Hit && anim.getCurrFrame() == 3) {  // Hit animation is done
            canMove = true;
            gotHit = false;
        } else if (anim.getState() == EnemyState.Dead && anim.getCurrFrame() == 6) {  // Death animation is done
            this.dead = true;
            player.killCount++;
            System.out.println("Kill Count: "+player.killCount);
        } else if (anim.getState() == EnemyState.Attack && attackTimer > 6) {
            this.attack = false;
        }

        anim.update(ANIM_FRAME_RATE);
        sprite.setTexture(anim.getAnimTexture());
    }


    /**
     * Take damage from player based on player current attack damage
     */
    public void takeDamage() {
        playerPositionX = player.getPosition().x;
        playerPositionY = player.getPosition().y;
        enemyPositionX = body.getPosition().x * PPM + 5;
        enemyPositionY = body.getPosition().y * PPM + 5;

        List<Bullet> bullets = player.getGun().getBullets();

        for (Bullet bullet : bullets){
            if (bullet.getBulletHit())  // Skip bullets that have already hit something
                continue;

            if (Math.abs(bullet.getPosition().x - enemyPositionX) < player.getGunAttackRange() && Math.abs(bullet.getPosition().y - enemyPositionY) < player.getGunAttackRange()
            && !this.gotHit)
            {
                enemyHealth.decreaseHP(player.getAttackDamage());
                bullet.setBulletHit(true);
                this.gotHit();
            }
        }

        if (Math.abs(playerPositionX - enemyPositionX) < player.getKnifeAttackRange() && Math.abs(playerPositionY - enemyPositionY) < player.getKnifeAttackRange()
         && player.knifeObj.getHoldKnife() && player.knifeObj.getDealingDamage() && !this.gotHit && player.knifeObj.getNextAttack() == 0)
        {
            enemyHealth.decreaseHP(player.getAttackDamage());
            player.knifeObj.setNextAttack();
            player.knifeObj.setDealingDamage(false);
            this.gotHit();
        }
    }

    /**
     * This method checks if the enemy is in range of the player and, if so, deals damage to the player
     */
    public void dealDamage() {
        playerPositionX = player.getPosition().x;
        playerPositionY = player.getPosition().y;
        enemyPositionX = body.getPosition().x * PPM + 5;
        enemyPositionY = body.getPosition().y * PPM + 5;

        if (Math.abs(playerPositionX - enemyPositionX) < this.attackRange &&
         Math.abs(playerPositionY - enemyPositionY) < this.attackRange) {
            this.attack = true;
            if (anim.getState() == EnemyState.Attack && anim.getCurrFrame() == 4) {
                player.gotHurt(this.attackDamage);
            }
        }
    }

    /**
     * Method to check if enemy health is below or equal to zero.
     * @return `true` if the enemy has no more health, `false` otherwise.
     */
    public boolean enemyHealthIsZero() {
        return enemyHealth.getHP() <= 0;
    }

    /**
     * Testing purposes
     */
    public void setEnemyHealth(Health health){
        this.enemyHealth = health;
    }

    /**
     * Method to check if enemy is dead.
     * @return a bool that tells us if the enemy is dead or not
     */
    public boolean enemyIsDead(){
        return this.dead;
    }

    /**
     * Checks if an enemy is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return body.getLinearVelocity().y == 0;
    }

    /*
     * This method flips the sprite of the enemy horizontally.
     */
    @Override
    public void flip() {
        sprite.flip(true, false);
    }

    /**
     * Method that returns the Health of the enemy.
     * @return the Health of the enemy.
     */
    public Health getHealth() {
        return this.enemyHealth;
    }

    /**
     * Method that check whether the enemy got hit. If so, it plays  sound and stops its movement.
     */
    public void gotHit() {
        this.audioManager.play("Hit");
        this.gotHit = true;
        this.canMove = false;
        this.attack = false;
    }
}
