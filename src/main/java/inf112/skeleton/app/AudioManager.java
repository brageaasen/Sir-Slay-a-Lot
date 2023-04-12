package inf112.skeleton.app;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    
    private float volume = 1f;
    private HashMap<String, Sound> sounds = new HashMap<String, Sound>();

    public AudioManager()
    {
        // music
        sounds.put("MainSong", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/music/main.mp3")));
        
        // sfx
        sounds.put("Hit", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/hit.wav")));
        sounds.put("Hurt", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/hurt.wav")));
        sounds.put("Jump", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/jump.wav")));
        sounds.put("Pickup", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/pickup.wav")));
        sounds.put("Select", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/select.wav")));
        sounds.put("Shoot", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/shoot.wav")));
    }

    /**
     * Plays given song with input as name
     */
    public void Play(String sound)
    {
        sounds.get(sound).play(this.volume);
    }
    
    /**
     * Stops given song with input as name
     */
    public void Stop(String sound)
    {
        sounds.get(sound).stop();
    }

    // public void Loop()

    /**
     * Gets the current volume of AudioManager instance
     * @return volume
     */
    public float GetVolume()
    {
        return this.volume;
    }

    /**
     * Sets the current volume of AudioManager instance
     */
    public void SetVolume(float volume)
    {
        this.volume = volume;
    }
}
