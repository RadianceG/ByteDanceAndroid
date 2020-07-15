package com.example.homework7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    private final static String ImageURL="https://wx4.sinaimg.cn/mw690/844527a1ly1ggr22tmid2j203c03c3yi.jpg";
    private final static String ImageUrl1="https://wx2.sinaimg.cn/mw690/844527a1ly1ggr22tt3k6j20sg0g01kx.jpg";
    private final static String ImageUrl2="http://www.baidu.com";//error image
    private int mStatus=0;
    ArrayList<String> imageList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageList.add(ImageURL);
        imageList.add(ImageUrl1);
        imageList.add(ImageUrl2);
        Button loadImage=(Button)findViewById(R.id.loadBU);
        final ImageView imageView=(ImageView)findViewById(R.id.imageV);
        loadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mStatus==2)
                {
                    Glide.with(ImageActivity.this)
                            .load(imageList.get(mStatus))
                            .apply(new RequestOptions().centerCrop())
                            .placeholder(R.drawable.icon_progress_bar)
                            .error(R.drawable.icon_failure)
                            .transition(withCrossFade(2000))
                            .into(imageView);
                    mStatus=0;
                }
                else
                {
                    Glide.with(ImageActivity.this)
                            .load(imageList.get(mStatus))
                            .apply(new RequestOptions().centerCrop())
                            .placeholder(R.drawable.icon_progress_bar)
                            .error(R.drawable.icon_failure)
                            .transition(withCrossFade(2000))
                            .into(imageView);
                    mStatus++;
                }

            }
        });
    }
}