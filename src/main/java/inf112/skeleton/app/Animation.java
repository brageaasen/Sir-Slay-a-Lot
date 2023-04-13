package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;

public class Animation {
    private final Texture[] textures;
    public final int frames;

    public Animation(String filename, int frames) {
        this.frames = frames;
        textures = new Texture[this.frames];

        for (int i = 0; i < this.frames; i++) {
            textures[i] = new Texture(filename.formatted(i+1));
        }
    }

    public Texture getFrame(int i) {
        assert(i >= 0);
        assert(i < frames);

        return textures[i-1];   // one-indexed
    }
}
