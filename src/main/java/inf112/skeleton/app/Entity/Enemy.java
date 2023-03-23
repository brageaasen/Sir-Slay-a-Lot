package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.AnimationHandler;
import inf112.skeleton.app.Health;

public class Enemy extends GameEntity {

    public enum EnemyState {
        Run,
//        Hurt,
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
    private long endTime;
    private long elapsedTime;
    private float playerPositionX, playerPositionY;
    private float enemyPositionX, enemyPositionY;
    public static float enemyPos;
    private final Player player;
    private final Health enemyHealth;
    private Health maxHealth;
    private Direction facing;

    private int attackRange, attackDamage;
    private boolean attack = false;
    private boolean justAttacked = false;

    // Sprite field variables
    private int attackTimer;

    public Enemy(float width, float height, Body body, Player player) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        this.player = player;
        this.attackRange = 40;
        this.attackDamage = 1;

        anim = new AnimationHandler<>(EnemyState.Run, "assets/Enemy/Run.png", 6);
//        anim.addAnimation(CurrentSprite.Hurt, "assets/Enemy/Hurt.png", 1);
        anim.addAnimation(EnemyState.Jump, "assets/Enemy/Jump.png", 1);
        anim.addAnimation(EnemyState.Fall, "assets/Enemy/Fall.png", 1);
        anim.addAnimation(EnemyState.Attack, "assets/Enemy/Attack.png", 6);
        anim.addAnimation(EnemyState.Dead, "assets/Enemy/Dead.png", 6);
        anim.addAnimation(EnemyState.Hit, "assets/Enemy/Hit.png", 3);
        this.sprite = new Sprite(anim.getAnimTexture());
        this.sprite.setScale(2);
        enemyHealth = new Health();
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;
        
        if (!this.attack)
            updatePosition();
        else
            attackTimer++;
        
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
            anim.setState(EnemyState.Run);
        } else if (this.getBody().getLinearVelocity().y > 0) {  // Checking if enemy is jumping
            anim.setState(EnemyState.Jump);
        } else if (this.getBody().getLinearVelocity().y < 0) {  // Checking if enemy is falling
            anim.setState(EnemyState.Fall);
        } else if (enemyIsDead()) {
            anim.setState(EnemyState.Dead);
        } else if (this.attack) {
            if (anim.getState() != EnemyState.Attack) {
                anim.reset();
                this.justAttacked = false;
            }
            anim.setState(EnemyState.Attack);
        } 
        else {
            anim.setState(EnemyState.Run);
        }

        if (anim.getState() == EnemyState.Attack && attackTimer > 6) {
            this.attack = false;
        }

        anim.update(10);
        anim.updateSprite(sprite, flipped);
    }


    private void updatePosition() {
        velX = 0;
        if (player == null)
            return;
        playerPositionX = player.getPosition().x;
        enemyPositionX = body.getPosition().x * PPM + 5;

        if(enemyPositionX < playerPositionX){
            if (this.facing != Direction.RIGHT && !flipped)
                flip();
            this.facing = Direction.RIGHT;
            velX = 1;
        }else if(enemyPositionX > playerPositionX){
            if (this.facing != Direction.LEFT && flipped)
                flip();
            this.facing = Direction.LEFT;
            velX = -1;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
        enemyPos = body.getPosition().x;

        double random = Math.random(); //for random jumping
        if(random <= 0.01 && jumpCounter < 2 && isGrounded()){
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

        if (Math.abs(playerPositionX - enemyPositionX) < player.getAttackRange() && Math.abs(playerPositionY - enemyPositionY) < player.getAttackRange() && player.knifeObj.getHoldKnife() && player.knifeObj.isDealingDamage()) {
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
            if (anim.getState() == EnemyState.Attack && anim.getCurrFrame()== 4 && !this.justAttacked) {
                player.getPlayerHealth().decreaseHP(this.attackDamage);
                player.gotHurt();
                this.justAttacked = true;
            }
        }
    }


    public boolean enemyIsDead() {
        return enemyHealth.getHP() <= 0;
    }

    /**
     * Checks if a enemy is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return body.getLinearVelocity().y == 0;
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

}
