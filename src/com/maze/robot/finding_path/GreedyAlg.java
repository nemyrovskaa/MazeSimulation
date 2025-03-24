package com.maze.robot.finding_path;

import java.util.PriorityQueue;
import com.maze.graph.Graph;

public class GreedyAlg extends GeneralPathFinding {
    public GreedyAlg() {
        // compare nodes in PriorityQueue by distance
        super.comparator = (pos1, pos2) -> {
            if (heuristicCost(pos1, goal) > heuristicCost(pos2, goal))
                return 1;
            else if (heuristicCost(pos1, goal) < heuristicCost(pos2, goal))
                return -1;

            return 0;
        };
    }

    // finding the shortest distance (between node and goal in greedy algorithm)
    private int heuristicCost(Position<Integer> pos1, Position<Integer> pos2) {
        return (pos2.x - pos1.x)*(pos2.x - pos1.x) + (pos2.y - pos1.y)*(pos2.y - pos1.y);
    }

    /*public GreedyAlg()
    {
        super.comparator = new HeuristicCostComparator();
    }

    class HeuristicCostComparator implements Comparator<Position<Integer>>
    {
        // finding the shortest distance (between node and goal in greedy algorithm)
        private int heuristicCost(Position<Integer> pos1, Position<Integer> pos2)
        {
            return (pos2.x - pos1.x)*(pos2.x - pos1.x) + (pos2.y - pos1.y)*(pos2.y - pos1.y);
        }

        // compare nodes in PriorityQueue by distance
        @Override
        public int compare(Position<Integer> pos1, Position<Integer> pos2)
        {
            if (heuristicCost(pos1, goal) > heuristicCost(pos2, goal))
                return 1;
            else if (heuristicCost(pos1, goal) < heuristicCost(pos2, goal))
                return -1;

            return 0;
        }
    }*/

    // overridden abstract method
    @Override
    void changeContainersData(Graph graph, PriorityQueue<Position<Integer>> explored, Position<Integer> current, Position<Integer> next) {
        if(!cameFrom.containsKey(next)) {
            cameFrom.put(next, current);
            explored.add(next);
        }
    }
}
