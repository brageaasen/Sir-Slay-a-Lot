package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.model.GameEntity;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.view.PlayerView;

public class PlayerController {
    
    private PlayerModel playerModel;
    private PlayerView playerView;

    public PlayerController(PlayerModel playerModel, PlayerView playerView) {
        this.playerModel = playerModel;
        this.playerView = playerView;
    }

    public void update() {
        this.checkUserInput();
    }

    public void checkUserInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerModel.move(GameEntity.Direction.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerModel.move(GameEntity.Direction.LEFT);
        } else{
            playerModel.move(GameEntity.Direction.NONE);
        }
        playerModel.setKnife(Gdx.input.isKeyPressed(Input.Keys.ENTER));
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && playerModel.getJump() < 2){
            playerModel.jump();
        }
        
        if(playerModel.getBody().getLinearVelocity().y == 0){
            playerModel.setJump(0);
        }
        playerModel.getBody().setLinearVelocity(playerModel.getVelocity().x * playerModel.getSpeed(), playerModel.getBody().getLinearVelocity().y < 25 ? playerModel.getBody().getLinearVelocity().y : 25);
    }

    public PlayerModel getModel() {
        return playerModel;
    }
}


