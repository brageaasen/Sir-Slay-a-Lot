package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;


import inf112.skeleton.app.Entity.Player;

public class Inventory {

    private Sprite gunSprite;
    private Sprite knife;
    private Gun gun;
    private BitmapFont font;

    private final Player player;

    public Inventory(Player player, Gun gun) {
        this.gunSprite = new Sprite(new Texture("assets/gun.png"));
        this.knife = new Sprite(new Texture("assets/knife.png"));
        this.gun = gun;
        this.player = player;
        this.font = new BitmapFont();
    }

    public void render(SpriteBatch batch) {
        gunSprite.setPosition(1000, 600);
        gunSprite.draw(batch);

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
        font.setColor(0,0,0,1);
        font.getData().setScale(0.8f);
        batch.setProjectionMatrix(fixedMatrix);
        batch.begin();
        batch.draw(gunSprite, 164,585,50,50);
        font.draw(batch, gun.bulletsInChamber().toString(),183,593);
        batch.draw(knife, 84, 585, 45, 45);
        batch.end();
    }
}