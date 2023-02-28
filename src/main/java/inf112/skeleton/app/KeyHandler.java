package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
public class KeyHandler {
    Player player;
    public KeyHandler(Player player){
        this.player = player;
        
    }
    
    public void checkUserInput(){
        player.velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            spriteChecker();
            player.direction = "right";
            player.velX = 1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            spriteChecker();
            player.direction = "left";
            player.velX = -1;
        }
        else{
            player.direction = "normal";
        }
        player.holdKnife = Gdx.input.isKeyPressed(Input.Keys.ENTER);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.jumpCounter < 2){
            jump();
        }
        if(player.body.getLinearVelocity().y == 0){
            player.jumpCounter = 0;
        }
        player.body.setLinearVelocity(player.velX * player.speed, player.body.getLinearVelocity().y < 25 ? player.body.getLinearVelocity().y : 25);
    }
    private void jump(){
        float force = player.body.getMass() * 18 * 2;
        player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
            player.body.applyLinearImpulse(new Vector2(0,force), player.body.getPosition(), true);
            player.jumpCounter++;
    }
    private void spriteChecker(){
        player.spriteCounter++;
            if (player.spriteCounter>20){
                if(player.spriteNum == 1){
                    player.spriteNum = 2;
                }
                else if (player.spriteNum == 2){
                    player.spriteNum = 1;
                }
                player.spriteCounter =0;
            }
    }
}