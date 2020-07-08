package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PropertyActiveiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_activeiy);
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.breathe);
        animator.setTarget(findViewById(R.id.anim_pic));
        animator.setDuration(1000);
        animator.start();
        Button durationButton=(Button)findViewById(R.id.duration_BU);
        durationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText =(EditText)findViewById(R.id.duration_ET);
                Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                if (editText.getText().toString()!="")
                {
                    if(pattern.matcher(editText.getText().toString()).matches())
                    {
                        Animator animator = AnimatorInflater.loadAnimator(PropertyActiveiy.this, R.animator.breathe);
                        animator.setDuration(Integer.parseInt(editText.getText().toString()));
                        animator.setTarget(findViewById(R.id.anim_pic));
                        animator.start();
                    }
                    else
                    {
                        Toast.makeText(PropertyActiveiy.this,"请输入数字",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(PropertyActiveiy.this,"请输入数字",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}