package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
public class Player extends GameEntity{
    private static final int PPM = 16;
    public Sprite sprite;
    private final Sprite knife;
    public boolean holdKnife;
    public int jumpCounter;
    public int spriteCounter;
    public int spriteNum;
    KeyHandler keyH;
    public String direction;
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;
        this.holdKnife = false;
        
        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.jumpCounter=0;
        this.direction = "normal";
        this.sprite = new Sprite(new Texture("assets/boy_down_1.png"));
        this.knife = new Sprite(new Texture("assets/knife.png"));    // temp
    }
    @Override
    public void update() {
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
            knife.setPosition(dx + (sprite.isFlipX() ? -width : width),dy);
            knife.draw(batch);
        }
    }
    
    public void getPlayerSprite(){
        switch(direction){
            case "right":
                if (spriteNum == 1){
                    sprite = new Sprite(new Texture("assets/boy_right_2.png"));
                }
                if (spriteNum == 2){
                    sprite = new Sprite(new Texture("assets/boy_right_1.png"));
                }
                break;
            case "left":
                if (spriteNum == 1){
                    sprite = new Sprite(new Texture("assets/boy_left_1.png"));
                }
                if (spriteNum == 2){
                    sprite = new Sprite(new Texture("assets/boy_left_2.png"));
                }
                break;
            case "normal":
                sprite = new Sprite(new Texture("assets/boy_down_1.png"));
                break;
        
        }
    }

    private void flip() { // TODO
        sprite.flip(true, false);
        knife.flip(true, false);
    }
    
}