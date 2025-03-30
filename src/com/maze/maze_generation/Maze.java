package com.maze.maze_generation;

import com.maze.graph.Graph;
import com.maze.robot.finding_path.Position;

import java.util.ArrayList;

public abstract class Maze implements Graph {

    protected final int mazeWidth;
    protected final int mazeHeight;
    protected ArrayList<ArrayList<Cell>> mazeGrid;

    public Maze(int mazeWidth, int mazeHeight) {
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;

        // filling maze with cells
        mazeGrid = new ArrayList<>(mazeHeight);
        for (int i = 0; i < mazeHeight; i++) {
            ArrayList<Cell> mazeGridRow = new ArrayList<>(mazeWidth);
            for (int j = 0; j < mazeWidth; j++) {
                mazeGridRow.add(new Cell(j, i));
            }
            mazeGrid.add(mazeGridRow);
        }

        // add adjacent cells for each cell in maze
        Cell currentCell;
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                currentCell = mazeGrid.get(i).get(j);

                if(i != 0)
                    currentCell.addNeighbour(mazeGrid.get(i-1).get(j));
                if(j != mazeWidth-1)
                    currentCell.addNeighbour(mazeGrid.get(i).get(j+1));
                if (i != mazeHeight-1)
                    currentCell.addNeighbour(mazeGrid.get(i+1).get(j));
                if (j != 0)
                    currentCell.addNeighbour(mazeGrid.get(i).get(j-1));
            }
        }
    }

    public abstract void generate();

    public int getMazeWidth() {
        return mazeWidth;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public Cell getCell(int xPos, int yPos) {
        if ((xPos >= 0 && xPos < mazeWidth) && (yPos >= 0 && yPos < mazeHeight))
            return mazeGrid.get(yPos).get(xPos);
        return  null;
    }

    @Override
    public ArrayList<Position<Integer>> getNeighbours(Position<Integer> pos) {
        ArrayList<Position<Integer>> retNeighbours = new ArrayList<>();

        for (Cell neighbour : getCell(pos.x, pos.y).getNeighbours().keySet())
            retNeighbours.add(neighbour.getPosition());

        return retNeighbours;
    }

    @Override
    public int getCellCost(Position<Integer> pos) {
        return 1;   // so far all cells have the same cost
    }

    @Override
    public String toString() {
        String retStr = "+" + "---+".repeat(mazeWidth) + "\n";

        for (int i = 0; i < mazeHeight; i++) {
            retStr += "|";
            for (int j = 0; j < mazeWidth; j++)
                retStr += (j == mazeWidth - 1 ? "   |" : (getCell(j, i).hasWall(getCell(j + 1, i))) ? "   |" : "    ");
            retStr += "\n";

            retStr += "+";
            for (int j = 0; j < mazeWidth; j++)
                retStr += (i == mazeHeight - 1 ? "---+" : (getCell(j, i).hasWall(getCell(j, i + 1))) ? "---+" : "   +");
            retStr += "\n";
        }
        return retStr;
    }
}
