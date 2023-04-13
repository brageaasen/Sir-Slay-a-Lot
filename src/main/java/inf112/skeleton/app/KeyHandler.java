package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Player;

public class KeyHandler {
    private final Player player;
    public KeyHandler(Player player){
        this.player = player;
        this.keyAlreadyPressed = false;
        this.gunKeyAlreadyPressed = false;
        this.startWithKnife = true;
    }
    
    // Audio
    private AudioManager audioManager = new AudioManager();

    //Time related variables
    long startTime = 0;
    long endTime;
    long elapsedTime;
    private boolean keyAlreadyPressed;
    private boolean gunKeyAlreadyPressed;
    private boolean startWithKnife;

    public void checkUserInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.move(GameEntity.Direction.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move(GameEntity.Direction.LEFT);
        } else{
            player.move(GameEntity.Direction.NONE);
        }


        this.isHoldingKnife();

        this.isHoldingGun();

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


    private void isHoldingKnife() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            player.gun.setHoldGun(false);
            keyAlreadyPressed = true;
        } 

        if (startWithKnife && Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            player.knifeObj.setHoldKnife(true);
            player.knifeObj.setDealingDamage(true);
            
        }

        else if (keyAlreadyPressed && Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            player.knifeObj.setHoldKnife(true);
            player.knifeObj.setDealingDamage(true);
        } 
        else {
            player.knifeObj.setHoldKnife(false);
        }
    }

    private void isHoldingGun() {
        int price = 1;
        
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            if(player.killCount >= price){
                startWithKnife = false;
                player.knifeObj.setHoldKnife(false);
                keyAlreadyPressed = false;
                if (!gunKeyAlreadyPressed) {
                    player.gun.setHoldGun(true);
                    gunKeyAlreadyPressed = true;   
                }
            }else if(player.killCount < price && !keyAlreadyPressed){
                System.out.println("You need "+(price-player.killCount)+" more kills to unlock this");
            } 
        }else {
                gunKeyAlreadyPressed = false;
                if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                    player.gun.setFiring(true);;
                }
                else {
                    player.gun.setFiring(false);;
                }
        }
    }
}