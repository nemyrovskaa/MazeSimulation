package com.maze.maze_generation;

import java.util.Random;

public class HuntAndKillMaze extends Maze {
    public HuntAndKillMaze(int mazeWidth, int mazeHeight) {
        super(mazeWidth, mazeHeight);
    }

    @Override
    public void generate() {
        Random rand = new Random();
        Cell currentCell = getCell(rand.nextInt(mazeWidth), rand.nextInt(mazeHeight));
        int unvisitedRow = 0;

        while (currentCell != null) {
            currentCell.setIsVisited();

            Cell unvisitedNeighbour = currentCell.getRandomUnvisited();
            if (unvisitedNeighbour != null) {
                currentCell.breakNeighbouringWall(unvisitedNeighbour);
                currentCell = unvisitedNeighbour;
                continue;
            }

            // else if no unvisited neighbours, enter hunt mode
            currentCell = null;
            for (int i = unvisitedRow; i < mazeHeight; i++) {
                for (int j = 0; j < mazeWidth; j++) {
                    Cell visitedNeighbour = getCell(j, i).getRandomVisited();
                    if (!getCell(j, i).getVisited() && visitedNeighbour != null) {
                        currentCell = getCell(j, i);
                        currentCell.breakNeighbouringWall(visitedNeighbour);
                        unvisitedRow = i;   // remember row with unvisited cells
                        break;
                    }
                }
                if (currentCell != null)
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Kill-and-Hunt Maze:\n" + super.toString();
    }
}
