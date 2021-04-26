package com.example.pathfindingvisualization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;
import androidx.core.util.Pools;

public class Ball {
    private double positionX;
    private double positionY;

    private int xVel;
    private int yVel;

    private double radius;
    private Paint paint;

    public Ball(Context context, double positionX, double positionY, double radius) {
        int rWidth = (int)Math.random() * GraphView.getScreenWidth() / 2;
        int rHeight = (int)Math.random() * GraphView.getScreenWidth() / 2;
        //position of ball
        this.positionX = positionX + rWidth;
        this.positionY = positionY + rHeight;

        //radii of the ball
        this.radius = radius;

        paint = new Paint();
        paint.setColor(Color.WHITE);

        //set velocity
        xVel = 30;
        yVel = 45;


    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }

    public void update() {

        positionX += xVel;
        positionY += yVel;


        if (positionY - radius < 0 || positionY + radius > GraphView.getScreenHeight()) {
            // the ball has hit the top or the bottom of the canvas

            if (positionY - radius < 0) {
                // the ball has hit the top of the canvas
                positionY = radius;
            } else {
                // the ball has hit the bottom of the canvas
                positionY = GraphView.getScreenHeight() - radius;
            }

            // reverse the y direction of the ball
            yVel *= -1;
        }

        if (positionX - radius < 0 || positionX + radius > GraphView.getScreenWidth()) {
            // the ball has hit the sides of the canvas
            if (positionX - radius < 0) {
                // the ball has hit the left of the canvas
                positionX = radius;
            } else {
                // the ball has hit the right of the canvas
                positionX = GraphView.getScreenWidth() - radius;
            }

            // reverse the x direction of the ball
            xVel *= -1;
        }
    }

}
