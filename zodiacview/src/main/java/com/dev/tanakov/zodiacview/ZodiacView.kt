/**
 * Copyright 16/08/21 Vladimir Tanakov
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dev.tanakov.zodiacview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.core.content.ContextCompat
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class ZodiacView : View, OnTouchListener {

    private var viewWidth = 0
    private var viewHeight = 0
    private val stars: MutableList<Star> = mutableListOf()

    private var starCount = DEFAULT_STAR_COUNT
    private var starSizeMin = DEFAULT_STAR_SIZE_MIN
    private var starSizeMax = DEFAULT_STAR_SIZE_MAX
    private var relationSize = DEFAULT_RELATION_SIZE
    private var speed = DEFAULT_SPEED
    private var distance = DEFAULT_DISTANCE
    private var colorBackground = ContextCompat.getColor(context, R.color.zv_default_color_background)
    private var colorStar = ContextCompat.getColor(context, R.color.zv_default_color_star)
    private var colorRelation = ContextCompat.getColor(context, R.color.zv_default_color_relation)
    private var interactionEnabled = DEFAULT_INTERACTION_ENABLED

    private lateinit var paintStar: Paint
    private lateinit var paintRelation: Paint

    private constructor(context: Context) : super(context)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ZodiacView, defStyleAttr, 0)
        starCount = a.getInt(R.styleable.ZodiacView_zv_star_count, DEFAULT_STAR_COUNT)
        starSizeMin = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_star_size_min, DEFAULT_STAR_SIZE_MIN)
        starSizeMax = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_star_size_max, DEFAULT_STAR_SIZE_MAX)
        relationSize = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_relation_size, DEFAULT_RELATION_SIZE)
        speed = a.getFloat(R.styleable.ZodiacView_zv_speed, DEFAULT_SPEED)
        distance = a.getInt(R.styleable.ZodiacView_zv_distance, DEFAULT_DISTANCE)
        colorBackground = a.getColor(
            R.styleable.ZodiacView_zv_color_bg,
            ContextCompat.getColor(context, R.color.zv_default_color_background)
        )
        colorStar = a.getColor(
            R.styleable.ZodiacView_zv_color_star,
            ContextCompat.getColor(context, R.color.zv_default_color_star)
        )
        colorRelation = a.getColor(
            R.styleable.ZodiacView_zv_color_relation,
            ContextCompat.getColor(context, R.color.zv_default_color_relation)
        )
        interactionEnabled = a.getBoolean(R.styleable.ZodiacView_zv_interaction_enabled, DEFAULT_INTERACTION_ENABLED)
        a.recycle()
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        createStars()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val starCount = stars.size
        for (i in 0 until starCount) {
            stars[i].connectedStars.clear()
        }
        for (i in 0 until starCount) {
            val star = stars[i]
            moveStar(star)
            canvas.drawCircle(star.x, star.y, star.size, paintStar)
            for (j in 0 until starCount) {
                val refStar = stars[j]
                if (star === refStar || refStar.connectedStars.contains(star)) {
                    continue
                }
                if (abs(star.x - refStar.x) < distance && abs(star.y - refStar.y) < distance) {
                    star.connectedStars.add(refStar)
                    drawRelation(canvas, star, refStar)
                }
            }
        }
        invalidate()
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x
        val y = motionEvent.y
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                val star = createStar().apply {
                    this.x = x
                    this.y = y
                }
                stars.add(star)
            }
        }
        return true
    }

    private fun init() {
        setBackgroundColor(colorBackground)
        paintStar = Paint().apply {
            color = colorStar
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintRelation = Paint().apply {
            color = colorRelation
            strokeWidth = relationSize.toFloat()
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        if (interactionEnabled) {
            setOnTouchListener(this)
        }
    }

    private fun createStars() {
        stars.clear()
        for (i in 0 until starCount) {
            stars.add(createStar())
        }
    }

    private fun createStar(): Star {
        val star = Star()
        star.x = Math.random().toFloat() * viewWidth
        star.y = Math.random().toFloat() * viewHeight
        star.dirX = (Math.random() - 0.5).toFloat()
        star.dirY = (Math.random() - 0.5).toFloat()
        star.size = (Math.random() * (starSizeMax - starSizeMin)).toFloat() + starSizeMin
        return star
    }

    private fun moveStar(star: Star): Star {
        star.x += star.dirX * speed
        star.y += star.dirY * speed
        when {
            star.y + star.size > viewHeight -> star.dirY = (Math.random() * -1).toFloat()
            star.y - star.size < 0          -> star.dirY = Math.random().toFloat()
            star.x + star.size > viewWidth  -> star.dirX = Math.random().toFloat() * -1
            star.x - star.size < 0          -> star.dirX = Math.random().toFloat()
        }
        return star
    }

    private fun drawRelation(canvas: Canvas, star: Star, refStar: Star) {
        val x = abs(star.x - refStar.x)
        val y = abs(star.y - refStar.y)
        val dist = sqrt(x.pow(2) + y.pow(2))
        val opacity = (1.4 - dist / distance) * 255
        paintRelation.alpha = opacity.toInt()
        canvas.drawLine(star.x, star.y, refStar.x, refStar.y, paintRelation)
    }

    companion object {
        fun setup(context: Context, config: ZodiacConfig): ZodiacView {
            return ZodiacView(context).apply {
                starCount = config.starCount
                starSizeMin = config.starSizeMin
                starSizeMax = config.starSizeMax
                relationSize = config.relationSize
                speed = config.speed
                distance = config.distance
                interactionEnabled = config.interactionEnabled
                colorBackground = ContextCompat.getColor(context, config.colorBackground)
                colorStar = ContextCompat.getColor(context, config.colorStar)
                colorRelation = ContextCompat.getColor(context, config.colorRelation)
                init()
            }
        }
    }
}
