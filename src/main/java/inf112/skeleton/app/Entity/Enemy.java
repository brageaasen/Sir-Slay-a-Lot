package inf112.skeleton.app.Entity;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Bullet;
import inf112.skeleton.app.Health;

public class Enemy extends GameEntity {

    public enum CurrentSprite {
        Run(6),
        Hurt(1),
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
    private final Player player;
    private Health enemyHealth;
    private Health maxHealth;
    private Direction facing;

    private int attackRange, attackDamage;
    private boolean attack = false;
    private boolean justAttacked = false;
    private boolean dead = false;

    // Sprite field variables
    private int spriteCounter;
    private int spriteNum;
    private CurrentSprite currentSprite;

    public Enemy(float width, float height, Body body, Player player) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        this.player = player;
        this.spriteNum = 1;
        this.attackRange = 40;
        this.attackDamage = 1;

        this.sprite = new Sprite(new Texture("assets/Enemy/Run/Run1.png"));
        this.sprite.setScale(2);
        enemyHealth = new Health();
    }

    @Override
    public void update() {
        spriteChecker();
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;
        
        if (!this.attack)
            updatePosition();
        
        updateSprite();
        takeDamage();
        dealDamage();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width - 15;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);
    }

    @Override
    public void move(Direction direction) {

    }

    public void updateSprite() {
        if (this.getBody().getLinearVelocity().y != 0 && this.isGrounded()) {
            currentSprite = CurrentSprite.Run;
        } else if (this.getBody().getLinearVelocity().y > 0) {  // Checking if enemy is jumping
            currentSprite = CurrentSprite.Jump;
        } else if (this.getBody().getLinearVelocity().y < 0) {  // Checking if enemy is falling
            currentSprite = CurrentSprite.Fall;
        } else if (enemyHealthIsZero()) {
            currentSprite = CurrentSprite.Dead;
        } else if (this.attack) {
            if (currentSprite != CurrentSprite.Attack)
                spriteNum = 1;
                this.justAttacked = false;
            currentSprite = CurrentSprite.Attack;
        } 
        else {
            currentSprite = CurrentSprite.Run;
        }

        if (spriteNum > currentSprite.frames) // Check if spriteNum is out of bounds for the given animation
            spriteNum = 1;

        if (currentSprite == CurrentSprite.Attack && spriteCounter > 6) {
            this.attack = false;
        }

        if (currentSprite == CurrentSprite.Dead && spriteNum > 4) {
            this.dead = true;
        }

        sprite.setTexture(new Texture("assets/Enemy/%s/%s%d.png".formatted(currentSprite.toString(), currentSprite.toString(), spriteNum)));
    }

    private void spriteChecker() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteCounter = 0;
            spriteNum++;
        }
    }


    private void updatePosition() {
        velX = 0;
        if (player == null)
            return;
        playerPositionX = player.getPosition().x;
        enemyPositionX = body.getPosition().x * PPM + 5;

        if(enemyPositionX < playerPositionX-PPM){
            if (this.facing != Direction.RIGHT && sprite.isFlipX()) // Flip sprite if facing wrong way
                flip();
            this.facing = Direction.RIGHT;
            velX = 1;
        }else if(enemyPositionX > playerPositionX+PPM){
            if (this.facing != Direction.LEFT && !sprite.isFlipX()) // Flip sprite if facing wrong way
                flip();
            this.facing = Direction.LEFT;
            velX = -1;
        }else{
            velX = 0;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
        enemyPos = body.getPosition().x;

        //double random = Math.random(); //for random jumping
        float lastPos = enemyPositionX;
        System.out.println("last: "+lastPos+" current: "+enemyPositionX);
        if(body.getLinearVelocity().x == 0 && jumpCounter < 2 && isGrounded()){
            startTime = System.currentTimeMillis();
            float force = body.getMass() * 10 * 2;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            jumpCounter++;
        }
    
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
            if (Math.abs(bullet.getPosition().x - enemyPositionX) < this.attackRange && Math.abs(bullet.getPosition().y - enemyPositionY) < this.attackRange  && player.gun.getHoldGun()){
                enemyHealth.decreaseHP(player.getAttackDamage());
                bullet.setBulletHit(true);
            }
        }


        if (Math.abs(playerPositionX - enemyPositionX) < this.attackRange && Math.abs(playerPositionY - enemyPositionY) < this.attackRange && player.knifeObj.getHoldKnife() && player.knifeObj.getDealingDamage()) {
            enemyHealth.decreaseHP(player.getAttackDamage());
            player.knifeObj.setDealingDamage(false);
        }
    }

    public void dealDamage() {
        playerPositionX = player.getPosition().x;
        playerPositionY = player.getPosition().y;
        enemyPositionX = body.getPosition().x * PPM + 5;
        enemyPositionY = body.getPosition().y * PPM + 5;

        if (Math.abs(playerPositionX - enemyPositionX) < this.attackRange && Math.abs(playerPositionY - enemyPositionY) < this.attackRange) {
            this.attack = true;
            if (currentSprite == CurrentSprite.Attack && spriteNum == 4 && this.justAttacked == false) {
                player.getPlayerHealth().decreaseHP(this.attackDamage);
                player.gotHurt();
                this.justAttacked = true;
            }
        }
    }


    public boolean enemyHealthIsZero() {
        return enemyHealth.getHP() <= 0;
    }

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

    public void flip() {
        sprite.flip(true, false);
    }

    // Get direction enemy is facing
    public Direction getDirection() {
        return this.facing;
    }

    public Health getHealth() {
        return this.enemyHealth;
    }

    public Health getMaxHealth() {
        return this.maxHealth;
    }

    public Enemy clone() {
        return new Enemy(width, height, this.getBody(), player);
    }

}
