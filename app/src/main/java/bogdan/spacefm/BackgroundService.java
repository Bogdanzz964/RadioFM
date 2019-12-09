package bogdan.spacefm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class BackgroundService extends Service implements MediaPlayer.OnCompletionListener
{
    MediaPlayer mediaPlayer;
    private String STREAM_URL = "http://84.200.53.95:8888/spacefm128";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        mediaPlayer = new MediaPlayer();
        try
        {
            mediaPlayer.setDataSource(STREAM_URL);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            try
            {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(STREAM_URL);
                mediaPlayer.prepareAsync();

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                {
                    @Override
                    public void onPrepared(MediaPlayer mp)
                    {
                        mediaPlayer.start();
                    }
                });
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        return START_STICKY;
    }

    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }
}