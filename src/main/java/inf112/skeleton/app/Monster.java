package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.Player;


public class Monster extends GameEntity{

    private int jumpCounter;
    private final Sprite sprite;
    private static final int PPM = 16;

    private long start = 0;
    float playerPosition;

    public Monster(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 7f;
        this.jumpCounter = 0;

        this.sprite = new Sprite(new Texture("assets/hero.png"));
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        updatePosition();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);
    }
    
    private void updatePosition(){
        velX = 0;
        playerPosition = Player.playerPos;

        if(body.getPosition().x < playerPosition){
            velX = 1;
        }else if(body.getPosition().x > playerPosition){
            velX = -1;
        }

        
        long random = Math.round(Math.random());
        if(random == 1 && jumpCounter < 2){
            start = System.currentTimeMillis();
            float force = body.getMass() * 18 * 2;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0,force), body.getPosition(), true);
            jumpCounter++;
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        if(body.getLinearVelocity().y == 0 && timeElapsed >= 3000){
            jumpCounter = 0;
        }
    
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
    }

}
