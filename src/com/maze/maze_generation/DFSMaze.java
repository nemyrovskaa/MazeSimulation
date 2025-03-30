package com.maze.maze_generation;

import java.util.*;

public class DFSMaze extends Maze {
    public DFSMaze(int mazeWidth, int mazeHeight) {
        super(mazeWidth, mazeHeight);
    }

    @Override
    public void generate() {
        Random rand = new Random();
        Cell currentCell = getCell(rand.nextInt(mazeWidth), rand.nextInt(mazeHeight));
        LinkedList<Cell> queue = new LinkedList<>();

        while (true) {
            currentCell.setIsVisited();
            queue.add(currentCell);

            Cell unvisitedNeighbour = currentCell.getRandomUnvisited();
            while (unvisitedNeighbour == null) {
                currentCell = queue.pollFirst();
                if (currentCell == null)
                    return;
                unvisitedNeighbour = currentCell.getRandomUnvisited();
            }

            currentCell.breakNeighbouringWall(unvisitedNeighbour);
            currentCell = unvisitedNeighbour;
        }
    }

    @Override
    public String toString() {
        return "DFS Maze:\n" + super.toString();
    }
}
