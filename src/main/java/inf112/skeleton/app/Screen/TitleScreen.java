package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.app.Back_end.AudioManager;

/*
 * This is a class called TitleScreen that extends ScreenAdapter from the LibGDX library. 
 * It is used to create the title screen of a game.
 */
public class TitleScreen extends ScreenAdapter {

    private boolean inControlsMenu;
    private boolean canClickExit = true;
    private int x;

    // Timer for preventing instaclick button overlapping eachother
    private Timer timer;

    // Logo UI
    private static final int LOGO_WIDTH = 200;
    private static final int LOGO_HEIGHT = 200;
    private static final int LOGO_Y = 405;
    
    private static final int LOGO_TEXT_WIDTH = 150;
    private static final int LOGO_TEXT_HEIGHT = 70;
    private static final int LOGO_TEXT_Y = 335;

    // Button UI
    private static final int PLAY_BUTTON_WIDTH = 175;
    private static final int PLAY_BUTTON_HEIGHT = 75;
    private static final int PLAY_BUTTON_Y = 250;

    private static final int EXIT_BUTTON_WIDTH = 175;
    private static final int EXIT_BUTTON_HEIGHT = 75;
    private static final int EXIT_BUTTON_Y = 50;

    private static final int CONTROLS_BUTTON_WIDTH = 200;
    private static final int CONTROLS_BUTTON_HEIGHT = 85;
    private static final int CONTROLS_BUTTON_Y = 147;

    private static final int BACK_BUTTON_WIDTH = 175;
    private static final int BACK_BUTTON_HEIGHT = 75;
    private static final int BACK_BUTTON_Y = 50;

    private static final int CONTROLS_MENU_WIDTH = 425;
    private static final int CONTROLS_MENU_HEIGHT = 477;
    private static final int CONTROLS_MENU_Y = 150;

    private static final int VIEWPORT_WIDTH = 1280;
    private static final int VIEWPORT_HEIGHT = 640;

    private OrthographicCamera ortographicCamera;
    private AudioManager audioManager = new AudioManager();

    GameScreenLauncher game;

    Texture background, logo, logoText, playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive, controlsButtonActive, controlsButtonInactive, backButtonActive, backButtonInactive, controlsMenu;

    /**
     * Constructor that creates a new instance of TitleScreen class
     * @param game an instance of the GameScreenLauncher class
     */
    public TitleScreen(GameScreenLauncher game) {
        this.game = game;
        this.timer = new Timer();
        
        background = new Texture("assets/UI/background.png");
        logo = new Texture("assets/UI/logo.png");
        logoText = new Texture("assets/UI/logoText.png");
        playButtonActive = new Texture("assets/UI/playButtonActive.png");
        playButtonInactive = new Texture("assets/UI/playButtonInactive.png");
        exitButtonActive = new Texture("assets/UI/exitButtonActive.png");
        exitButtonInactive = new Texture("assets/UI/exitButtonInactive.png");
        controlsButtonActive = new Texture("assets/UI/controlsButtonActive.png");
        controlsButtonInactive = new Texture("assets/UI/controlsButtonInactive.png");
        backButtonActive = new Texture("assets/UI/backButtonActive.png");
        backButtonInactive = new Texture("assets/UI/backButtonInactive.png");
        controlsMenu = new Texture("assets/UI/controlsMenu.png");
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

        
        if (!inControlsMenu) // Only draw Play, Controls and Exit button if player is not in controls menu
        {
            // Draw logo
            x = VIEWPORT_WIDTH / 2 - LOGO_WIDTH / 2;
            game.batch.draw(logo, x, LOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);
    
            x = VIEWPORT_WIDTH / 2 - LOGO_TEXT_WIDTH / 2;
            game.batch.draw(logoText, x, LOGO_TEXT_Y, LOGO_TEXT_WIDTH, LOGO_TEXT_HEIGHT);
            
            // Draw play button
            x = VIEWPORT_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
            if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)
            {
                game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                if (Gdx.input.isTouched())
                {
                    this.audioManager.play("Select");
                    this.dispose();
                    this.ortographicCamera = new OrthographicCamera();
                    this.ortographicCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                    game.setScreen(new GameScreen(ortographicCamera, game));
                }
            }
            else
            {
                game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            }

            // Draw controls button
            x = VIEWPORT_WIDTH / 2 - CONTROLS_BUTTON_WIDTH / 2;
            if (Gdx.input.getX() < x + CONTROLS_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < CONTROLS_BUTTON_Y + CONTROLS_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > CONTROLS_BUTTON_Y)
            {
                game.batch.draw(controlsButtonActive, x, CONTROLS_BUTTON_Y, CONTROLS_BUTTON_WIDTH, CONTROLS_BUTTON_HEIGHT);
                if (Gdx.input.isTouched())
                {
                    this.audioManager.play("Select");
                    this.dispose();
                    this.inControlsMenu = true;
                }
            }
            else
            {
                game.batch.draw(controlsButtonInactive, x, CONTROLS_BUTTON_Y, CONTROLS_BUTTON_WIDTH, CONTROLS_BUTTON_HEIGHT);
            }

            // Draw exit button
            x = VIEWPORT_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
            if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y)
            {
                game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
                if (Gdx.input.isTouched() && canClickExit)
                {
                    System.out.println(canClickExit);
                    this.audioManager.play("Select");
                    Gdx.app.exit();
                }
            }
            else
            {
                game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            }
        }
        else
        {
            canClickExit = false;

            // Draw controls menu
            x = VIEWPORT_WIDTH / 2 - CONTROLS_MENU_WIDTH / 2;
            game.batch.draw(controlsMenu, x, CONTROLS_MENU_Y, CONTROLS_MENU_WIDTH, CONTROLS_MENU_HEIGHT);

            // Draw back button
            x = VIEWPORT_WIDTH / 2 - BACK_BUTTON_WIDTH / 2;
            if (Gdx.input.getX() < x + BACK_BUTTON_WIDTH && Gdx.input.getX() > x && VIEWPORT_HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT && VIEWPORT_HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y)
            {
                game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
                if (Gdx.input.isTouched())
                {
                    this.audioManager.play("Select");
                    this.dispose();
                    this.inControlsMenu = false;
                    timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run()
                    {
                        canClickExit = true;
                    }        
                     }, 1);
                }
            }
            else
            {
                game.batch.draw(backButtonInactive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            }
        }
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.R))
        {
            this.dispose();
            this.ortographicCamera = new OrthographicCamera();
            this.ortographicCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
            game.setScreen(new GameScreen(ortographicCamera, game));
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