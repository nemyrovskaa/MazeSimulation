package com.maze.maze_generation;

import java.util.HashSet;
import java.util.Random;

public class PrimsMaze extends Maze {
    public PrimsMaze(int mazeWidth, int mazeHeight) {
        super(mazeWidth, mazeHeight);
    }

    @Override
    public void generate() {
        Random rand = new Random();
        HashSet<Cell> frontier = new HashSet<>();

        Cell frontierCell = getCell(rand.nextInt(mazeWidth), rand.nextInt(mazeHeight));
        while (frontierCell != null) {
            frontierCell.setIsVisited();

            for (Cell neighbour : frontierCell.getNeighbours().keySet())
                if (!neighbour.getVisited())
                    frontier.add(neighbour);

            if (frontierCell.getRandomVisited() != null)
                frontierCell.breakNeighbouringWall(frontierCell.getRandomVisited());

            if (frontier.isEmpty())
                return;

            frontierCell = frontier.stream().skip(rand.nextInt(frontier.size())).findFirst().orElse(null);
            frontier.remove(frontierCell);
        }
    }

    @Override
    public String toString() {
        return "Prim's Maze:\n" + super.toString();
    }
}
