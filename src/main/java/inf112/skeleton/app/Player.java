package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends GameEntity{
    private static final int PPM = 16; //?? what does this mean???
    public Sprite sprite;   //?? Make private final and change texture?
    private final Sprite knife;
    public boolean holdKnife;   //?? Set to private, change using API (e.g. 'slashKnife')
    public int jumpCounter;     //?? Set to private, change using API (e.g. 'Jump')
    public int spriteCounter;   //?? Set to private, change using API (e.g. 'updateSprite')
    public int spriteNum;       //?? Set to private, change using API (e.g. 'updateSprite')
    KeyHandler keyH;    //?? Should this _really_ be package private?
    public String direction; //?? Replace with 'player state' enum? also change access using API
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;   //?? Introduce constant?
        this.holdKnife = false;
        
        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.jumpCounter = 0;
        this.direction = "normal";
        this.sprite = new Sprite(new Texture("assets/boy_down_1.png")); //?? Should we preload all textures?
        this.knife = new Sprite(new Texture("assets/knife.png"));    //?? replace with new texture
    }
    @Override
    public void update() {
        spriteChecker();
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        getPlayerSprite();
        this.keyH = new KeyHandler(this);
        keyH.checkUserInput();
    }
    @Override
    public void render(SpriteBatch batch) {
        float dx = x - width / 2;
        float dy = y - height / 2;

        sprite.setPosition(dx,dy);
        sprite.draw(batch);

        if (holdKnife) {
            knife.setPosition(dx + (direction.equals("left") ? -width : width),dy);
            knife.draw(batch);
        }
    }
    public void getPlayerSprite(){  //?? Rename to `update` or `set` since nothing is returned?
        //?? Would it be better to associate the texture with the enum? (assuming it will be implemented that way)
        if (direction.equals("normal")) {
            sprite = new Sprite(new Texture("assets/boy_down_1.png"));
        } else {
            //?? Do we really need one for both left and right? Note that it _is_ possible to flip the sprite.
            sprite = new Sprite(new Texture("assets/boy_%s_%d.png".formatted(direction, spriteNum)));
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
    
}