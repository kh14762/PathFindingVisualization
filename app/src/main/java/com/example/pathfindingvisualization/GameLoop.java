package com.example.pathfindingvisualization;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.logging.Handler;

public class GameLoop extends Thread {
    static final double MAX_UPS = 30.0;
    static final double UPS_PERIOD = (1E+3)/MAX_UPS;
    private boolean isRunning = false;
    private GraphView graphView;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(GraphView graphView, SurfaceHolder surfaceHolder) {
        this.graphView = graphView;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        //Declare time and cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        //GameLoop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning) {
            //try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    graphView.update();
                    updateCount++;
                    graphView.draw(canvas);
                }
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Skip frames to keep up with target UPS
            while(sleepTime < 0 && updateCount < MAX_UPS - 1) {
                graphView.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }
            //Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

}
