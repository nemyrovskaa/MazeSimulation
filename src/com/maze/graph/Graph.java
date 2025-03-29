package com.maze.graph;

import java.util.ArrayList;
import com.maze.robot.finding_path.Position;

public interface Graph {
    ArrayList<Position<Integer>> getNeighbours(Position<Integer> pos);
    int getCellCost(Position<Integer> pos);
}
