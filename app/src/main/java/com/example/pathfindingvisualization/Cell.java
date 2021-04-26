package com.example.pathfindingvisualization;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class Cell {
    protected int x;
    protected int y;
    protected int size;
    protected int inset;
    protected Rect outerRect;
    protected Rect innerRect;

    protected Paint paint;
    protected Paint paint1;

    public Cell(Context context, int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        inset = 1;

        outerRect = new Rect(x, y, x + size, y + size);
        paint = new Paint();
        paint.setColor(Color.WHITE);

        innerRect = new Rect(x + inset, y + inset, x + size - 3, y + size - 2);
        paint1 = new Paint();
        paint1.setColor(Color.LTGRAY);

    }

    public void draw(Canvas canvas) {
        canvas.drawRect(outerRect, paint);
        canvas.drawRect(innerRect, paint1);

    }

    public boolean isTouched(float x2, float y2) {
        return (x2 > x && x2 < x + size && y2 > y && y2 < y + size);
    }

    public void changeColor() {
        paint1.setColor(Color.BLACK);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
