package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Health;

public class Monster extends GameEntity {

    private int jumpCounter;
    private final Sprite sprite;
    private static final int PPM = 16;

    private long startTime = 0;
    private long endTime;
    private long elapsedTime;
    private float playerPosition;
    private float monsterPosition;
    public static float monsterPos;
    private final Player player;
    private Health monsterHealth;

    public Monster(float width, float height, Body body, Player player) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        this.player = player;

        this.sprite = new Sprite(new Texture("assets/Enemy/Run/Run1.png"));
        this.sprite.setScale(2);
        monsterHealth = new Health();
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM + 5;
        y = body.getPosition().y * PPM + 17;
        
        updatePosition();
        damage();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);
    }

    @Override
    public void move(Direction direction) {

    }

    private void updatePosition() {
        velX = 0;
        if (player == null)
            return;
        playerPosition = player.getPosition().x;
        monsterPosition = body.getPosition().x * PPM + 5;
        //System.out.println("Player: "+playerPosition+" Monster: "+ monsterPosition);
        if(monsterPosition < playerPosition){
            velX = 1;
        }else if(monsterPosition > playerPosition){
            velX = -1;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
        monsterPos = body.getPosition().x;

        double random = Math.random(); //for random jumping
        if(random <= 0.01 && jumpCounter < 2){
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

    public void damage() {
        playerPosition = player.getPosition().x;
        monsterPosition = body.getPosition().x * PPM + 5;

        if (Math.abs(playerPosition - monsterPosition) < 8 && player.holdKnife) {
            monsterHealth.decreaseHP(15);
        }
    }

    public boolean monsterIsDead() {
        return monsterHealth.getHP() <= 0;
    }

}
