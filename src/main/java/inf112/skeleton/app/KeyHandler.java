package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;

import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Player;

public class KeyHandler {
    private final Player player;
    public KeyHandler(Player player){
        this.player = player;
    }
    
    // Audio
    private AudioManager audioManager = new AudioManager();

    //Time related variables
    long startTime = 0;
    long endTime;
    long elapsedTime;

    public void checkUserInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.move(GameEntity.Direction.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move(GameEntity.Direction.LEFT);
        } else{
            player.move(GameEntity.Direction.NONE);
        }

        player.holdKnife = Gdx.input.isKeyPressed(Input.Keys.ENTER);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.jumpCounter < 2){
            startTime = System.currentTimeMillis();
            player.jump();
            audioManager.Play("Jump");
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        if(player.getBody().getLinearVelocity().y == 0 && elapsedTime >= 250){
            player.jumpCounter = 0;
        }

        player.getBody().setLinearVelocity(player.getVelocity().x * player.getSpeed(), player.getBody().getLinearVelocity().y < 25 ? player.getBody().getLinearVelocity().y : 25);
    }
}