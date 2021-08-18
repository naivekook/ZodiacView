package io.github.naivekook.zodiacviewsample

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.github.naivekook.zodiacview.ZodiacConfig
import io.github.naivekook.zodiacview.ZodiacView

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
