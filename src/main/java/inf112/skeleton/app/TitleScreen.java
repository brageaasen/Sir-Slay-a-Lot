package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen extends ScreenAdapter {

    // Logo UI
    private static final int LOGO_WIDTH = 200;
    private static final int LOGO_HEIGHT = 200;
    private static final int LOGO_Y = 400;

    // Button UI
    private static final int PLAY_BUTTON_WIDTH = 200;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_Y = 200;
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

    public TitleScreen(GameScreenLauncher game) {
        this.game = game;
        background = new Texture("assets/UI/background.png");
        logo = new Texture("assets/UI/logo.png");
        playButtonActive = new Texture("assets/UI/playButtonActive.png");
        playButtonInactive = new Texture("assets/UI/playButtonInactive.png");
        exitButtonActive = new Texture("assets/UI/exitButtonActive.png");
        exitButtonInactive = new Texture("assets/UI/exitButtonInactive.png");
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, .0f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        // Draw background
        game.batch.draw(background, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // Draw logo
        int x = VIEWPORT_WIDTH / 2 - LOGO_WIDTH / 2;
        game.batch.draw(logo, x, LOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);


        // Draw play button
        x = VIEWPORT_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)
        {
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched())
            {
                this.audioManager.Play("Select");
                this.dispose();
                this.ortographicCamera = new OrthographicCamera();
                this.ortographicCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                game.setScreen(new GameScreen(ortographicCamera));
            }
        }
        else
        {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
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

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}