package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Test "spill" for oblig 1
 */
public class TestGame extends ApplicationAdapter {
    
    ShapeRenderer shape;
    Ball b;
    List<Ball> balls = new ArrayList<>();
    Random r = new Random();

    @Override
    public void create() {
        shape = new ShapeRenderer();
        b = new Ball(50, 50, 100, 5, 5);

        for (int i = 0; i < 15; i++) {
            balls.add(new Ball(r.nextInt(Gdx.graphics.getWidth()),
            r.nextInt(Gdx.graphics.getHeight()),
            r.nextInt(100), r.nextInt(15), r.nextInt(15)));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b.update();
        
        shape.begin(ShapeRenderer.ShapeType.Filled);
        b.update();
        b.draw(shape);     
        shape.end();
    }
}
