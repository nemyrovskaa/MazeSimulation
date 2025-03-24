package com.maze.robot.finding_path;

import java.util.*;
import com.maze.graph.Graph;

public abstract class GeneralPathFinding implements PathFinding {
    protected Map<Position<Integer>, Position<Integer>> cameFrom; // contains next node of graph as key and previous as value
    protected Map<Position<Integer>, Integer> summaryCost;        // contains node as key and minimal summary cost of path to this node as a key
    protected Comparator<Position<Integer>> comparator;           // special compare for PriorityQueue
    protected Position<Integer> start;                            // start node
    protected Position<Integer> goal;                             // goal node

    // abstract method (different for every Path Finding algorithm)
    abstract void changeContainersData(Graph graph, PriorityQueue<Position<Integer>> explored, Position<Integer> current, Position<Integer> next);

    // general Path Finding method; returns Path = vector of Positions(x, y) from start+1 to goal-1
    public Vector<Position<Integer>> findPath(Graph graph, Position<Integer> start, Position<Integer> goal) {
        this.start = start;
        this.goal = goal;

        Position<Integer> current;
        Position<Integer> previous;

        cameFrom = new HashMap<>();
        summaryCost = new HashMap<>();
        PriorityQueue<Position<Integer>> explored = new PriorityQueue<>(comparator);
        Vector<Position<Integer>> path = new Vector<>();

        summaryCost.put(start, graph.getCellCost(start));
        explored.add(start);

        while(!explored.isEmpty()) {
            current = explored.remove();

            if(current.equals(goal))
                break;

            for(Position<Integer> neighbour : graph.getNeighbours(current)) {
                // use different clauses in different path finding algorithms
                changeContainersData(graph, explored, current, neighbour);
            }
        }

        // recover path using cameFrom
        if(cameFrom.containsKey(goal)) {
            previous = goal;

            while(previous != start) {
                path.add(previous);
                previous = cameFrom.get(previous);
            }
        }

        // reverse path to make proper order (from start to goal)
        Collections.reverse(path);

        return path;
    }
}
