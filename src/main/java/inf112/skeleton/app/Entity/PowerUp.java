package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PowerUp {
    
    private Sprite Sprite;
    private Player player;
    private float playerPositionX, playerPositionY;
    private Vector2 position;
    private boolean ammoPickedUp;
    private boolean ammoActive;
    private boolean healthPickedUp;
    private boolean healthActive;
    
    private int id;

    public PowerUp(Player player, Vector2 pos, Sprite sprite, int id){
        this.Sprite = sprite;
        this.Sprite.setScale(1.5f);
        this.player = player;
        this.position = pos;
        this.ammoPickedUp = false;
        this.healthPickedUp = false;
        this.ammoActive = false;
        this.healthActive = false;
        this.id = id;
    }

    public void render(SpriteBatch batch){
        if (id == 1){
            if (player.getKillcount() > 0 && player.getKillcount() % 10 == 0){
                this.ammoActive = true;
            }
            if (player.getKillcount() % 9 == 0){
                this.ammoActive = false;
                this.ammoPickedUp = false;
            }
            if (!ammoPickedUp && ammoActive){
                inRange();
                Sprite.setPosition(position.x, position.y);
                Sprite.draw(batch);
            }
        }
        else if (id == 2){
            if (player.getKillcount() > 0 && player.getKillcount() % 15 == 0){
                this.healthActive = true;
            }
            if (player.getKillcount() % 14 == 0){
                this.healthActive = false;
                this.healthPickedUp = false;
            }
            if (!healthPickedUp && healthActive){
                inRange();
                Sprite.setPosition(position.x, position.y);
                Sprite.draw(batch);
            }
        }
    }

    public void inRange() {
        playerPositionX = player.getPosition().x;
        playerPositionY = player.getPosition().y;

        if (Math.abs(playerPositionX - position.x) < player.getKnifeAttackRange() && Math.abs(playerPositionY - position.y) < player.getKnifeAttackRange()+10)
        {
            if (id == 1){
                if (!ammoPickedUp){
                    player.getGun().bulletsPowerUp();
                    ammoPickedUp = true;
                }
            }
            else if (id == 2){
                if (!healthPickedUp){
                    player.getPlayerHealth().fullHealth();
                    healthPickedUp = true;
                }
            }
        }
        
    }

    /**
     * Currently used for testing
     */
    public boolean getAmmoActive() {
        return this.ammoActive;
    }

    /**
     * Currently used for testing
     */
    public void setAmmoActive(boolean ammoActive) {
        this.ammoActive = ammoActive;
    }

        /**
     * Currently used for testing
     */
    public boolean getHealthActive() {
        return this.healthActive;
    }

    /**
     * Currently used for testing
     */
    public void setHealthActive(boolean healthActive) {
        this.healthActive = healthActive;
    }

    /**
     * Currently used for testing
     */
    public void checkForPowerup() {
        if (player.getKillcount() > 0 && player.getKillcount() % 10 == 0){
            this.ammoActive = true;
        }
        if (player.getKillcount() > 0 && player.getKillcount() % 15 == 0){
            this.healthActive = true;
        }
    }

}
