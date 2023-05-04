package inf112.skeleton.app;
import org.mockito.Mockito;

import inf112.skeleton.app.Back_end.AudioManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AudioManagerTest {
    
    AudioManager audioManager;

    @BeforeEach 
    void setUp(){
        audioManager = mock(AudioManager.class, Mockito.CALLS_REAL_METHODS);
    }

    /**
     * Tests setter and getter for audiomanager volume
     */
    @Test 
    void testSetAndGetVolume(){
        audioManager.setVolume(0.3f);
        assertEquals(0.3f, audioManager.getVolume());
    }

}
