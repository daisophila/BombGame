package com.example.bombgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class MyView extends View {
    Bitmap bomb1;
    Bitmap bomb2;
    Bitmap bomb3;
    static Boolean sayBomb = false;
    static Boolean gameOver = false;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        setBackgroundColor(Color.BLUE);
    }

    public void onSizeChanged(int w, int h, int ow, int oh) {
        bomb1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.bomb1);
        bomb2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.bomb2);
        bomb3 = BitmapFactory.decodeResource(this.getResources(),R.drawable.bomb3);

        bomb1 = Bitmap.createScaledBitmap(bomb1, MainActivity.width *2/3, MainActivity.height *2/3,true);
        bomb2 = Bitmap.createScaledBitmap(bomb2, MainActivity.width *2/3, MainActivity.height *2/3,true);
        bomb3 = Bitmap.createScaledBitmap(bomb3, MainActivity.width *2/3, MainActivity.height *2/3,true);

    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(60);
        MainActivity.checkMin();

        if (MainActivity.lapseMin >= MainActivity.randomMin) {
            canvas.drawBitmap(bomb3, 50, 150, null);
            canvas.drawText("폭탄 터진 시간"+MainActivity.randomMin+"초",100,150,p);

            if (sayBomb == false) {
                sayBomb = true;
                gameOver = true;
                MainActivity.stopBackSound();
                MainActivity.sayBomb();

            }
        }
        else {
            if ((MainActivity.lapseMin)%2==0)
                canvas.drawBitmap(bomb1,50,150,null);
            else
                canvas.drawBitmap(bomb2,50,150,null);

            canvas.drawText("지나간시간"+MainActivity.lapseMin+"",100,100,p);
        }
        invalidate();

    }

}
