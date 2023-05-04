package inf112.skeleton.app.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import inf112.skeleton.app.Entity.Health;

/*
 * class for creating the healthbar that shows the player's health.
 */
public class HealthBar {
    private final float x, y, width, height;
    private final Health health;
    private float healthPercentage;

    /**
     * This constructor creates a new instance of the HealthBar class. 
     * It initializes the x, y, width, and height variables that determine the position and size of the health bar. 
     * It also initializes the healthPercentage variable to the current percentage of health remaining.
     * @param health an instance of the Health class
     * @param width the width of the health bar in pixels
     * @param height the height of the health bar in pixels
     * @param screenWidth the width of the screen in pixels
     * @param screenHeight the height of the screen in pixels
     */
    public HealthBar(Health health, float width, float height, float screenWidth, float screenHeight) {
        this.health = health;
        this.x = (screenWidth - width) / 2;
        this.y = screenHeight - height - 10;
        this.width = width;
        this.height = height;
        this.healthPercentage = health.getHP() / 100f;
    }

    /**
     * This method updates the healthPercentage variable to the current percentage of health remaining.
     */
    public void updateHealth() {
        this.healthPercentage = health.getHP() / 100f;
    }

    /**
     * This method regenerates the player's health and updates the healthPercentage variable to the new percentage of health remaining.
     */
    public void updateHealthRegen() {
        health.regenHealth();
        this.healthPercentage = health.getHP() / 100f;
    }

    /**
     * This method renders the health bar on the screen using the ShapeRenderer. 
     * It calls the updateHealth() method to ensure the health percentage is up-to-date.
     * @param shapeRenderer an instance of the ShapeRenderer class
     */
    public void render(ShapeRenderer shapeRenderer) {
        this.updateHealth();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, width * healthPercentage, height);
        shapeRenderer.end();
    }


    /**
     * This method renders the health bar on the screen with the updated health percentage after regenerating the health.
     * It calls the updateHealthRegen() method to ensure the health percentage is up-to-date after the regeneration.
     * @param shapeRenderer
     */
    public void renderRegen(ShapeRenderer shapeRenderer) {
        this.updateHealthRegen();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, width * healthPercentage, height);
        shapeRenderer.end();
    }
}

