package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Entity.Enemy;


public class GameScreen extends ScreenAdapter{

    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final ParallaxLayer[] layers;
    private final World world;
    private final Viewport viewport;

    private final OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private final TileMapHelper tileMapHelper;
    
    private Player player;
    private HealthBar healthBar;
    private ShapeRenderer shapeRenderer;
    private Timer regenTimer;
    private Timer spawnTimer;
    private Set<Enemy> enemies;
    private Inventory inventory;
    // private Box2DDebugRenderer box2dDebugRenderer;

    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25f),false);

        // this.box2dDebugRenderer = new Box2DDebugRenderer();        

        this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        
        layers = new ParallaxLayer[6];
		layers[0] = new ParallaxLayer(new Texture("assets/Background/6.png"), 0.1f, true, false);
		layers[1] = new ParallaxLayer(new Texture("assets/Background/5.png"), 0.2f, true, false);
		layers[2] = new ParallaxLayer(new Texture("assets/Background/4.png"), 0.3f, true, false);
		layers[3] = new ParallaxLayer(new Texture("assets/Background/3.png"), 0.5f, true, false);
		layers[4] = new ParallaxLayer(new Texture("assets/Background/2.png"), 0.8f, true, false);
		layers[5] = new ParallaxLayer(new Texture("assets/Background/1.png"), 1.0f, true, false);

        enemies = new HashSet<>();

        this.tileMapHelper = new TileMapHelper(this);
        
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        
		for (ParallaxLayer layer : layers) {
			layer.setCamera(camera);
		}

        shapeRenderer = new ShapeRenderer();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float barWidth = (float) (screenWidth * 0.5);
        float barHeight = (float) (screenHeight * 0.025);
        healthBar = new HealthBar(player.getPlayerHealth(), barWidth, barHeight, screenWidth, screenHeight);
        this.inventory = new Inventory(player, player.getGun());

        regenTimer = new Timer();
        regenTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                healthBar.renderRegen(shapeRenderer);
            }
            
        }, 3, 3);

        spawnTimer = new Timer();
        spawnTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                tileMapHelper.updateMapObjects();
            }
        }, 5, 5);

            
    }   
    

    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            if (!e.enemyIsDead())
                e.update();
            else{
                enemiesToRemove.add(e);
            }
        }
        enemies.removeAll(enemiesToRemove);
        
        for (Enemy e : enemiesToRemove) {
            world.destroyBody(e.getBody());
        }

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
        
        
        tileMapHelper.movePlatform(delta);   
        
        
        batch.begin(); // Render Parallax background
		for (ParallaxLayer layer : layers)
        {
            if (layer.equals(layers[0]))
            {
                layer.renderInfiniteY(batch);
            }
			layer.render(batch);
		}
        batch.end();

        healthBar.render(shapeRenderer);
        inventory.render(shapeRenderer, batch);
        

        batch.begin();
        tileMapHelper.render(batch);
        
        orthogonalTiledMapRenderer.render();
        
        player.render(batch);

        for (Enemy e : enemies) {
            if (!e.enemyIsDead())
                e.render(batch);
        }
        batch.end();
        healthBar.render(shapeRenderer);
        // box2dDebugRenderer.render(world,camera.combined.scl(PPM));
    }

    public World getWorld(){
        return world;
    }
 
    public void setPlayer(Player player){
        this.player = player;
        
    }

    public Player getPlayer(){
        return player;
    }

    public void setEnemies(Enemy enemy) {
        enemies.add(enemy);
    }


    public Set<Enemy> getEnemies(){
        Set<Enemy> enemiesCopy = new HashSet<Enemy>();
        enemiesCopy.addAll(enemies);
        return enemiesCopy;
    }
    

    @Override 
    public void resize(int width, int height){
        viewport.update(width, height);
    }

    @Override
	public void dispose () {
		batch.dispose();
	}

}