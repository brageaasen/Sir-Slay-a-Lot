package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * This class Main is the entry point for the application and it contains only one method main() which launches the game screen.
 */
public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Sir Slay-a-Lot");
        cfg.setWindowedMode(1280, 640);
        cfg.setWindowIcon("assets/UI/logo.png");
        cfg.setForegroundFPS(60);

        new Lwjgl3Application(new GameScreenLauncher(), cfg);
    }
}