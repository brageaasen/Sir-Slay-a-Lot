package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreenLauncher extends Game{

    public static GameScreenLauncher instance;

    public GameScreenLauncher(){
        instance = this;
    }

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    
    @Override
    public void create() {
        AudioManager audioManager = new AudioManager();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new TitleScreen(instance));
        //System.out.println(this.audioManager);
        audioManager.Play("MainSong");
    }
    
    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
    
}
