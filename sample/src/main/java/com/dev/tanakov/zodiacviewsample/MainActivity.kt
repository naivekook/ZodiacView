package com.dev.tanakov.zodiacviewsample

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.dev.tanakov.zodiacview.ZodiacConfig
import com.dev.tanakov.zodiacview.ZodiacView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zodiacConfig = ZodiacConfig(
            starCount = 10,
            speed = 5f,
            colorStar = R.color.colorAccent,
            colorRelation = R.color.colorAccent,
            interactionEnabled = true
        )
        val zodiacView = ZodiacView.setup(this, zodiacConfig)

        val frameLayout = findViewById<FrameLayout>(R.id.zodiac_container)
        frameLayout.addView(zodiacView)
    }
}
