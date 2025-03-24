package com.maze.robot.finding_path;

import java.util.PriorityQueue;
import com.maze.graph.Graph;

public class DijkstraAlg extends GeneralPathFinding {
    public DijkstraAlg() {
        // compare nodes in PriorityQueue by cost of the path
        super.comparator = (pos1, pos2) -> {
            if (summaryCost.get(pos1) > summaryCost.get(pos2))
                return 1;
            else if (summaryCost.get(pos1) < summaryCost.get(pos2))
                return -1;

            return 0;
        };
    }

    /*public DijkstraAlg()
    {
        super.comparator = new SummCostComparator();
    }


    class SummCostComparator implements Comparator<Position<Integer>>
    {
        // compare nodes in PriorityQueue by cost of the path
        @Override
        public int compare(Position<Integer> pos1, Position<Integer> pos2) {

            if (summaryCost.get(pos1) > summaryCost.get(pos2))
                return 1;
            else if (summaryCost.get(pos1) < summaryCost.get(pos2))
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
