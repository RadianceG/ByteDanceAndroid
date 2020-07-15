package com.example.homework7;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import static android.provider.CalendarContract.CalendarCache.URI;

public class VideoActivity extends AppCompatActivity {
    private final static String videoURI="http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";

    private int mSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        final VideoView videoView=(VideoView)findViewById(R.id.videoV);
        videoView.setVideoURI(Uri.parse(videoURI));

        videoView.start();

        final SeekBar seekBar=(SeekBar) findViewById(R.id.seekb1);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(seekBar.getProgress()*1000);

            }
        });
        Handler myHandler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 10000:
                        mSecond=videoView.getDuration();
                        seekBar.setMax(mSecond/1000);
                        seekBar.setProgress(videoView.getCurrentPosition()/1000);
                        removeMessages(10000);
                        sendEmptyMessageDelayed(10000,1000);
                        break;
                }
            }
        };
        myHandler.sendEmptyMessage(10000);
        final Button playButton=(Button)findViewById(R.id.playpauseBU);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoView.isPlaying()==true)
                {
                    videoView.pause();
                    playButton.setText("play");
                }
                else
                {
                    videoView.start();
                    playButton.setText("pause");
                }
            }
        });

    }

}


//视频进度条更新
