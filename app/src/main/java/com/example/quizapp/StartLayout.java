package com.example.quizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StartLayout extends View {

    Paint paint;

    public StartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 背景、半透明
        canvas.drawColor(Color.argb(0, 0, 0, 0));

        // 円
        paint.setColor(Color.argb(255, 0, 0, 255));
        paint.setStrokeWidth(30);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
        canvas.drawCircle(250, 450, 100, paint);

        // バツ
        paint.setStrokeWidth(30);
        paint.setColor(Color.argb(255, 255, 0, 0));
        // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
        canvas.drawLine(550, 350, 750, 550, paint);
        paint.setStrokeWidth(30);
        paint.setColor(Color.argb(255, 255, 0, 0));
        // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
        canvas.drawLine(550, 550, 750, 350, paint);
    }
}