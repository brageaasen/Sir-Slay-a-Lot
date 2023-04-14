package inf112.skeleton.app.Entity;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.app.AudioManager;
import inf112.skeleton.app.Bullet;
import inf112.skeleton.app.Health;

/*
 * This class is where the Enemy entity is created. It extends the GameEntity class. 
 */
public class Enemy extends GameEntity {

    public enum CurrentSprite {
        Run(6),
        Jump(1),
        Fall(1),
        Attack(6),
        Dead(6),
        Hit(3);

        final int frames;
        CurrentSprite(int i) {
            frames = i;
        }
    }

    private int jumpCounter;
    private final Sprite sprite;
    private static final int PPM = 16;

    private long startTime = 0;
    private long endTime;
    private long elapsedTime;
    private float playerPositionX, playerPositionY;
    private float enemyPositionX, enemyPositionY;
    public static float enemyPos;
    private float lastPos;
    private final Player player;
    private Health enemyHealth;
    private Health maxHealth;
    private Direction facing;

    private boolean gotHit;
    private boolean canMove = true;

    private Timer timer;

    private int attackRange, attackDamage;
    private boolean attack = false;
    private boolean justAttacked = false;
    private boolean dead = false;

    // Audio
    private AudioManager audioManager = new AudioManager();

    // Sprite field variables
    private int spriteCounter;
    private int spriteNum;
    private CurrentSprite currentSprite;

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
        this.spriteNum = 1;
        this.attackRange = 40;
        this.attackDamage = 5;

        this.sprite = new Sprite(new Texture("assets/Enemy/Run/Run1.png"));
        this.sprite.setScale(2);
        enemyHealth = new Health();

        this.timer = new Timer();
    }

    /**
     * This method updates the state of the Enemy object.
     */
    @Override
    public void update() {
        spriteChecker();
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;
        
        //System.out.println(this.canMove);
        if (!this.attack && this.canMove)
            updatePosition();
        else
            body.setLinearVelocity(0,0);    // Attacking or can't move, so we don't move it
        //System.out.println(this.canMove);
        
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

    public void resetHitFlags() {
        
    }

    @Override
    public void move(Direction direction) {

    }

    /**
     * This method updates the sprite of the enemy based on its current state
     */
    public void updateSprite() {
        var lastState = currentSprite;

        if (enemyHealthIsZero()) {
            currentSprite = CurrentSprite.Dead;
        } else if (this.getBody().getLinearVelocity().y != 0 && this.isGrounded()) {
            currentSprite = CurrentSprite.Run;
        } else if (this.gotHit) {
            currentSprite = CurrentSprite.Hit;
            this.gotHit = false;
        } else if (this.getBody().getLinearVelocity().y > 0) {  // Checking if enemy is jumping
            currentSprite = CurrentSprite.Jump;
        } else if (this.getBody().getLinearVelocity().y < 0) {  // Checking if enemy is falling
            currentSprite = CurrentSprite.Fall;
        } else if (this.attack) {
            if (currentSprite != CurrentSprite.Attack)
                this.justAttacked = false;
            currentSprite = CurrentSprite.Attack;
        } 
        else if (canMove){
            currentSprite = CurrentSprite.Run;
        }

        if (currentSprite == CurrentSprite.Hit && spriteNum > 3) {  // Hit animation is done
            canMove = true;
        } else if (currentSprite == CurrentSprite.Dead && spriteNum > 6) {  // Death animation is done
            this.dead = true;
            player.killCount++;
            System.out.println("Kill Count: "+player.killCount);
        }

        // Reset the animation timers if we change animation state or the spriteNum is out of bounds for the given animation
        if (lastState != currentSprite || spriteNum > currentSprite.frames) {
            spriteNum = 1;
            spriteCounter = 0;
        }


        if (currentSprite == CurrentSprite.Attack && spriteCounter > 6) {
            this.attack = false;
        }


        sprite.setTexture(new Texture("assets/Enemy/%s/%s%d.png".formatted(currentSprite.toString(), currentSprite.toString(), spriteNum)));
    }

    /**
     * This method updates the sprite of the enemy by incrementing the spriteCounter field and spriteNum if necessary.
     */
    private void spriteChecker() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteCounter = 0;
            spriteNum++;
        }
    }

    /**
     * This method updates the position of the enemy based on the position of the player. 
     * It sets the velX field to 1 if the enemy is to the left of the player, -1 if the enemy is to the right of the player, and 0 otherwise.
     */
    private void updatePosition() {
        if (enemyHealthIsZero() || player == null) {
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

        //double random = Math.random(); //for random jumping
        if((lastPos == enemyPositionX || body.getLinearVelocity().x == 0) && jumpCounter < 2 && isGrounded()){
            startTime = System.currentTimeMillis();
            float force = body.getMass() * 10 * 2;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            jumpCounter++;
        }
        lastPos = enemyPositionX;

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        if(body.getLinearVelocity().y == 0 && elapsedTime >= 250){
            jumpCounter = 0;
        }
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

            if (Math.abs(bullet.getPosition().x - enemyPositionX) < player.getGunAttackRange() && Math.abs(bullet.getPosition().y - enemyPositionY) < player.getGunAttackRange())
            {
                enemyHealth.decreaseHP(player.getAttackDamage());
                bullet.setBulletHit(true);
                this.gotHit();
            }
        }

        if (Math.abs(playerPositionX - enemyPositionX) < player.getKnifeAttackRange() && Math.abs(playerPositionY - enemyPositionY) < player.getKnifeAttackRange() && player.knifeObj.getHoldKnife() && player.knifeObj.getDealingDamage())
        {
            enemyHealth.decreaseHP(player.getAttackDamage());
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

        if (Math.abs(playerPositionX - enemyPositionX) < this.attackRange && Math.abs(playerPositionY - enemyPositionY) < this.attackRange) {
            this.attack = true;
            if (currentSprite == CurrentSprite.Attack && spriteNum == 4 && !this.justAttacked) {
                player.getPlayerHealth().decreaseHP(this.attackDamage);
                this.justAttacked = true;
                player.gotHurt();
            }
        }
    }

    /**
     * Method to check if enemy health is below or equal to zero.
     * @return
     */
    public boolean enemyHealthIsZero() {
        return enemyHealth.getHP() <= 0;
    }

    /**
     * Method to check if enemy is dead. 
     * @return a bool that tells us if the enemy is dead or not
     */
    public boolean enemyIsDead(){
        return this.dead;
    }

    /**
     * Checks if a enemy is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return body.getLinearVelocity().y == 0;
    }

    /*
     * This method flips the sprite of the enemy horizontally.
     */
    public void flip() {
        sprite.flip(true, false);
    }

    /**
     * Method that returns the direction of the enemy. 
     * @return the direction of the enemy.
     */
    public Direction getDirection() {
        return this.facing;
    }

    /**
     * Method that returns the Health of the enemy. 
     * @return the Health of the enemy.
     */
    public Health getHealth() {
        return this.enemyHealth;
    }

    /**
     * Method that returns the max health of the enemy. 
     * @return the max health of the enemy.
     */
    public Health getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Method that check whether or not the enemy got hit. If so, it plays  sound and stops it's movement. 
     */
    public void gotHit() {
        this.audioManager.Play("Hit");
        this.gotHit = true;
        this.canMove = false;
    }

    /**
     * A method to clone an enemy. 
     * @return a clone of the enemy.
     */
    public Enemy clone() {
        return new Enemy(width, height, this.getBody(), player);
    }

}
