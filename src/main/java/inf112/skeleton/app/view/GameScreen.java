package inf112.skeleton.app.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2dDebugRenderer;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    private PlayerView playerView;

    private static final float PPM = 16.0f;
    private HealthBar healthBar;
    private Timer regenTimer;

    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25f),false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        
        this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float barWidth = (float) (screenWidth * 0.5);
        float barHeight = (float) (screenHeight * 0.025);
        healthBar = new HealthBar(playerView.getPlayerHealth(), barWidth, barHeight, screenWidth, screenHeight);

        shapeRenderer = new ShapeRenderer();
        regenTimer = new Timer();
        regenTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                healthBar.renderRegen(shapeRenderer);
            }
            
        }, 3, 3);

        // contactChecker = new ContactChecker(player);
        // world.setContactListener(contactChecker);
    }   
    

    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        playerView.update();
        //endGameCondition();
    }

    private void cameraUpdate() {
        //  Vector3 position = camera.position;
        // position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        // position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(new Vector2(camera.viewportWidth/2,camera.viewportHeight/2),100);
        
        camera.update();
    }

    @Override 
    public void render(float delta) {
        this.update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();
        
        batch.begin();
        playerView.render(batch);
        batch.end();
        
        healthBar.render(shapeRenderer);
        box2dDebugRenderer.render(world,camera.combined.scl(PPM));

    }

    public World getWorld() {
        return this.world;
    }
 
    public void setPlayerView(PlayerView player){
        this.playerView = player;
        
    }

    public void endGameCondition() {
        if (playerView.isDead())
            Gdx.app.exit();
    }

    @Override 
    public void resize(int width, int height){
        viewport.update(width, height);
    }

}