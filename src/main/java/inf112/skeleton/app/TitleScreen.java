package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TitleScreen extends ScreenAdapter {

    GameScreenLauncher game;

    public TitleScreen(GameScreenLauncher game) {
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            private OrthographicCamera ortographicCamera;

            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.R) {
                    this.ortographicCamera = new OrthographicCamera();
                    this.ortographicCamera.setToOrtho(false,1280,640);
                    game.setScreen(new GameScreen(ortographicCamera));
                }
                return true;
            }
        });
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, .0f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "Title Screen!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Press R to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}