package inf112.skeleton.app;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.Entity.Enemy;

public class EnemyFactory {
    private Timer spawnTimer;
    private int spawnInterval;
    private int numEnemies;
    private Enemy enemyTemplate;
    private SpriteBatch batch;

    public EnemyFactory(int spawnInterval, int numEnemies, Enemy enemyTemplate, SpriteBatch batch) {
        this.spawnInterval = spawnInterval;
        this.numEnemies = numEnemies;
        this.enemyTemplate = enemyTemplate;
        this.batch = batch;
    }

    public void startSpawning() {
        spawnTimer = new Timer();
        spawnTimer.schedule(new SpawnTask(), 0, spawnInterval);
    }

    private class SpawnTask extends TimerTask {
        private int numEnemiesSpawned = 0;

        public void run() {
            if (numEnemiesSpawned >= numEnemies) {
                spawnTimer.cancel();
            } else {
                numEnemiesSpawned++;
                Enemy enemyClone = enemyTemplate.clone();
                enemyClone.render(batch);
            }
        }
    }
}
