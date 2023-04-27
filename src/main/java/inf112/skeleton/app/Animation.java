package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents an Animation with individual textures for each frame.
 */
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

    /**
     * Get the frame corresponding to index `i`
     * @param i the index of the frame.
     * @return the frame corresponding to `i` as a `Texture`.
     */
    public Texture getFrame(int i) {
        assert(i >= 0);
        assert(i < frames);

        return textures[i-1];   // one-indexed
    }
}
