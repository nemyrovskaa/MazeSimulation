package com.maze.statistics;

import java.util.Vector;
import com.maze.robot.finding_path.Position;

public class StatisticsData {
    protected String name;
    protected Vector<Position<Integer>> passedPath;

    StatisticsData(String name) {
        this.name = name;
        passedPath = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public int getStepsAmount() {
        return passedPath.size() - 1;
    }

    public Vector<Position<Integer>> getPath() {
        return passedPath;
    }
}
