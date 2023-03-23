package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    private final Texture texture;
    public final int frames;

    public Animation(String filename, int frames) {
        this.frames = frames;
        texture = new Texture(filename);
    }

    public TextureRegion getFrame(int i) {
        assert(i >= 0);
        assert(i < frames);
        int w = texture.getWidth();
        int h = texture.getHeight();
        int dw = w/frames;
        return new TextureRegion(texture, dw*(i-1), 0, dw, h);
    }
}
