package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBar {
    private final float x, y, width, height;
    private final Health health;
    private float healthPercentage;

    public HealthBar(Health health, float width, float height, float screenWidth, float screenHeight) {
        this.health = health;
        this.x = (screenWidth - width) / 2;
        this.y = screenHeight - height - 10;
        this.width = width;
        this.height = height;
        this.healthPercentage = health.getHP() / 100f;
    }

    public void updateHealth() {
        this.healthPercentage = health.getHP() / 100f;
    }

    public void updateHealthRegen() {
        health.regenHealth();
        this.healthPercentage = health.getHP() / 100f;
    }


    public void render(ShapeRenderer shapeRenderer) {
        this.updateHealth();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, width * healthPercentage, height);
        shapeRenderer.end();
    }


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

