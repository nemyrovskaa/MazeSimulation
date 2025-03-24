package com.maze.graph;

import java.util.Vector;
import com.maze.robot.finding_path.Position;

public interface Graph {
    Vector<Position<Integer>> getNeighbours(Position<Integer> pos);
    int getCellCost(Position<Integer> pos);
}
