package com.maze.maze_generation;

import com.maze.graph.Graph;
import com.maze.robot.finding_path.Position;

import java.util.Vector;

public class Maze implements Graph {

    public Maze(int width, int height) {
    }

    @Override
    public Vector<Position<Integer>> getNeighbours(Position<Integer> pos) {
        return null;
    }

    @Override
    public int getCellCost(Position<Integer> pos) {
        return 0;
    }
}
