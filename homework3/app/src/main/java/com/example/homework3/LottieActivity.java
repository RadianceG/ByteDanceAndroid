package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class LottieActivity extends AppCompatActivity {

    private int animStatus=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lottie);
        final LottieAnimationView lottieAnimationView=(LottieAnimationView) findViewById(R.id.lottie_anim);
        SeekBar seekBar=(SeekBar) findViewById(R.id.seekb1);
        TextView textView=(TextView) findViewById(R.id.autoTV);
        Button autoButton=(Button) findViewById(R.id.autoBU);
        autoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animStatus==1)
                {
                    lottieAnimationView.pauseAnimation();
                    TextView textView=(TextView) findViewById(R.id.autoTV);
                    textView.setText("自动播放：关");
                    animStatus=0;
                }
                else
                {
                    lottieAnimationView.resumeAnimation();
                    TextView textView=(TextView) findViewById(R.id.autoTV);
                    textView.setText("自动播放：开");
                    animStatus=1;
                }
            }
        });
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lottieAnimationView.setFrame(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}