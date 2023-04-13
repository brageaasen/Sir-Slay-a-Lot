package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The GameScreenLauncher class is a subclass of the Game class from the LibGDX game development framework. 
 * This class serves as the entry point for the game and manages the creation and disposal of the game's resources.
 */
public class GameScreenLauncher extends Game{

    public static GameScreenLauncher instance;

    /**
     * Constructor method for the GameScreenLauncher class. 
     * Sets the static instance variable to the current instance of the class.
     */
    public GameScreenLauncher(){
        instance = this;
    }

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;

    /**
     * Method called once when the game is launched. 
     * Initializes the sprite batch, shape renderer, and font, then sets the screen to a new instance of the TitleScreen class.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new TitleScreen(instance));
        
    }
    
    /**
     * Method called when the game is exited. 
     * Disposes of the sprite batch, shape renderer, and font to free up memory.
     */
    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
    
}
