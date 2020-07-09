package com.bytedance.camp.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bytedance.camp.chapter4.widget.ClockView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case 10000:
                        findViewById(R.id.clock).invalidate();
                        removeMessages(10000);
                        sendEmptyMessageDelayed(10000,1000);
                        break;
                }
            }
        };
        handler.sendEmptyMessage(10000);
    }
}
