package inf112.skeleton.app.Back_end;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/*
 * Class to keep track of the different audios. 
 */
public class AudioManager {
    
    private float volume = 1f;
    private final HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    private final HashMap<String, Music> music = new HashMap<String, Music>();

    public AudioManager()
    {
        try {
            // music
            music.put("MainSong", Gdx.audio.newMusic(Gdx.files.internal("assets/Sound/music/main.mp3")));

            // sfx
            sounds.put("Hit", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/hit.wav")));
            sounds.put("Hurt", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/hurt.wav")));
            sounds.put("Jump", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/jump.wav")));
            sounds.put("Pickup", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/pickup.wav")));
            sounds.put("Select", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/select.wav")));
            sounds.put("Shoot", Gdx.audio.newSound(Gdx.files.internal("assets/Sound/sfx/shoot.wav")));
        } catch (Exception ignored) {
            // used for tests
        }
    }

    /**
     * Plays given song with input as name
     */
    public void play(String sound)
    {
        try {
            sounds.get(sound).play(this.volume);
        } catch (Exception ignored) {
            
        }
        try {
            Music song = music.get(sound);
            song.setVolume(this.volume - 0.75f);
            song.play();
            song.setLooping(true);
        } catch (Exception ignored) {
            
        }
    }

    /**
     * Gets the current volume of AudioManager instance
     * @return volume
     */
    public float getVolume()
    {
        return this.volume;
    }

    /**
     * Sets the current volume of AudioManager instance
     */
    public void setVolume(float volume)
    {
        this.volume = volume;
    }
}
