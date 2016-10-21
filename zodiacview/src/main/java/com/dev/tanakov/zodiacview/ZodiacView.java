/**
 * Copyright 10/20/16 Vladimir Tanakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dev.tanakov.zodiacview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ZodiacView extends View {

    //region DEFAULT VALUES

    private static final int DEFAULT_STAR_COUNT = 30;
    private static final int DEFAULT_STAR_SIZE_MIN = 10;
    private static final int DEFAULT_STAR_SIZE_MAX = 20;
    private static final int DEFAULT_RELATION_SIZE = 5;
    private static final float DEFAULT_SPEED = 0.7f;
    private static final int DEFAULT_DISTANCE = 200;
    private static final String DEFAULT_COLOR_BACKGROUND = "#16151f";
    private static final String DEFAULT_COLOR_STAR = "#49348b";
    private static final String DEFAULT_COLOR_RELATION = "#49348b";

    //endregion

    private int mWidth;
    private int mHeight;

    private List<Star> mStars = new ArrayList<>();
    private int mStarCount = DEFAULT_STAR_COUNT;
    private int mStarSizeMin = DEFAULT_STAR_SIZE_MIN;
    private int mStarSizeMax = DEFAULT_STAR_SIZE_MAX;
    private int mRelationSize = DEFAULT_RELATION_SIZE;

    private float mSpeed = DEFAULT_SPEED;
    private int mDistance = DEFAULT_DISTANCE;

    private Paint mPaintRelation;
    private Paint mPaintStar;

    private int mColorBackground = Color.parseColor(DEFAULT_COLOR_BACKGROUND);
    private int mColorStar = Color.parseColor(DEFAULT_COLOR_STAR);
    private int mColorRelation = Color.parseColor(DEFAULT_COLOR_RELATION);

    public ZodiacView(Context context) {
        super(context);
        init();
    }

    public ZodiacView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZodiacView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ZodiacView, defStyleAttr, 0);

        mStarCount = a.getInt(R.styleable.ZodiacView_zv_star_count, DEFAULT_STAR_COUNT);
        mStarSizeMin = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_star_size_min, DEFAULT_STAR_SIZE_MIN);
        mStarSizeMax = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_star_size_max, DEFAULT_STAR_SIZE_MAX);
        mRelationSize = a.getDimensionPixelSize(R.styleable.ZodiacView_zv_relation_size, DEFAULT_RELATION_SIZE);
        mSpeed = a.getFloat(R.styleable.ZodiacView_zv_speed, DEFAULT_SPEED);
        mDistance = a.getInt(R.styleable.ZodiacView_zv_distance, DEFAULT_DISTANCE);
        mColorBackground = a.getColor(R.styleable.ZodiacView_zv_color_bg, Color.parseColor(DEFAULT_COLOR_BACKGROUND));
        mColorStar = a.getColor(R.styleable.ZodiacView_zv_color_star, Color.parseColor(DEFAULT_COLOR_STAR));
        mColorRelation = a.getColor(R.styleable.ZodiacView_zv_color_relation, Color.parseColor(DEFAULT_COLOR_RELATION));

        a.recycle();

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        createStars();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mStarCount; i++) {
            mStars.get(i).connectedStars.clear();
        }

        for (int i = 0; i < mStarCount; i++) {
            Star star = mStars.get(i);
            moveStar(star);

            canvas.drawCircle(star.x, star.y, star.size, mPaintStar);

            for (int j = 0; j < mStarCount; j++) {
                Star refStar = mStars.get(j);
                if (star == refStar || refStar.connectedStars.contains(star)) {
                    continue;
                }

                if (Math.abs(star.x - refStar.x) < mDistance && Math.abs(star.y - refStar.y) < mDistance) {
                    star.connectedStars.add(refStar);
                    drawRelation(canvas, star, refStar);
                }
            }
        }

        invalidate();
    }

    private void init() {
        setBackgroundColor(mColorBackground);

        mPaintStar = new Paint();
        mPaintStar.setColor(mColorStar);
        mPaintStar.setStyle(Paint.Style.FILL);
        mPaintStar.setAntiAlias(true);

        mPaintRelation = new Paint();
        mPaintRelation.setColor(mColorRelation);
        mPaintRelation.setStrokeWidth(mRelationSize);
        mPaintRelation.setStyle(Paint.Style.FILL);
        mPaintRelation.setAntiAlias(true);
    }

    private void createStars() {
        mStars.clear();
        for (int i = 0; i < mStarCount; i++) {
            mStars.add(createStar());
        }
    }

    private Star createStar() {
        Star star = new Star();
        star.x = (float) Math.random() * mWidth;
        star.y = (float) Math.random() * mHeight;
        star.dirX = (float) (Math.random() - 0.5);
        star.dirY = (float) (Math.random() - 0.5);
        star.size = (float) (Math.random() * (mStarSizeMax - mStarSizeMin)) + mStarSizeMin;

        return star;
    }

    private Star moveStar(Star star) {
        star.x += star.dirX * mSpeed;
        star.y += star.dirY * mSpeed;

        if (star.y + star.size > mHeight) {
            star.dirY = (float) (Math.random() * -1);
        } else if (star.y - star.size < 0) {
            star.dirY = (float) Math.random();
        } else if (star.x + star.size > mWidth) {
            star.dirX = (float) Math.random() * -1;
        } else if (star.x - star.size < 0) {
            star.dirX = (float) Math.random();
        }

        return star;
    }

    private void drawRelation(Canvas canvas, Star star, Star refStar) {
        double x = Math.abs(star.x - refStar.x);
        double y = Math.abs(star.y - refStar.y);
        double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        int opacity = (int) ((1.4 - dist / mDistance) * 255);

        mPaintRelation.setAlpha(opacity);

        canvas.drawLine(star.x, star.y, refStar.x, refStar.y, mPaintRelation);
    }

    //region SETTERS

    public void setStarCount(int count) {
        mStarCount = count;
    }

    public void setStarSizeMin(int size) {
        mStarSizeMin = size;
    }

    public void setStarSizeMax(int size) {
        mStarSizeMax = size;
    }

    public void setRelationSize(int size) {
        mRelationSize = size;
    }

    public void setSpeed(float speed) {
        mSpeed = speed;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public void setColorBackground(int bgColor) {
        mColorBackground = bgColor;
    }

    public void setColorStar(int starColor) {
        mColorStar = starColor;
    }

    public void setColorRelation(int relationColor) {
        mColorRelation = relationColor;
    }

    //endregion
}
