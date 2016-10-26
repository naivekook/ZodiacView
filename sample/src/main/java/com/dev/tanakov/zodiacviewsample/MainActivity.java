package com.dev.tanakov.zodiacviewsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.dev.tanakov.zodiacview.ZodiacView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZodiacView zodiacView = ZodiacView.with(this)
                .starCount(10)
                .speed(5.0f)
                .colorStar(Color.YELLOW)
                .colorRelation(Color.YELLOW)
                .interactionEnabled(true)
                .build();

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.zodiac_container);
        frameLayout.addView(zodiacView);
    }
}
