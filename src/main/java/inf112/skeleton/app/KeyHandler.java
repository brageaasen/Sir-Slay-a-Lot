package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.Entity.GameEntity;
import inf112.skeleton.app.Entity.Player;

/*
 * The KeyHandler class is responsible for handling key inputs for the Player object and updating the game state accordingly.
 */
public class KeyHandler {
    private final Player player;

    /**
     * Constructor method that initializes a new KeyHandler object with a Player object as input parameter.
     * @param player The player object to which the key inputs will be applied.
     */
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

    /**
     * Method that checks the user inputs and updates the player object's state accordingly.
     */
    public void checkUserInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.move(GameEntity.Direction.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move(GameEntity.Direction.LEFT);
        } else{
            player.move(GameEntity.Direction.NONE);
        }

        this.isHoldingGun();
        this.isHoldingKnife();

        

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

    /**
     * Method that checks if the player is holding the knife and updates the knifeObj object's state accordingly.
     */
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

    /**
     * Method that checks if the player is holding the gun and updates the gun object's state accordingly.
     */
    private void isHoldingGun() {
        int priceGun = 1; //Number of kills you need before unlocking the gun
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            if(player.killCount >= priceGun){
                startWithKnife = false;
                player.knifeObj.setHoldKnife(false);
                keyAlreadyPressed = false;
                if (!gunKeyAlreadyPressed) {
                    player.gun.setHoldGun(true);
                    gunKeyAlreadyPressed = true;   
                }
            } else{
                System.out.println("You need "+(priceGun-player.killCount)+" more kill(s) before unlocking this");
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