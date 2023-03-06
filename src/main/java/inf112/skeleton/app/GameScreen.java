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
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Entity.GameEntity.Direction;

public class GameScreen extends ScreenAdapter{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture img;
    private ParallaxLayer[] layers;
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

        
        this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        
        layers = new ParallaxLayer[6];
        //layers[0] = new ParallaxLayer(new Texture("assets/Background/test.png"), 0.1f, true, false);
		layers[0] = new ParallaxLayer(new Texture("assets/Background/6.png"), 0.1f, true, false);
		layers[1] = new ParallaxLayer(new Texture("assets/Background/5.png"), 0.2f, true, false);
		layers[2] = new ParallaxLayer(new Texture("assets/Background/4.png"), 0.3f, true, false);
		layers[3] = new ParallaxLayer(new Texture("assets/Background/3.png"), 0.5f, true, false);
		layers[4] = new ParallaxLayer(new Texture("assets/Background/2.png"), 0.8f, true, false);
		layers[5] = new ParallaxLayer(new Texture("assets/Background/1.png"), 1.0f, true, false);
        
        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        
		for (ParallaxLayer layer : layers) {
			layer.setCamera(camera);
		}
        
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

    private void cameraUpdate()
    {
        // Camera follow player on y axis based on current y, e.g: Camera doesn't follow current y axis when player at bottom of map

        if (player.getBody().getPosition().y * player.getPPM() > 400) // Camera follow player in both x and y axis
        {
            camera.position.set(player.getBody().getPosition().x * player.getPPM() + 5, player.getBody().getPosition().y * player.getPPM() - 40, 0);
        }
        
        else // Camera follow player in x axis
        {
            camera.position.set(player.getBody().getPosition().x * player.getPPM() + 5, camera.viewportHeight/2, 0);
        }
        camera.update();
    }

    @Override 
    public void render(float delta){
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
		batch.setProjectionMatrix(camera.combined);
        
        batch.begin(); // Render Parralax background
		for (ParallaxLayer layer : layers)
        {
            if (layer.equals(layers[0]))
            {
                layer.renderInfiniteY(batch);
            }
			layer.render(batch);
		}
        batch.end();

        batch.begin();
        orthogonalTiledMapRenderer.render();
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

    @Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}