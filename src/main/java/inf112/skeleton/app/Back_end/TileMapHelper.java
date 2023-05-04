package inf112.skeleton.app.Back_end;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;


import inf112.skeleton.app.Entity.Player;
import inf112.skeleton.app.Screen.*;
import inf112.skeleton.app.Entity.Enemy;

/*
 * This class is a helper class for loading and rendering Tiled maps. 
 * It contains methods for setting up a Tiled map, parsing through the map objects, 
 * creating static or moving entities depending on the type of MapObject, and updating the map objects.
 */
public class TileMapHelper {
    
    private TiledMap tiledMap;
    public static GameScreen gameScreen;
    private static final int PPM = 16;

    private Body body2;
    private Vector2 position;

    private final Timer timer;
    private final Sprite movingPlatform;


    /**
     * Constructor method for the TileMapHelper class.
     * It takes in a GameScreen object as a parameter and initializes a Timer object and a moving platform.
     * @param gameScreen the GameScreen object that the TileMapHelper is assisting.
     */
    public TileMapHelper(GameScreen gameScreen){
        TileMapHelper.gameScreen = gameScreen;
        timer = new Timer();
        this.movingPlatform= new Sprite(new Texture("assets/Background/movingPlatform.png"));
    }

    /**
     * Initializes and returns an OrthogonalTiledMapRenderer object with the specified TiledMap.
     * @return OrthogonalTiledMapRenderer object with the specified TiledMap.
     */
    public OrthogonalTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load("assets/Map/map.tmx");
        parseMapObjects(tiledMap.getLayers().get("objects").getObjects());

        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    /**
     * Parses through MapObjects and creates static or moving entities depending on the type of MapObject.
     * @param mapObjects the MapObjects that need to be parsed through.
     */
    private void parseMapObjects(MapObjects mapObjects){
        for(MapObject mapObject : mapObjects){

            if (mapObject instanceof PolygonMapObject){
                String polygonName = mapObject.getName();
                if (polygonName != null && polygonName.equals("moving")){
                    createMovingPlatform((PolygonMapObject) mapObject);
                }
                else {
                    createStaticBody((PolygonMapObject) mapObject);
                }
            }   

            if(mapObject instanceof RectangleMapObject){
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if(rectangleName.equals("player")){
                    Body body = BodyHelper.createEntityBody(
                                rectangle.getX() + rectangle.getWidth()/2, 
                                rectangle.getY() + rectangle.getHeight()/2, 
                                rectangle.getWidth(), rectangle.getHeight(), false, gameScreen.getWorld());

                    gameScreen.setPlayer(new Player(rectangle.getWidth(),rectangle.getHeight(), body));

                }
                if(rectangleName.equals("monster")){
                    Body body = BodyHelper.createEntityBody(
                                rectangle.getX() + rectangle.getWidth()/2, 
                                rectangle.getY() + rectangle.getHeight()/2, 
                                rectangle.getWidth(), rectangle.getHeight(), false, gameScreen.getWorld());

                    

                    gameScreen.setEnemies(new Enemy(rectangle.getWidth(),rectangle.getHeight(), body, gameScreen.getPlayer()));
                }
            }
        }
    }

    /**
     * This method updates the map objects.
     * 
     */
    public void updateMapObjects() {
        MapObjects objects = tiledMap.getLayers().get("objects").getObjects();
        RectangleMapObject newObject = new RectangleMapObject((float) 1000.95, (float) 100, (float) 25.4954, (float) 52.1497);
        Rectangle newRect = newObject.getRectangle();
        Body newBody = BodyHelper.createEntityBody(
                    newRect.getX() + newRect.getWidth()/2, 
                    newRect.getY() + newRect.getHeight()/2, 
                    newRect.getWidth(), newRect.getHeight(), false, gameScreen.getWorld());

        objects.add(newObject);
        gameScreen.setEnemies(new Enemy(newRect.getWidth(),newRect.getHeight(), newBody, gameScreen.getPlayer()));
    }

    /**
     * This method creates a static Body for the given PolygonMapObject.
     * @param polygonMapObject The PolygonMapObject for which to create the Body.
     */
    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape,1000);
        shape.dispose();
    }

    /**
     * This method creates a PolygonShape for the given PolygonMapObject.
     * @param polygonMapObject The PolygonMapObject for which to create the PolygonShape.
     * @return A new PolygonShape for the given PolygonMapObject.
     */
    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for(int i = 0; i < vertices.length / 2; i++){
            Vector2 current = new Vector2(vertices[i*2] / PPM, vertices[i*2+1] / PPM);
            worldVertices[i] = current;

        }

        PolygonShape shape = new PolygonShape();

        shape.set(worldVertices);

        return shape;
    }
    
    /**
     * This method creates a kinematic Body for the given PolygonMapObject
     * @param polygonMapObject The PolygonMapObject for which to create the moving platform.
     */
    public void createMovingPlatform(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        body2 = gameScreen.getWorld().createBody(bodyDef);
        body2.setTransform(new Vector2(0,-10), 0);
        Shape shape = createPolygonShape(polygonMapObject);
        body2.createFixture(shape,1000);
        shape.dispose();
        
    }

    /**
     * This method moves the platform up and down based on a timer.
     * It checks the current position of the platform and sets the direction based on whether the platform is at the top or bottom of its range. 
     * It then schedules a task with a timer to move the platform up or down depending on the direction.
     * @param delta The time in seconds since the last frame.
     */
    public void movePlatform(float delta){
        String direction = "up";
        position = body2.getPosition();

        if (position.y <= -10 ){
            direction = "up";
        }
        if (position.y >= 5){
            direction = "down";
        }
        if (direction.equals("down")){
            timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                    float newY = position.y - 3f * delta; // adjust the speed as needed
                    body2.setTransform(position.x, newY, 0);
                    
                }
            }, 4);
        }

        if (direction.equals("up")){  
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    float newY = position.y + 3f * delta; // adjust the speed as needed
                    body2.setTransform(position.x, newY, 0);
                }               
             }, 4);
        }
    }
    
    /**
     * his method renders the moving platform. 
     * It calculates the position of the platform in pixels, sets the position of the movingPlatform sprite, 
     * and draws the sprite using the given SpriteBatch.
     * @param batch The SpriteBatch to use for drawing.
     */
    public void render(SpriteBatch batch) {
        float x = body2.getPosition().x * PPM;
        float y = body2.getPosition().y * PPM;

        movingPlatform.setPosition(x + 1472, y + 353); // litt hardkodet
        movingPlatform.draw(batch);
    }
}

