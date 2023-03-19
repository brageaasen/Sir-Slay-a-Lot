package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParallaxLayer {
    private final Texture texture;
    private final float factor;
    private final boolean wrapHorizontally;
    private final boolean wrapVertically;
    public Camera camera;   // TODO: Should this be final? (See todo below)

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

    // TODO: Should this be updatable? or moved to the constructor?
    void setCamera(Camera camera) {
        this.camera = camera;
    }

    void render(SpriteBatch batch) {
        int xOffset = (int) (camera.position.x * factor);
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionWidth(wrapHorizontally ? (int) camera.viewportWidth : texture.getWidth());
        region.setRegionHeight(wrapVertically ? (int) camera.viewportHeight : texture.getHeight());
        batch.draw(region, camera.position.x - camera.viewportWidth/2, 360 - camera.viewportHeight/2);
    }

    void renderInfiniteY(SpriteBatch batch) {
        int xOffset = (int) (camera.position.x * factor);
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionWidth(wrapHorizontally ? (int) camera.viewportWidth : texture.getWidth());
        region.setRegionHeight(wrapVertically ? (int) camera.viewportHeight : texture.getHeight());
        batch.draw(region, camera.position.x - camera.viewportWidth/2, camera.position.y - camera.viewportHeight/2);
    }
}
