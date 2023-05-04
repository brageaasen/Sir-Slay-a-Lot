package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import inf112.skeleton.app.Animation.Animation;
import inf112.skeleton.app.Animation.AnimationHandler;

public class AnimationTest {

    @Test
    void testAnimation() {
        AnimationHandler<String> anim = new AnimationHandler<>("a", new Animation(3));
        assertEquals("a", anim.getState());
        anim.addAnimation("b", new Animation(5));
        var f = anim.getCurrFrame();
        anim.update(2); // animTimer -> 1
        assertEquals(f, anim.getCurrFrame());
//        assertEquals(t, anim.getAnimTexture());
        anim.update(2); // animTimer -> 2
        anim.update(2); // animTimer -> 3 -> 0, frame -> 1
        assertNotEquals(f, anim.getCurrFrame());
//        assertNotEquals(t, anim.getAnimTexture());
        assertEquals(2, anim.getCurrFrame());
        anim.update(2); // animTimer -> 1
        anim.update(2); // animTimer -> 2
        assertEquals(2, anim.getCurrFrame());
        anim.update(2); // animTimer -> 3 -> 0, frame -> 3
        assertEquals(3, anim.getCurrFrame());
        anim.update(2); // animTimer -> 1
        anim.update(2); // animTimer -> 2
        assertEquals(3, anim.getCurrFrame());
        anim.update(2); // animTimer -> 3 -> 0, frame -> 4 -> 1
        assertEquals(1, anim.getCurrFrame());

        anim.setState("b");
        assertEquals(1, anim.getCurrFrame());
        assertEquals("b", anim.getState());
        anim.update(0); // animTimer -> 1 -> 0, frame -> 2
        assertEquals(2, anim.getCurrFrame());
        anim.update(0); // animTimer -> 1 -> 0, frame -> 3
        assertEquals(3, anim.getCurrFrame());
        anim.update(0); // animTimer -> 1 -> 0, frame -> 4
        anim.update(0); // animTimer -> 1 -> 0, frame -> 5
        assertEquals(5, anim.getCurrFrame());
        anim.update(0); // animTimer -> 1 -> 0, frame -> 6 -> 1
        assertEquals(1, anim.getCurrFrame());
        anim.update(0); // animTimer -> 1 -> 0, frame -> 2
        anim.update(0); // animTimer -> 1 -> 0, frame -> 3
        anim.update(0); // animTimer -> 1 -> 0, frame -> 4
        assertEquals(4, anim.getCurrFrame());
        var a = anim.getCurrAnim();
        anim.setState("a");
        assertEquals(1, anim.getCurrFrame());
        assertNotEquals(a, anim.getCurrAnim());

        // Unset animation
        assertThrows(IllegalArgumentException.class, () -> anim.setState("c"));
    }
}
