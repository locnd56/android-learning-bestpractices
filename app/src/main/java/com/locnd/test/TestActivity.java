package com.locnd.test;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.locnd.appbase.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class TestActivity extends Activity implements View.OnClickListener {
    final int FORWARDTIME = 5000;
    final int BACKWARDTIME = 5000;

    ImageView iv_avarsong;
    ImageButton ib_previous;
    ImageButton ib_pause;
    ImageButton ib_next;
    ImageButton ib_loop;
    ImageButton ib_delete;
    ImageButton ib_play;
    TextView tv_songname;
    SeekBar sb_timer;
    TextView tv_timer;

    MediaPlayer mediaPlayer;
    int finalTime;
    int startTime;
    int secondsFinalTime;
    int minutesFinalTime;
    Handler myHandler;

    public enum MUSIC {
        TAMSUVOINGUOILA(R.raw.tamsuvoinguoila), SAUTATCA(R.raw.sautatca), NEUEMCONTONTAI(R.raw.neuemcontontai);
        int value;

        MUSIC(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp3widget);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        if (myHandler == null)
            myHandler = new Handler();
        mediaPlayer = MediaPlayer.create(this, R.raw.neuemcontontai);
        sb_timer.setMax((int) TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()));
    }

    private void initListener() {
        iv_avarsong.setOnClickListener(this);
        ib_previous.setOnClickListener(this);
        ib_pause.setOnClickListener(this);
        ib_pause.setOnClickListener(this);
        ib_next.setOnClickListener(this);
        ib_loop.setOnClickListener(this);
        ib_delete.setOnClickListener(this);
        ib_play.setOnClickListener(this);
        sb_timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initView() {
        iv_avarsong = (ImageView) findViewById(R.id.iv_songimage);
        tv_songname = (TextView) findViewById(R.id.tv_songname);
        ib_previous = (ImageButton) findViewById(R.id.ib_previous);
        ib_pause = (ImageButton) findViewById(R.id.ib_pause);
        ib_next = (ImageButton) findViewById(R.id.ib_next);
        ib_delete = (ImageButton) findViewById(R.id.ib_delete);
        ib_loop = (ImageButton) findViewById(R.id.ib_loop);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
//        sb_timer = (SeekBar) findViewById(R.id.sb_timer);
        ib_play = (ImageButton) findViewById(R.id.ib_play);
    }

    Runnable UpdateTimeTrack = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            int secondsStartTime = (int) (TimeUnit.MILLISECONDS.toSeconds(startTime));
            int secondsRemain = secondsFinalTime - secondsStartTime;
            int minutesRemain = secondsRemain / 60;
            if (secondsRemain > 60) {
                secondsRemain = secondsRemain % 60;
            }
            tv_timer.setText(minutesRemain + ":" + secondsRemain);
            sb_timer.setProgress(secondsStartTime);
            myHandler.postDelayed(this, 100);
            if (secondsFinalTime == secondsStartTime) {
                myHandler.removeCallbacks(this);
                resetSeekBar();
            }
        }
    };

    private void resetSeekBar() {
        ib_play.setVisibility(View.VISIBLE);
        ib_pause.setVisibility(View.GONE);
        sb_timer.setProgress(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_songimage:
                break;
            case R.id.tv_songname:
                break;
            case R.id.ib_previous:
                if (startTime > BACKWARDTIME) {
                    startTime = startTime - BACKWARDTIME;
                    mediaPlayer.seekTo(startTime);
                }
                break;
            case R.id.ib_pause:
                mediaPlayer.pause();
                ib_play.setVisibility(View.VISIBLE);
                ib_pause.setVisibility(View.GONE);
                break;
            case R.id.ib_play:
                ib_play.setVisibility(View.GONE);
                ib_pause.setVisibility(View.VISIBLE);

                mediaPlayer.start();
                setMediaAtBegin();
                myHandler.postDelayed(UpdateTimeTrack, 100);
                break;
            case R.id.ib_next:
                if (startTime > FORWARDTIME) {
                    startTime = startTime + FORWARDTIME;
                    mediaPlayer.seekTo(startTime);
                }
                break;
            case R.id.ib_loop:
                break;
            case R.id.ib_delete:
                break;
        }
    }

    private void setMediaAtBegin() {
        finalTime = mediaPlayer.getDuration();
        secondsFinalTime = (int) (TimeUnit.MILLISECONDS.toSeconds(finalTime));
        minutesFinalTime = (int) TimeUnit.MILLISECONDS.toMinutes(finalTime);

        tv_timer.setText(minutesFinalTime + ":" + (secondsFinalTime - TimeUnit.MINUTES.toSeconds(minutesFinalTime)));
        sb_timer.setProgress(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
