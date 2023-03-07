package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.controller.PlayerController;
import inf112.skeleton.app.model.GameEntity;
import inf112.skeleton.app.model.Health;
import inf112.skeleton.app.model.PlayerModel;

public class PlayerView extends GameEntity {

    private int jumpCounter;
    private static final int PPM = 16;
    private final Sprite sprite;
    private final Sprite knife;
    private boolean holdKnife = false;
    private Health playerHP;
    private boolean isOnSurface;
    private PlayerModel playerModel;
    private PlayerController playerController;

    public PlayerView(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;
        this.jumpCounter = 0;
        
        playerHP = new Health();
        playerModel = new PlayerModel(body);
        playerController = new PlayerController();

        this.sprite = new Sprite(new Texture("assets/hero.png"));
        this.knife = new Sprite(new Texture("assets/hero.png"));    // temp
        isOnSurface = true;
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        playerController.checkUserInput(velX, holdKnife, body, jumpCounter, speed);
        playerModel.checkFallDamage();
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


    /**
     * Getter method for using the instantiated Health object
     * @return the Health object
     */
    public Health getPlayerHealth() {
        return playerModel.getPlayerHealth();
    }

    /**
     * Checks if the player is dead by checking HP
     * @return true if the player is dead, false otherwise
     */
    public boolean isDead() {
        return playerModel.isDead();
    }

    /**
     * Checks if a player is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return playerModel.isGrounded();
    }
    
}
