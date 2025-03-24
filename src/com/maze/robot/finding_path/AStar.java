package com.maze.robot.finding_path;

import java.util.PriorityQueue;
import com.maze.graph.Graph;

public class AStar extends GeneralPathFinding {
    public AStar() {
        // compare nodes in PriorityQueue by cost of the path + distance

        /*super.comparator = new Comparator<Position<Integer>>() {
            @Override
            public int compare(Position<Integer> o1, Position<Integer> o2) {
                return 0;         ||
            }                     \/
        }*/

        super.comparator = (pos1, pos2) -> {
            if(summaryCost.get(pos1) + heuristicCost(pos1, goal) > summaryCost.get(pos2) + heuristicCost(pos2, goal))
                return 1;
            else if(summaryCost.get(pos1)+ heuristicCost(pos1, goal) < summaryCost.get(pos2) + heuristicCost(pos2, goal))
                return -1;

            return 0;
        };
    }

    // finding the shortest distance (between node and goal in A* algorithm)
    private int heuristicCost(Position<Integer> pos1, Position<Integer> pos2) {
        return (pos2.x - pos1.x)*(pos2.x - pos1.x) + (pos2.y - pos1.y)*(pos2.y - pos1.y);
    }

    /*public AStar()
    {
        super.comparator = new CostComparator();
    }


    class CostComparator implements Comparator<Position<Integer>>
    {
        // finding the shortest distance (between node and goal in A* algorithm)
        private int heuristicCost(Position<Integer> pos1, Position<Integer> pos2)
        {
            return (pos2.x - pos1.x)*(pos2.x - pos1.x) + (pos2.y - pos1.y)*(pos2.y - pos1.y);
        }

        // compare nodes in PriorityQueue by cost of the path + distance
        @Override
        public int compare(Position<Integer> pos1, Position<Integer> pos2) {

            if(summaryCost.get(pos1) + heuristicCost(pos1, goal) > summaryCost.get(pos2) + heuristicCost(pos2, goal))
                return 1;
            else if(summaryCost.get(pos1)+ heuristicCost(pos1, goal) < summaryCost.get(pos2) + heuristicCost(pos2, goal))
                return -1;

            return 0;
        }
    }*/


    // overridden abstract method
    @Override
    void changeContainersData(Graph graph, PriorityQueue<Position<Integer>> explored, Position<Integer> current, Position<Integer> next) {
        if (!summaryCost.containsKey(next) || summaryCost.get(current) + graph.getCellCost(next) < summaryCost.get(next)) {
            summaryCost.put(next, summaryCost.get(current) + graph.getCellCost(next));
            cameFrom.put(next, current);
            explored.add(next);
        }
    }
}
