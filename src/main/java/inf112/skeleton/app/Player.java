package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends GameEntity{

    private int jumpCounter;
    private static final int PPM = 16;
    private final Sprite sprite;
    private final Sprite knife;
    private boolean holdKnife = false;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;
        this.jumpCounter = 0;

        this.sprite = new Sprite(new Texture("assets/hero.png"));
        this.knife = new Sprite(new Texture("assets/knife.png"));    // temp
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        checkUserInput();

    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);

        if (holdKnife) {
            knife.setPosition(dx + width,dy);
            knife.draw(batch);
        }
    }

    private void checkUserInput(){
        velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velX = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velX = -1;
        }
        holdKnife = Gdx.input.isKeyPressed(Input.Keys.ENTER);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && jumpCounter < 2){
            float force = body.getMass() * 18 * 2;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0,force), body.getPosition(), true);
            jumpCounter++;

        }

        if(body.getLinearVelocity().y == 0){
            jumpCounter = 0;
        }

        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);

    }
    
}
