package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.model.PlayerModel;

public class PlayerController {
    
    private PlayerModel playerModel;

    public PlayerController(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public void checkUserInput(float velX, boolean holdKnife, Body body, int jumpCounter, float speed) {
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


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
