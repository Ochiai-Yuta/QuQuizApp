package com.example.quizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CirclePaint extends View {

    Paint paint;

    public CirclePaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float centerX = getWidth() * 0.5f;
        final float centerY = getHeight() * 0.5f;
        final float radius = Math.min(centerX*0.5f, centerY*0.5f);
        // 背景、半透明
        canvas.drawColor(Color.argb(0, 0, 0, 0));

        // 円
        paint.setColor(Color.argb(255, 0, 0, 255));
        paint.setStrokeWidth(30);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
        canvas.drawCircle(centerX, centerY, radius, paint);

    }
}