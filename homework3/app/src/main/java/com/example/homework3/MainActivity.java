package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLo=(Button)findViewById(R.id.Lottie_BU);
        buttonLo.setOnClickListener(this);
        Button buttonPro=(Button)findViewById(R.id.Property_BU);

        buttonPro.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Lottie_BU:
                Intent intent0 = new Intent(MainActivity.this, LottieActivity.class);

                startActivity(intent0);
                break;
            case R.id.Property_BU:
                Intent intent1 = new Intent(MainActivity.this,PropertyActiveiy.class);

                startActivity(intent1);
                break;

            default:
                break;
        }
    }
}