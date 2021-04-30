package com.example.pathfindingvisualization;
import android.content.Context;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class AStarPathFinding {
    private GraphView graphView;
    private int cellSize, diagonalMoveCost;
    private long runTime;
    private double kValue;
    private Cell startCell, endCell, par;
    private boolean diagonal, running, noPath, complete, trig;
    ArrayList<Cell> openList, closedList, borderList, pathList;
    Context context;

    public AStarPathFinding() {

    }

    public AStarPathFinding(GraphView graphView,int cellSize, Cell startCell, Cell endCell) {
        this.graphView = graphView;
        this.cellSize = cellSize;
        this.startCell = startCell;
        this.endCell = endCell;
        context = getGraphView().getContext();
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

    public void start(Cell startCell, Cell endCell) {
        running = true;
        setStartCell(startCell);
        getStartCell().setG(0);
        setEndCell(endCell);

        addClosed(startCell);

        long startTime = System.currentTimeMillis();
        findPath(getStartCell());

    }

    public void findPath(Cell parentCell) {
        Cell openCell = null;

        //  uses sin and cos to get points around parent
        for (int i = 0; i < 4; i++) {
            int possibleX = (int) Math.round(parentCell.getX() + (-cellSize * Math.cos(kValue * i)));
            int possibleY = (int) Math.round(parentCell.getY() + (-cellSize * Math.sin(kValue * i)));

            calculateNodeValues(possibleX, possibleY, openCell, parentCell);
        }
    }

    public void calculateNodeValues(int possibleX, int possibleY, Cell openCell, Cell parentCell) {
        if (possibleX < 0 | possibleY < 0 | possibleX >= graphView.getScreenWidth() | possibleY >= graphView.getScreenHeight()) {
            return;
        }

        if (searchBorder(possibleX, possibleY) != -1 | searchClosed(possibleX, possibleY) != -1
                | searchOpen(possibleX, possibleY) != -1) {
            return;
        }

        // Find an open cell with the avaliable X and Y coords
        // coordinates
        openCell = new Cell(context, possibleX, possibleY, cellSize);

        // Set the parent of the open node
        openCell.setParent(parentCell);

        // Calculating G cost
        // Cost to move from parent node to one open node (x
        // and
        // y
        // separately)
        int GxMoveCost = openCell.getX() - parentCell.getX();
        int GyMoveCost = openCell.getY() - parentCell.getY();
        int gCost = parentCell.getG();

        if (GxMoveCost != 0 && GyMoveCost != 0) {
            gCost += diagonalMoveCost;
        } else {
            gCost += cellSize;
        }
        openCell.setG(gCost);

        // Calculating H Cost
        int HxDiff = Math.abs(endCell.getX() - endCell.getX());
        int HyDiff = Math.abs(endCell.getY() - endCell.getY());
        int hCost = HxDiff + HyDiff;
        openCell.setH(hCost);

        // Calculating F Cost
        int fCost = gCost + hCost;
        openCell.setF(fCost);

        addOpen(openCell);
    }



    public int searchBorder(int xSearch, int ySearch) {
        int Location = -1;

        for (int i = 0; i < borderList.size(); i++) {
            if (borderList.get(i).getX() == xSearch && borderList.get(i).getY() == ySearch) {
                Location = i;
                break;
            }
        }
        return Location;
    }

    public int searchClosed(int xSearch, int ySearch) {
        int Location = -1;

        for (int i = 0; i < closedList.size(); i++) {
            if (closedList.get(i).getX() == xSearch && closedList.get(i).getY() == ySearch) {
                Location = i;
                break;
            }
        }
        return Location;
    }

    public int searchOpen(int xSearch, int ySearch) {
        int Location = -1;

        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).getX() == xSearch && openList.get(i).getY() == ySearch) {
                Location = i;
                break;
            }
        }
        return Location;
    }

    public void addBorder(Cell cell) {
        if (borderList.size() == 0) {
            borderList.add(cell);
        } else if (!checkBorderDuplicate(cell)){
            borderList.add(cell);
        }
    }

    public void addClosed(Cell cell) {
        if (closedList.size() == 0) {
            closedList.add(cell);
        } else if (!checkClosedDuplicate(cell)){
            closedList.add(cell);
        }
    }

    public void addOpen(Cell cell) {
        if (openList.size() == 0) {
            openList.add(cell);
        } else if (!checkOpenDuplicate(cell)){
            openList.add(cell);
        }
    }

    public void removeOpen(Cell cell) {
        for (int i = 0; i < openList.size(); i++) {
            if (cell.getX() == openList.get(i).getX() && cell.getY() == openList.get(i).getY()) {
                openList.remove(i);
            }
        }
    }

    public boolean checkClosedDuplicate(Cell cell) {
        for (int i = 0; i < closedList.size(); i++) {
            if (cell.getX() == closedList.get(i).getX() && cell.getY() == closedList.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOpenDuplicate(Cell cell) {
        for (int i = 0; i < openList.size(); i++) {
            if (cell.getX() == openList.get(i).getX() && cell.getY() == openList.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBorderDuplicate(Cell cell) {
        for (int i = 0; i < borderList.size(); i++) {
            if (cell.getX() == borderList.get(i).getX() && cell.getY() == borderList.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public Cell getStartCell() {
        return startCell;
    }
    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }
    public void setEndCell(Cell endCellCell) {
        this.endCell = endCell;
    }

    public GraphView getGraphView() {
        return graphView;
    }
    public void setGraphView(GraphView graphView) {
        this.graphView = graphView;
    }
 }
