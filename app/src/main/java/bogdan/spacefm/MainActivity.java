package bogdan.spacefm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import java.io.IOException;
import java.io.SerializablePermission;

public class MainActivity extends AppCompatActivity {

    Button b_play;
    Button buttonPlay;
    Button buttonPause;

    MediaPlayer mediaPlayer;
    private MediaPlayer mPlayer;
    Intent playbackServiceIntent;

    ConstraintLayout constraintLayout;

    boolean prepared = false;
    boolean started = false;

    String stream = "http://84.200.53.95:8888/spacefm128";

    private SeekBar volumeSeekbar;
    private AudioManager audioManager;

    FloatingActionButton fab_plus, fab_twitter, fab_fb, fab_yt, fab_insta;
    Animation FabOpen, FabClose, FabRotateClockwise, FabRotateanticlockwise;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        mPlayer = new MediaPlayer();

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        ConstraintLayout constraintLayout = findViewById(R.id.myLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        fab_plus = findViewById(R.id.fab_plus);
        fab_twitter = findViewById(R.id.fab_twitter);
        fab_fb = findViewById(R.id.fab_facebook);
        fab_yt = findViewById(R.id.fab_youtube);
        fab_insta = findViewById(R.id.fab_instagram);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRotateClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRotateanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                } else {
                    fab_fb.startAnimation(FabOpen);
                    fab_twitter.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRotateClockwise);
                    fab_yt.startAnimation(FabOpen);
                    fab_insta.startAnimation(FabOpen);
                    fab_insta.setClickable(true);
                    fab_fb.setClickable(true);
                    fab_twitter.setClickable(true);
                    fab_yt.setClickable(true);
                    isOpen = true;
                }

            }
        });

        FloatingActionButton fb = findViewById(R.id.fab_facebook);
        fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/spacefm.radiostation/"));
                startActivity(browserIntent);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }
            }
        });
        FloatingActionButton twitter = findViewById(R.id.fab_twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/radiospacefm"));
                startActivity(browserIntent);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }
            }
        });
        FloatingActionButton instagram = findViewById(R.id.fab_instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/spacefm_dancestation/"));
                startActivity(browserIntent);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }
            }
        });
        FloatingActionButton youtube = findViewById(R.id.fab_youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/channel/UCoJZ7g3Z5dZJbHnUdhe0tLA"));
                startActivity(browserIntent);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }
            }
        });

        Button mb_like = findViewById(R.id.b_track);
        mb_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(""));
                startActivity(browserIntent);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }
            }
        });

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();

        b_play = findViewById(R.id.b_play);

        b_play.setEnabled(false);
        b_play.setBackgroundResource(R.drawable.ic_autorenew_black_16dp);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if (isOpen) {
                        fab_fb.startAnimation(FabClose);
                        fab_twitter.startAnimation(FabClose);
                        fab_plus.startAnimation(FabRotateanticlockwise);
                        fab_yt.startAnimation(FabClose);
                        fab_insta.startAnimation(FabClose);
                        fab_insta.setClickable(false);
                        fab_fb.setClickable(false);
                        fab_twitter.setClickable(false);
                        fab_yt.setClickable(false);
                        isOpen = false;
                    }
                    return true;
                }
            });

 /*       b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (started) {

                    started = false;
                    mediaPlayer.pause();
                    b_play.setBackgroundResource(R.drawable.baseline_play_arrow_white_24);

                } else {

                    started = true;
                    mediaPlayer.start();
                    b_play.setBackgroundResource(R.drawable.baseline_pause_white_24);

                }

            }
        });*/

        buttonPlay = findViewById(R.id.b_play);
        buttonPause = findViewById(R.id.b_pause);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.start();
                startService(playbackServiceIntent);
                buttonPlay.setVisibility(View.INVISIBLE);
                buttonPause.setVisibility(View.VISIBLE);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }

            }
        });

       buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
                stopService(playbackServiceIntent);
                buttonPause.setVisibility(View.INVISIBLE);
                buttonPlay.setVisibility(View.VISIBLE);
                if (isOpen) {
                    fab_fb.startAnimation(FabClose);
                    fab_twitter.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRotateanticlockwise);
                    fab_yt.startAnimation(FabClose);
                    fab_insta.startAnimation(FabClose);
                    fab_insta.setClickable(false);
                    fab_fb.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_yt.setClickable(false);
                    isOpen = false;
                }

            }
        });

        playbackServiceIntent = new Intent(this, BackgroundService.class);
    }

    private void startService()
    {
        startService(new Intent(MainActivity.this,BackgroundService.class));
    }
    private void stopService()
    {
        stopService(new Intent(MainActivity.this,BackgroundService.class));
    }

    private void initControls() {

        try {

            volumeSeekbar = findViewById(R.id.sb);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        } catch (Exception e) {

        }

    }

    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            b_play.setEnabled(true);
            b_play.setBackgroundResource(R.drawable.baseline_play_arrow_white_24);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (started) {

            mediaPlayer.pause();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (started) {

            mediaPlayer.start();

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (prepared) {

            mediaPlayer.release();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            int index = volumeSeekbar.getProgress();
            volumeSeekbar.setProgress(index + 1);
            return true;

        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            int index = volumeSeekbar.getProgress();
            volumeSeekbar.setProgress(index - 1);
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    public void clickExit(View v) {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
