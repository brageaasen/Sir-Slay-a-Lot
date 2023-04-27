package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/*
 * This is a class called EndScreen that extends ScreenAdapter from the LibGDX library. 
 * It is used to create the end screen of a game.
 */
public class EndScreen extends ScreenAdapter {

    // Logo UI
    private static final int LOGO_WIDTH = 200;
    private static final int LOGO_HEIGHT = 200;
    private static final int LOGO_Y = 400;

    // Button UI
    private static final int RESTART_BUTTON_WIDTH = 200;
    private static final int RESTART_BUTTON_HEIGHT = 100;
    private static final int RESTART_BUTTON_Y = 200;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_Y = 50;

    private static final int VIEWPORT_WIDTH = 1280;
    private static final int VIEWPORT_HEIGHT = 640;

    private OrthographicCamera ortographicCamera;
    private AudioManager audioManager = new AudioManager();

    GameScreenLauncher game;

    Texture background;
    Texture logo;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture restartButtonActive;
    Texture restartButtonInactive;

    /**
     * Constructor that creates a new instance of EndScreen class
     * @param game an instance of the GameScreenLauncher class
     */
    public EndScreen(GameScreenLauncher game) {
        this.game = game;
        background = new Texture("assets/UI/background.png");
        logo = new Texture("assets/UI/logo.png");
        playButtonActive = new Texture("assets/UI/playButtonActive.png");
        playButtonInactive = new Texture("assets/UI/playButtonInactive.png");
        exitButtonActive = new Texture("assets/UI/exitButtonActive.png");
        exitButtonInactive = new Texture("assets/UI/exitButtonInactive.png");
        restartButtonActive = new Texture("assets/UI/restartButtonActive.png");
        restartButtonInactive = new Texture("assets/UI/restartButtonInactive.png");
    }

    /**
     * Overrides the render method from the ScreenAdapter class.
     * Draws the UI elements of the title screen
     * @param delta the time in seconds since the last render call
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .0f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        // Draw background
        game.batch.draw(background, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // Draw logo
        int x = VIEWPORT_WIDTH / 2 - LOGO_WIDTH / 2;
        game.batch.draw(logo, x, LOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);


        // Draw restart button
        x = VIEWPORT_WIDTH / 2 - RESTART_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < x + RESTART_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < RESTART_BUTTON_Y + RESTART_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > RESTART_BUTTON_Y)
        {
            game.batch.draw(restartButtonActive, x, RESTART_BUTTON_Y, RESTART_BUTTON_WIDTH, RESTART_BUTTON_HEIGHT);
            if (Gdx.input.isTouched())
            {
                this.audioManager.Play("Select");
                this.dispose();
                this.ortographicCamera = new OrthographicCamera();
                this.ortographicCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                game.setScreen(new GameScreen(ortographicCamera, game));
            }
        }
        else
        {
            game.batch.draw(restartButtonInactive, x, RESTART_BUTTON_Y, RESTART_BUTTON_WIDTH, RESTART_BUTTON_HEIGHT);
        }

        // Draw exit button
        x = VIEWPORT_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y)
        {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched())
            {
                this.audioManager.Play("Select");
                Gdx.app.exit();
            }
        }
        else
        {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        
        game.batch.end();
    }

    /**
     * Overrides the hide method from the ScreenAdapter class.
     * Hides the input processor when the screen is no longer displayed.
     */
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}
