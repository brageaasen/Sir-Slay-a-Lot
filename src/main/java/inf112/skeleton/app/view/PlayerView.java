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
import inf112.skeleton.app.model.GameEntity.Direction;

public class PlayerView {

    private static final int PPM = 16;
    private final Sprite sprite;
    private final Sprite knife;
    private boolean holdKnife = false;

    private int spriteCounter;
    private int spriteNum;
    private PlayerModel playerModel;

    private float width;
    private float height;
    private float x;
    private float y;


    public PlayerView(float width, float height, Body body) {
        // this.speed = 15f;

        playerModel = new PlayerModel(width, height, body);
        //playerController = new PlayerController(playerModel);

        this.sprite = new Sprite(new Texture("assets/hero.png"));
        this.knife = new Sprite(new Texture("assets/hero.png"));    // temp
        
        spriteCounter = 0;
        spriteNum = 1;

        width = playerModel.getWidth();
        height = playerModel.getHeight();
        x = playerModel.getX();
        y = playerModel.getY();
    }

    public void update() {

        x = playerModel.getBody().getPosition().x * PPM;
        y = playerModel.getBody().getPosition().y * PPM;
        
        //playerController.checkUserInput(velX, holdKnife, body, jumpCounter, speed);
        playerModel.update();
    }

    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;
        Direction facing = playerModel.getDir();

        sprite.setPosition(dx,dy);
        sprite.draw(batch);

        if (holdKnife) {
            knife.setPosition(dx + (facing == Direction.LEFT ? -width : width), dy);
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


    private void spriteChecker(){
        spriteCounter++;
        if (spriteCounter > 20){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void flip() {
        sprite.flip(true, false);
        knife.flip(true, false);
    }

    
}
