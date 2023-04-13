package inf112.skeleton.app.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Gun;
import inf112.skeleton.app.Health;
import inf112.skeleton.app.KeyHandler;
import inf112.skeleton.app.Knife;

public class Player extends GameEntity {
    public enum CurrentSprite {
        Idle(4),
        Run(8),
        Hurt(1),
        Jump(3),
        Fall(3),
        Attack(4);

        final int frames;
        CurrentSprite(int i) {
            frames = i;
        }
    }

    private static final int PPM = 16; //?? what does this mean???

    public int jumpCounter;     //?? Set to private, change using API (e.g. 'Jump')
    private int spriteCounter, spriteNum;
    private CurrentSprite currentSprite;
    private Direction facing;
    public Knife knifeObj;
    public Gun gun; 

    // Combat
    private int attackDamage;
    private int attackRange;
    private boolean gotHurt;

    private final Sprite knife;
    private final KeyHandler keyH;
    private final Sprite sprite;

    private final Health playerHealth; 

    private boolean attack = false;


    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 20f;   //?? Introduce constant?
        this.attackDamage = 5;
        this.attackRange = 5;

        knifeObj = new Knife();

        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.currentSprite = CurrentSprite.Idle;

        this.jumpCounter = 0;
        this.facing = Direction.NONE;
        this.sprite = new Sprite(new Texture("assets/Player/Idle/Idle1.png")); //?? Should we preload textures instead of loading them every time? (does it even matter?)
        this.knife = new Sprite(new Texture("assets/knife.png"));
        this.keyH = new KeyHandler(this);   //?? Should the player class hold input handling?
        this.sprite.setScale(2);

        playerHealth = new Health();

        this.gun = new Gun(700f, 20, 500, 0.5f, "assets/gunBullet.png", "assets/gun.png");
        
    }

    @Override
    public void update() {
        if (currentSprite == CurrentSprite.Idle)
            spriteChecker(8);
        else 
            spriteChecker(6);
        
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;


        updateSprite();
        gun.update(Gdx.graphics.getDeltaTime());
        keyH.checkUserInput();
        this.checkFallDamage();
        // dealDamage();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx, dy);
        sprite.draw(batch);

        if (knifeObj.isHoldKnife()) {
            this.attack = true;
            knife.setPosition(dx + (sprite.isFlipX() ? -width : width), dy);
            knife.draw(batch);

        }

        if (gun.getHoldGun()){
            gun.setPosition(new Vector2(dx + (sprite.isFlipX() ? -width-5 : width+5), dy + 5));
            gun.renderBullets(batch);
            
            if (gun.getFiring()){
                this.attack = true;
                gun.fire(new Vector2(dx + (sprite.isFlipX() ? -width + 40 : width), dy + (sprite.isFlipX() ? 0 : 13)), (sprite.isFlipX() ? new Vector2(-10,0) : new Vector2(10,0)) );
                gun.renderGun(batch);
            }
        }
    }

    public void updateSprite() {
        if (this.attack) {
            if (currentSprite != CurrentSprite.Attack)
                spriteNum = 1;
            currentSprite = CurrentSprite.Attack;
            
        } 

        else if (this.gotHurt == true) {
            currentSprite = CurrentSprite.Hurt;
            this.gotHurt = false;
        } else if (facing == Direction.NONE && getBody().getLinearVelocity().y == 0) {
            currentSprite = CurrentSprite.Idle;
        } 
        else if (getBody().getLinearVelocity().y < 0) {
            currentSprite = CurrentSprite.Idle;
        }
        else if (getBody().getLinearVelocity().y > 0) {  // Checking if player is jumping
            currentSprite = CurrentSprite.Jump;
        } else if (getBody().getLinearVelocity().y < -3) {  // Checking if player is falling
            System.out.println(getBody().getLinearVelocity().y);
            currentSprite = CurrentSprite.Fall;
        } 
    
        else {
            currentSprite = CurrentSprite.Run;
        }

        if (spriteNum > currentSprite.frames) // Check if spriteNum is out of bounds for the given animation
            spriteNum = 1;

        if (currentSprite == CurrentSprite.Attack && spriteCounter > 4) {
            this.attack = false;
        }

        sprite.setTexture(new Texture("assets/Player/%s/%s%d.png".formatted(currentSprite.toString(), currentSprite.toString(), spriteNum)));
    }


    public void jump() {
        float force = body.getMass() * 10 * 2;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
        jumpCounter++;
    }


   

    /**
     * n is speed of which sprites changes
     */
    private void spriteChecker(int n) {
        spriteCounter++;
        if (spriteCounter > n) {
            spriteCounter = 0;
            spriteNum++;
        }
    }

    public void flip() { // TODO?: replace unneeded texture?
        sprite.flip(true, false);
        knife.flip(true, false);
        gun.getSprite().flip(true, false);
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

    public void fireGun() {
        Vector2 position = new Vector2(x, y);
        Vector2 direction = new Vector2(facing == Direction.RIGHT ? 1 : -1, 0);
        gun.fire(position, direction);
    }

    public Gun getGun(){
        return gun;
    }

}