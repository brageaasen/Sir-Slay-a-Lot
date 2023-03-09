package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Player;

public class KeyHandler {
    private final Player player;
    public KeyHandler(Player player){
        this.player = player;
    }
    
    public void checkUserInput() {
        long startTime = System.currentTimeMillis();
        long endTime;
        long elapsedTime;
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
        }

        if(player.getBody().getLinearVelocity().y == 0){
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println(elapsedTime);
            if(elapsedTime >= 1000){
                player.jumpCounter = 0;
            }
        }

        player.getBody().setLinearVelocity(player.getVelocity().x * player.getSpeed(), player.getBody().getLinearVelocity().y < 25 ? player.getBody().getLinearVelocity().y : 25);
    }
}