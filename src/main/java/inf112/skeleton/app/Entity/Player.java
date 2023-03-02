package inf112.skeleton.app.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.KeyHandler;

public class Player extends GameEntity {
    private static final int PPM = 16; //?? what does this mean???

    public boolean holdKnife;   //?? Set to private, change using API (e.g. 'slashKnife')
    public int jumpCounter;     //?? Set to private, change using API (e.g. 'Jump')
    private int spriteCounter;
    private int spriteNum;
    private Direction direction;
    private final Sprite knife;
    private final KeyHandler keyH;
    private final Sprite sprite;
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;   //?? Introduce constant?
        this.holdKnife = false;
        
        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.jumpCounter = 0;
        this.direction = Direction.NONE;
        this.sprite = new Sprite(new Texture("assets/boy_down_1.png")); //?? Should we preload textures instead of loading them every time? (does it even matter?)
        this.knife = new Sprite(new Texture("assets/knife.png"));
        this.keyH = new KeyHandler(this);   //?? Should the player class hold input handling?
    }
    @Override
    public void update() {
        spriteChecker();
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        updateSprite();
        keyH.checkUserInput();
    }
    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);

        if (holdKnife) {
            knife.setPosition(dx + (direction == Direction.LEFT ? -width : width),dy);
            knife.draw(batch);
        }
    }
    public void updateSprite(){
        //?? Would it be better to associate the texture with the enum? (e.g. "assets/boy_NONE_1.png")
        if (direction == Direction.NONE) {
            sprite.setTexture(new Texture("assets/boy_down_1.png"));
        } else {
            //?? Do we really need one for both left and right? Note that it _is_ possible to flip the sprite.
            String dir = direction == Direction.LEFT ? "left" : "right"; // TODO: create constants? or rename to fit enum names
            sprite.setTexture(new Texture("assets/boy_%s_%d.png".formatted(dir, spriteNum)));
        }
    }

    public void jump(){
        float force = body.getMass() * 18 * 2;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(0,force), body.getPosition(), true);
        jumpCounter++;
    }

    private void spriteChecker(){
        spriteCounter++;
        if (spriteCounter>20){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter =0;
        }
    }

    public void flip() { // TODO?: replace unneeded texture?
        sprite.flip(true, false);
        knife.flip(true, false);
    }

    @Override
    public void move(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case RIGHT -> velX = 1;
            case LEFT-> velX = -1;
            default -> velX = 0;
        }
    }
}