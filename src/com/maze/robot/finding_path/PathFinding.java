package com.maze.robot.finding_path;

import com.maze.graph.Graph;
import java.util.Vector;

public interface PathFinding {
    Vector<Position<Integer>> findPath(Graph graph, Position<Integer> start, Position<Integer> goal);
}
