package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.Entity.Player;

public class PowerUp {
    
    private Sprite Sprite;
    private Player player;
    private float playerPositionX, playerPositionY;
    private Vector2 position;
    private boolean ammoPickedUp;
    private boolean healthPickedUp;
    private int id;

    public PowerUp(Player player, Vector2 pos, Sprite sprite, int id){
        this.Sprite = sprite;
        this.Sprite.setScale(1.5f);
        this.player = player;
        this.position = pos;
        this.ammoPickedUp = false;
        this.healthPickedUp = false;
        this.id = id;
    }

    public void render(SpriteBatch batch){
        if (id == 1){
            if (player.getKillcount() > 0 && player.getKillcount() % 10 == 0){
                inRange();
                if (!ammoPickedUp){
                    Sprite.setPosition(position.x, position.y);
                    Sprite.draw(batch);
                }
            }
        }
        else if (id == 2){
            if (player.getKillcount() > 0 && player.getKillcount() % 15 == 0){
                inRange();
                if (!healthPickedUp){
                    Sprite.setPosition(position.x, position.y);
                    Sprite.draw(batch);
                }
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





}
