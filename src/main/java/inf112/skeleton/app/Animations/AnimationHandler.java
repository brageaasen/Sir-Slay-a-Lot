package inf112.skeleton.app.Animations;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Class for handling animations.
 */
public class AnimationHandler<S> {
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

    /**
     * Set the current "state" of the animation.
     * @param s the new state.
     */
    public void setState(S s) {
        if (animations.get(s) == null)
            throw new IllegalArgumentException("No animation corresponding to the state: " + s);

        if (currState != s) {
            currState = s;
            reset();
        }
    }

    /**
     * Get the current "state" of the animation.
     * @return the current state.
     */
    public S getState() {
        return currState;
    }

    /**
     * Get the currently playing animation.
     * @return the current animation.
     */
    public Animation getCurrAnim() {
        return animations.get(currState);
    }

    /**
     * Get the texture corresponding to the current frame in the animation.
     * @return the current texture of the playing animation.
     */
    public Texture getAnimTexture() {
        return getCurrAnim().getFrame(currFrame);
    }

    /**
     * Add an animation to the animation handler.
     * The animation will be associated to the state `s`.
     *
     * @param s the state to associate the animation with.
     * @param anim the animation to add.
     */
    public void addAnimation(S s, Animation anim) {
        animations.put(s, anim);
    }

    /**
     * Add an animation to the animation handler.
     * The animation will be associated to the state `s`.
     *
     * @param s the state to associate the animation with.
     * @param fileName the filename of the animation to add.
     * @param frames the amount of frames of the animation to add.
     */
    public void addAnimation(S s, String fileName, int frames) {
        animations.put(s, new Animation(fileName, frames));
    }

    public int getCurrFrame() {
        return currFrame;
    }

    /**
     * Update the animation
     *
     * @param n The frame rate of the animation to use.
     */
    public void update(int n) {
        animTimer++;
        if (animTimer > n) {
            animTimer = 0;
            currFrame++;
        }
        if (currFrame > getCurrAnim().frames)
            currFrame = 1;  // One indexed
    }

    /**
     * Reset the animation.
     * The current frame will be reset to 1.
     */
    public void reset() {
        animTimer = 0;
        currFrame = 1;
    }
}
