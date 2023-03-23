package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;


import inf112.skeleton.app.Entity.Player;

public class Inventory {

    private Sprite gun;
    private Sprite knife;

    private final Player player;

    public Inventory(Player player) {
        this.gun = new Sprite(new Texture("assets/gun.png"));
        this.knife = new Sprite(new Texture("assets/knife.png"));

        this.player = player;
    }

    public void render(SpriteBatch batch) {
        gun.setPosition(1000, 600);
        gun.draw(batch);

        knife.draw(batch);
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (!player.gun.getHoldGun()){
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(75, 575, 60, 60);
            }
        else if (player.gun.getHoldGun()){
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(145, 575, 60, 60);
        }
       
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(150, 580, 50, 50);
    
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(80, 580, 50, 50);
        shapeRenderer.end();

        Matrix4 fixedMatrix = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(fixedMatrix);
        batch.begin();
        batch.draw(gun, 164,585,50,50);
        batch.draw(knife, 84, 585, 45, 45);
        batch.end();
    }
}