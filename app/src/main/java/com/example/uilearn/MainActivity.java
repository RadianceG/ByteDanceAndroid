package com.example.uilearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int COMPLETED = 0;
    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private int setuStatus=0;
    public List<Integer> setus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.edit_text);
        Button button=(Button)findViewById(R.id.button);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        setus=new ArrayList<>();
        setus.add(R.drawable.setu);
        setus.add(R.drawable.setu2);
        setus.add(R.drawable.setu3);
        setus.add(R.drawable.setu4);
        Button button2=(Button)findViewById(R.id.button2);
        imageView=(ImageView)findViewById(R.id.image_view);
        textView=(TextView)findViewById(R.id.text_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText=editText.getText().toString();
                textView.setText(inputText);
                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(setuStatus!=3)
                        {
                            setuStatus+=1;
                            imageView.setImageResource(setus.get(setuStatus));
                        }
                        else{
                            setuStatus=0;
                            imageView.setImageResource(setus.get(setuStatus));
                        }
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                }, new Random().nextInt(2500)+500);
            }
        });
    }
}