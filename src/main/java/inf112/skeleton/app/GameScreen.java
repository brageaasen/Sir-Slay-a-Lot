package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Entity.Player;

public class GameScreen extends ScreenAdapter{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture img;
    private World world;
    private Box2DDebugRenderer box2dDebugRenderer;
    private Viewport viewport;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    private Player player;

    private static final float PPM = 16.0f;

    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25f),false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        

        this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        
    }   
    

    private void update() {

        world.step(1/60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }


    }

    private void cameraUpdate() {
        //  Vector3 position = camera.position;
        // position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        // position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(new Vector2(camera.viewportWidth/2,camera.viewportHeight/2),100);
        
        camera.update();
    }

    @Override 
    public void render(float delta){
        this.update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();
        batch.begin();
        player.render(batch);
        batch.end();
        box2dDebugRenderer.render(world,camera.combined.scl(PPM));

    }

    public World getWorld(){
        return this.world;
    }
 
    public void setPlayer(Player player){
        this.player = player;
        
    }

    @Override 
    public void resize(int width, int height){
        viewport.update(width, height);
    }

}