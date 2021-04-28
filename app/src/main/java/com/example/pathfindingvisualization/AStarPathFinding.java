package com.example.pathfindingvisualization;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class AStarPathFinding {
    private int cellSize, diagonalMoveCost;
    private long runTime;
    private double kValue;
    private Cell startCell, endCell, par;
    private boolean diagonal, running, noPath, complete, trig;
    ArrayList<Cell> openList, closedList, borderList, pathList;

    public AStarPathFinding(int cellSize) {
        this.cellSize = cellSize;
        //
        diagonalMoveCost = (int) (Math.sqrt(2 * (Math.pow(cellSize, 2))));
        kValue = Math.PI / 2;
        diagonal = true;
        trig = false;
        running = false;
        complete  = false;

        borderList = new ArrayList<Cell>();
        openList = new ArrayList<Cell>();
        closedList = new ArrayList<Cell>();
        pathList = new ArrayList<Cell>();

    }

    public Cell getStartCell() {
        return startCell;
    }
    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }
 }
