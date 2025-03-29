package com.maze.maze_generation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.maze.robot.finding_path.Position;

public class Cell {
    private Position<Integer> position;
    private boolean isVisited;
    private Hashtable<Cell, Integer> neighbours;    // hash table with neighbour cells and presence of the wall (weight)
    private static final int NEIGHBOUR_NUM = 4;

    public Cell(int xPos, int yPos) {
        this.position = new Position<>(xPos, yPos);
        this.isVisited = false;
        this.neighbours = new Hashtable<>(NEIGHBOUR_NUM);
    }

    public void addNeighbour(Cell neighbourCell) {
        neighbours.put(neighbourCell, 1);
        neighbourCell.getNeighbours().put(this, 1);
    }

    public void breakNeighbouringWall(Cell neighbourCell) {
        neighbours.replace(neighbourCell, 0);
        neighbourCell.getNeighbours().replace(this, 0);
    }

    public boolean hasWall(Cell neighbourCell) {
        return neighbours.get(neighbourCell) == 1;
    }

    public Cell getRandomUnvisited() {
        ArrayList<Cell> unvisitedNeighbours = new ArrayList<>();
        for (Cell neighbour : neighbours.keySet())
            if (!neighbour.isVisited)
                unvisitedNeighbours.add(neighbour);

        if (unvisitedNeighbours.isEmpty())
            return null;

        Random random = new Random();
        return unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
    }

    public void setIsVisited() {
        isVisited = true;
    }

    public Position<Integer> getPosition() {
        return position;
    }

    public boolean getVisited() {
        return isVisited;
    }

    public Hashtable<Cell, Integer> getNeighbours() {
        return neighbours;
    }

}
