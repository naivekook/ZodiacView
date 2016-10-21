package com.dev.tanakov.zodiacviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.dev.tanakov.zodiacview.ZodiacView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZodiacView zodiacView = new ZodiacView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.zodiac_container);
        frameLayout.addView(zodiacView);
    }
}
