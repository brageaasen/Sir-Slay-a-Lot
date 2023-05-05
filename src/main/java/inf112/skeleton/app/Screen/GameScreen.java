package inf112.skeleton.app.Screen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Controller.KeyHandler;
import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Entity.PowerUp;
import inf112.skeleton.app.Back_end.*;
import inf112.skeleton.app.Entity.Enemy;
import inf112.skeleton.app.Entity.Inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;

/*
 * The code defines a GameScreen class that extends ScreenAdapter and contains the main logic for the game.
 */
public class GameScreen extends ScreenAdapter {
    private static final float TEXT_TIME = 2f;

    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final ParallaxLayer[] layers;
    private final World world;
    private final Viewport viewport;
    private final GameScreenLauncher game;
    private final KeyHandler keyH;

    private final OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private final TileMapHelper tileMapHelper;
    
    private Player player;
    private final HealthBar healthBar;
    private final ShapeRenderer shapeRenderer;
    private final Timer regenTimer;
    private final Timer spawnTimer;
    private final Set<Enemy> enemies;
    private final Inventory inventory;
    private final PowerUp moreAmmo;
    private final PowerUp moreHealth;
    private float timeElapsed;

    /**
     * This is the constructor of the GameScreen class.
     * @param camera an instance of OrthographicCamera class used to define the game camera.
     */
    public GameScreen(OrthographicCamera camera, GameScreenLauncher game){
        this.game = game;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25f),false);
        this.keyH = new KeyHandler(null);   // will be set to `player` when using `setPlayer()`

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
        this.moreAmmo = new PowerUp(player, new Vector2(1120,480), new Sprite(new Texture("assets/Player/Weapons/ammoCrates.png")), 1);
        this.moreHealth = new PowerUp(player, new Vector2(1710,360), new Sprite(new Texture("assets/Player/Weapons/health.png")), 2);


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
            
        }, 5, 3);

        spawnTimer = new Timer();
        spawnTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                spawnEnemy();
            }
        }, 5, 5);

    }   
    
    public void spawnEnemy(){
        tileMapHelper.updateMapObjects();
    }

    /**
     * This method is used to update the game objects, such as the physics world, camera, player, enemies, and the tile map. 
     * It also checks for user input to quit the game.
     */
    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        orthogonalTiledMapRenderer.setView(camera);
        keyH.checkUserInput();
        player.update();
        
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            if (!e.enemyIsDead())
                e.update();
            else{
                enemiesToRemove.add(e);
            }
        }
        enemiesToRemove.forEach(enemies::remove);
        
        for (Enemy e : enemiesToRemove) {
            world.destroyBody(e.getBody());
        }

        if (player.isDead()){
            game.setScreen(new EndScreen(game, player.getKillCount()));
        }

       
    }


    /**
     * This method updates the position of the camera to follow the player. 
     * The camera follows the player on both x and y-axis if the player is above a certain y coordinate.
     * Otherwise, the camera follows only the player on the x-axis.
     */
    private void cameraUpdate()
    {
        // Camera follow player on y-axis based on current y, e.g: Camera doesn't follow current y-axis when player at bottom of map

        if (player.getBody().getPosition().y * Player.PPM > 400) // Camera follow player in both x and y-axis
        {
            camera.position.set(player.getBody().getPosition().x * Player.PPM + 5, player.getBody().getPosition().y * Player.PPM - 40, 0);
        }
        
        else // Camera follow player in x-axis
        {
            camera.position.set(player.getBody().getPosition().x * Player.PPM + 5, camera.viewportHeight/2, 0);
        }
        camera.update();
    }

    /**
     * This method is the main rendering method that gets called repeatedly by the game loop. 
     * It updates the game objects and renders them on the screen.
     * @param delta the time in seconds between the current and previous frame.
     */
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
        

        batch.begin();
        tileMapHelper.render(batch);
        moreAmmo.render(batch);
        moreHealth.render(batch);
        orthogonalTiledMapRenderer.render();
        
        player.render(batch);

        if (this.player.getGun().getUnlocked()){
            timeElapsed += delta;
            if (timeElapsed <= TEXT_TIME) {
                Texture gunUnlocked = new Texture("assets/UI/gunUnlocked.png");
                // Draw gun unlocked
                batch.draw(gunUnlocked, Gdx.graphics.getWidth() / 2f + 760, Gdx.graphics.getHeight() / 2f - 50, 200, 90);
            }
        }
        
        for (Enemy e : enemies) {
            if (!e.enemyIsDead())
                e.render(batch);
        }
        batch.end();
        inventory.render(shapeRenderer, batch);
        healthBar.render(shapeRenderer);
        // box2dDebugRenderer.render(world,camera.combined.scl(16));
    }

    /**
     * This method returns the World object associated with the current instance of the game.
     * @return A World object
     */
    public World getWorld(){
        return world;
    }
 
    /**
     * This method sets the Player object for the current instance of the game.
     * @param player A Player object
     */
    public void setPlayer(Player player){
        this.player = player;
        keyH.setPlayer(this.player);
    }

    /**
     *  This method returns the Player object associated with the current instance of the game.
     * @return A Player object
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * This method adds an Enemy object to the Set of enemies for the current instance of the game.
     * @param enemy An Enemy object
     */
    public void setEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * This method returns a copy of the Set of Enemy objects associated with the current instance of the game. 
     * The copy is made to prevent direct modification of the original Set.
     * @return A Set of Enemy objects
     */
    public Set<Enemy> getEnemies(){
        return new HashSet<>(enemies);
    }
    

    /**
     * This method is called when the screen size is changed,
     * and it updates the viewport to ensure that the game looks good regardless of the screen size.
     * @param width the new screen width
     * @param height the new screen height
     */
    @Override 
    public void resize(int width, int height){
        viewport.update(width, height);
    }

    /**
     * This method is part of the LibGDX application  and is called when the application is about to be closed or destroyed.
     * The method is responsible for freeing up any resources used by the application, such as textures, sounds, and batch.
     */
    @Override
	public void dispose () {
		batch.dispose();
	}

}