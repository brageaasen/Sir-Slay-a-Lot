package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * The ParallaxLayer class represents a single layer in a parallax scrolling background. 
 * In a parallax scrolling background, multiple layers of images are moved at different speeds to create an illusion of depth.
 */
public class ParallaxLayer {
    private final Texture texture;
    private final float factor;
    private final boolean wrapHorizontally;
    private final boolean wrapVertically;
    public Camera camera;   // TODO: Should this be final? (See todo below)

    /**
     * Constructor for the ParallaxLayer.
     * @param texture The texture for the parallax layer.
     * @param factor The parallax factor that determines how much the layer moves relative to the camera.
     * @param wrapHorizontally Whether the layer should wrap horizontally when scrolling.
     * @param wrapVertically Whether the layer should wrap vertically when scrolling.
     */
    ParallaxLayer(Texture texture, float factor, boolean wrapHorizontally, boolean wrapVertically) {
        this.texture = texture;
        this.factor = factor;
        this.wrapHorizontally = wrapHorizontally;
        this.wrapVertically = wrapVertically;
        this.texture.setWrap(
            this.wrapHorizontally ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge,
            this.wrapVertically ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
        );
    }

    /**
     *  Sets the camera to use for this parallax layer.
     * @param camera The camera to set for this parallax layer.
     */

    // TODO: Should this be updatable? or moved to the constructor?
    void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Renders the parallax layer using the specified SpriteBatch, 
     * with the camera position and viewport used to calculate the layer's position and size.
     * @param batch The SpriteBatch to use for rendering.
     */
    void render(SpriteBatch batch) {
        int xOffset = (int) (camera.position.x * factor);
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionWidth(wrapHorizontally ? (int) camera.viewportWidth : texture.getWidth());
        region.setRegionHeight(wrapVertically ? (int) camera.viewportHeight : texture.getHeight());
        batch.draw(region, camera.position.x - camera.viewportWidth/2, 360 - camera.viewportHeight/2);
    }

    /**
     * Renders the parallax layer using the specified SpriteBatch, 
     * with the camera position used to calculate the layer's horizontal position and size, and with infinite vertical tiling.
     * @param batch The SpriteBatch to use for rendering.
     */
    void renderInfiniteY(SpriteBatch batch) {
        int xOffset = (int) (camera.position.x * factor);
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionWidth(wrapHorizontally ? (int) camera.viewportWidth : texture.getWidth());
        region.setRegionHeight(wrapVertically ? (int) camera.viewportHeight : texture.getHeight());
        batch.draw(region, camera.position.x - camera.viewportWidth/2, camera.position.y - camera.viewportHeight/2);
    }
}
