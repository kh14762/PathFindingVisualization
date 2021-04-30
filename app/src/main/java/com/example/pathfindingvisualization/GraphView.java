package com.example.pathfindingvisualization;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends SurfaceView implements SurfaceHolder.Callback {
    private Cell cell;
    private int cellSize = 50;
    private List<Cell> cellList;
    private GameLoop gameLoop;
    Cell startCell, endCell;

    private boolean wallsClicked, startClicked, endClicked;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //  Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        //  Add gameLoop
        gameLoop = new GameLoop(this, surfaceHolder);
        //  Add many cells
        cellList = new ArrayList<>();
        for (int i = 0; i < getScreenHeight(); i += cellSize) {
            for (int j = 0; j < getScreenWidth(); j += cellSize) {
                cellList.add(new Cell(context, j, i, cellSize));
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

        for (int i = 0; i < cellList.size(); i++) {
            cellList.get(i).draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getWallsClicked()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    addWalls(event);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    addWalls(event);
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    return false;
            }
        } else if (getStartClicked()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                addStart(event);
                return true;
            }
            return false;
        } else if (getEndClicked()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                addEnd(event);
                return true;
            }
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

    public boolean getWallsClicked() {
        return wallsClicked;
    }
    public void setWallsClicked(boolean wallsClicked) {
        this.wallsClicked = wallsClicked;
    }

    public void addWalls(MotionEvent event) {
        for (int i = cellList.size() - 1; i >= 0; i--) {
            Cell cell = cellList.get(i);
            if (cell.isTouched(event.getX(), event.getY())) {
                cell.setColor(Color.BLACK);
                // add to pathFinding.borderList
                //if there is no duplicate cell in border list add
//                if (!pathFinding.checkBorderDuplicate(cell)) {
//                    pathFinding.borderList.add(cell);
//                }
            }

        }
    }

    public boolean getStartClicked() {
        return startClicked;
    }
    public void setStartClicked(boolean startClicked) {
        this.startClicked = startClicked;
    }
    public void addStart(MotionEvent event) {
        for (int i = cellList.size() - 1; i >= 0; i--) {
            Cell cell = cellList.get(i);
            if (cell.isTouched(event.getX(), event.getY()) && startCell == null) {
                cell.setColor(Color.BLUE);
                startCell = cell;
            } else if (cell.isTouched(event.getX(), event.getY()) && startCell != null){
                startCell.setColor(Color.LTGRAY);
                cell.setColor(Color.BLUE);
                startCell = cell;
            }

        }
    }

    public boolean getEndClicked() {
        return endClicked;
    }
    public void setEndClicked(boolean endClicked) {
        this.endClicked = endClicked;
    }
    public void addEnd(MotionEvent event) {
        for (int i = cellList.size() - 1; i >= 0; i--) {
            Cell cell = cellList.get(i);
            if (cell.isTouched(event.getX(), event.getY()) && endCell == null) {
                cell.setColor(Color.RED);
                endCell = cell;
            } else if (cell.isTouched(event.getX(), event.getY()) && endCell != null){
                endCell.setColor(Color.LTGRAY);
                cell.setColor(Color.RED);
                endCell = cell;
            }

        }
    }

}
