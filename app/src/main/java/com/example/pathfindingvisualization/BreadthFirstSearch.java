package com.example.pathfindingvisualization;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    GraphView graphView;
    Cell uCell;
    Cell vCell;
    Cell sCell;
    List<Cell> cellList;
    Queue<Cell> queue;
    GameLoop gameLoop;
    int cellSize;


    public BreadthFirstSearch(Context context,GraphView graphView, GameLoop gameLoop, Cell sCell) {
        this.graphView = graphView;
        this.gameLoop = gameLoop;
        cellList = graphView.getCellList();
        cellSize = graphView.getCellSize();
    }

    public void bfs(Cell sCell) {
        this.sCell = sCell;
        for (int i = 1; i < cellList.size() - 1; i++) {
            uCell = cellList.get(i);
            uCell.setDistance(Integer.MAX_VALUE);
            uCell.setParent(null);
        }
        sCell.setDistance(0);
        sCell.setParent(null);
        queue = new LinkedList<>();
        queue.offer(sCell);

        while (!queue.isEmpty()) {
            uCell = queue.poll();
            //  for each neighbor belonging to uCell
            sleepThread();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int neighborX = (uCell.getX() - cellSize) + cellSize * i;
                    int neighborY = (uCell.getY() - cellSize) + cellSize * j;
                    if (neighborX > 0 || neighborY > 0 || neighborX <= graphView.getScreenWidth() ||
                            neighborY <= graphView.getScreenHeight()) {
                        //  iterate over cellList for specific cell
                        for (int k = 0; k < cellList.size(); k++) {
                            if (cellList.get(k).getX() == neighborX && cellList.get(k).getY() == neighborY
                                    && cellList.get(k).getPaint().getColor() == Color.LTGRAY) {
                                vCell = cellList.get(k);
                                vCell.setColor(Color.RED);
                                vCell.setDistance(uCell.getDistance() + 1);
                                vCell.setParent(uCell);
                                queue.offer(vCell);
                            }
                        }
                    }
                }
            }
            uCell.setColor(Color.GREEN);
        }
    }



    public void paintNeighbors(GraphView graphView, Cell sourceCell) {
        this.graphView = graphView;
        int size = graphView.getCellSize();
        // get nodes surrounding source cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int possibleX = (sourceCell.getX() - size) + size * i;
                int possibleY = (sourceCell.getY() - size) + size *j;
                if (possibleX > 0 || possibleY > 0 || possibleX <= graphView.getScreenWidth() ||
                possibleY <= graphView.getScreenHeight()) {
                    //  iterate over cellList for specific cell
                    paintSpecificCell(possibleX, possibleY);
                }
            }
        }
    }

    public void paintSpecificCell(int x, int y) {
        List<Cell> cellList = graphView.getCellList();
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i).getX() == x && cellList.get(i).getY() == y) {
                cellList.get(i).setColor(Color.GREEN);
            }
        }
    }

    public void sleepThread() {
        try {
            gameLoop.sleep(5);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}
