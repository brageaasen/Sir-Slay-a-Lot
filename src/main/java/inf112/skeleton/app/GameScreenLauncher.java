package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreenLauncher extends Game{

    public static GameScreenLauncher instance;
    private OrthographicCamera ortographicCamera;

    public GameScreenLauncher(){
        instance = this;
    }

    @Override
    public void create() {
       
        this.ortographicCamera = new OrthographicCamera();
        this.ortographicCamera.setToOrtho(false,1280,640);
        setScreen(new GameScreen(ortographicCamera));
    }
    
}
