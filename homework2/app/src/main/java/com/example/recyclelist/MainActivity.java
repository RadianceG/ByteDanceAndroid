package com.example.recyclelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listviewtest.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<com.example.listviewtest.Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        LinearLayout fensi =(LinearLayout)findViewById(R.id.fensi);
        fensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"你没有粉丝",Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout zan =(LinearLayout)findViewById(R.id.zan);
        zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"你也没有赞",Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout pinglun =(LinearLayout)findViewById(R.id.pinglun);
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"recycler_view的总子View为  "+count1(findViewById(R.id.recycler_view)),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFruits()
    {
        for (int i=0;i<4;i++)
        {
            Fruit apple=new Fruit("Apple",R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit pear=new Fruit("Pear",R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit banana=new Fruit("banana",R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange=new Fruit("orange",R.drawable.orange_pic);
            fruitList.add(orange);
        }
    }
    public int count1(View root) {
        int viewCount = 0;

        if (null == root) {
            return 0;
        }

        if (root instanceof ViewGroup) {
            viewCount++;
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View view = ((ViewGroup) root).getChildAt(i);
                if (view instanceof ViewGroup) {
                    viewCount += count1(view);
                } else {
                    viewCount++;
                }
            }
        }

        return viewCount;
    }
}