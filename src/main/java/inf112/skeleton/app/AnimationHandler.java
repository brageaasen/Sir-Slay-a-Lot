package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public class AnimationHandler<S> {
    // TODO: add documentation
    private final HashMap<S, Animation> animations;
    private S currState;
    private int currFrame = 1;
    private int animTimer = 0;

    public AnimationHandler(S initialState, String fileName, int frames) {
        this(initialState, new Animation(fileName, frames));
    }

    public AnimationHandler(S initialState, Animation initialAnimation) {
        animations = new HashMap<>();
        animations.put(initialState, initialAnimation);
        currState = initialState;
    }

    public void setState(S s) {
        currState = s;
    }

    public S getState() {
        return currState;
    }

    public Animation getCurrAnim() {
        return animations.get(currState);
    }

    public Texture getAnimTexture() {
        return getCurrAnim().getFrame(currFrame);
    }

    public void addAnimation(S s, Animation anim) {
        animations.put(s, anim);
    }

    public void addAnimation(S s, String fileName, int frames) {
        animations.put(s, new Animation(fileName, frames));
    }

    public int getCurrFrame() {
        return currFrame;
    }

    // TODO: make `n` a parameter of the animation itself.
    public void update(int n) {
        animTimer++;
        if (animTimer > n) {
            animTimer = 0;
            currFrame++;
        }
        if (currFrame > getCurrAnim().frames)
            currFrame = 1;  // One indexed
    }

    public void reset() {
        animTimer = 0;
        currFrame = 1;
    }

    public void updateSprite(Sprite sprite, boolean flipped) {
        var t = getAnimTexture();
        sprite.setTexture(t);  // Should hopefully be optimized away if it's the same object (since we do sprite.texture = sprite.texture)
        if ((!flipped && !sprite.isFlipX()) || (flipped && sprite.isFlipX()))
            sprite.flip(true, false);
    }
}
