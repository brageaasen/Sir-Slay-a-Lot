package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.model.GameEntity;
import inf112.skeleton.app.model.Health;
import inf112.skeleton.app.model.PlayerModel;

public class Player extends GameEntity {

    private int jumpCounter;
    private static final int PPM = 16;
    private final Sprite sprite;
    private final Sprite knife;
    private boolean holdKnife = false;
    private Health playerHP;
    private boolean isOnSurface;
    private PlayerModel playerModel;
    private PlayerController playerController;

    public Player(float width, float height, Body body) {
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
        checkFallDamage();
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
     * Checks if the player has fallen from too high and how much damage is inflicted
     */
    public void checkFallDamage() {
        float verticalSpeed = body.getLinearVelocity().y;

        if (verticalSpeed < -10) {
            int damageScale = (int) ((Math.abs(verticalSpeed) - 10));

            playerHP.decreaseHP(damageScale);
        }
    }

    /**
     * Getter method for using the instantiated Health object
     * @return the Health object
     */
    public Health getPlayerHealth() {
        return playerHP;
    }

    /**
     * Checks if the player is dead by checking HP
     * @return true if the player is dead, false otherwise
     */
    public boolean isDead() {
        return playerHP.getHP() <= 0;
    }

    /**
     * Checks if a player is on the ground/surface and not midair
     * @return true if the player is on a surface, false otherwise
     */
    public boolean isGrounded() {
        return body.getLinearVelocity().y == 0;
    }
    
}
