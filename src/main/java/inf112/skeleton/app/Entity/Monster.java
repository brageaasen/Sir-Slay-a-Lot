package inf112.skeleton.app.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Monster extends GameEntity{

    private int jumpCounter;
    private final Sprite sprite;
    private static final int PPM = 16;

    private long start = 0;
    private float playerPosition;
    public static float monsterPos;
    private  final Player player;

    public Monster(float width, float height, Body body, Player player) {
        super(width, height, body);
        this.speed = 7f;
        this.jumpCounter = 0;
        this.player = player;

        this.sprite = new Sprite(new Texture("assets/Enemy/Run/Run1.png"));
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        updatePosition();
    }

    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);
    }

    @Override
    public void move(Direction direction) {

    }

    private void updatePosition() {
        velX = 0;
        if (player == null)
            return;
        playerPosition = player.getPosition().x;

        if(body.getPosition().x < playerPosition){
            velX = 1;
        }else if(body.getPosition().x > playerPosition){
            velX = -1;
        }
        

    
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
        monsterPos = body.getPosition().x;
    }

}
