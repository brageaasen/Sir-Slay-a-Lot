package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class AnimationHandler<S> {
    private final HashMap<S, Animation> animations;
    private S currState = null;
    private int currFrame = 1;
    private int animTimer = 0;

    public AnimationHandler() {
        animations = new HashMap<>();
    }

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

    public void update() {
        animTimer++;
        if (animTimer > 10) {
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
}
