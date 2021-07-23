package com.example.quizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CrossPaint extends View {

    Paint paint;

    public CrossPaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float centerX = getWidth() * 0.5f;
        final float centerY = getHeight() * 0.5f;
        // 背景、半透明
        canvas.drawColor(Color.argb(0, 0, 0, 0));

        // バツ
        paint.setStrokeWidth(30);
        paint.setColor(Color.argb(255, 255, 0, 0));
        // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
        canvas.drawLine(centerX*0.5f, centerY*0.5f, centerX*1.5f, centerY*1.5f, paint);
        canvas.drawLine(centerX*0.5f, centerY*1.5f, centerX*1.5f, centerY*0.5f, paint);
    }
}