package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyHandler {
    private final Player player;
    public KeyHandler(Player player){
        this.player = player;
    }
    
    public void checkUserInput() {
        player.velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.move("right");
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move("left");
        } else{
            player.move("normal");
        }
        player.holdKnife = Gdx.input.isKeyPressed(Input.Keys.ENTER);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.jumpCounter < 2){
            player.jump();
        }
        if(player.body.getLinearVelocity().y == 0){
            player.jumpCounter = 0;
        }
        player.body.setLinearVelocity(player.velX * player.speed, player.body.getLinearVelocity().y < 25 ? player.body.getLinearVelocity().y : 25);
    }
}