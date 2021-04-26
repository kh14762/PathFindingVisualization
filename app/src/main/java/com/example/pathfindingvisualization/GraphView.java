package com.example.pathfindingvisualization;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends SurfaceView implements SurfaceHolder.Callback {
    private Cell cell;
    private int cellSize = 50;
    private List<Cell> cellArray;
    private GameLoop gameLoop;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //  Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        //  Add gameLoop
        gameLoop = new GameLoop(this, surfaceHolder);
        //  Add many cells
        cellArray = new ArrayList<>();
        for (int i = 0; i < getScreenHeight(); i += cellSize) {
            for (int j = 0; j < getScreenWidth(); j += cellSize) {
                cellArray.add(new Cell(context, j, i, cellSize));
            }
        }

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        for (int i = 0; i < cellArray.size(); i++) {
            cellArray.get(i).draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = cellArray.size() - 1; i >= 0; i--) {
                    Cell cell = cellArray.get(i);
                    if (cell.isTouched(event.getX(), event.getY())) {
                        cell.changeColor();
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                for (int i = cellArray.size() - 1; i >= 0; i--) {
                    Cell cell = cellArray.get(i);
                    if (cell.isTouched(event.getX(), event.getY())) {
                        cell.changeColor();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return false;
        }
        return super.onTouchEvent(event);
    }

    public void update() {
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
