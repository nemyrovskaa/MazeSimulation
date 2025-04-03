package com.maze.statistics;

import java.util.*;
import com.maze.robot.Robot;

public abstract class Statistics implements Observer {  // TODO fix deprecated Observer/Observable
    // contains hash of Robot as a key and data for it as a value
    private HashMap<Robot, StatisticsData> robotsData; //TODO concurrency hash map ?

    public Statistics() {
        robotsData = new HashMap<>();
    }
    protected abstract void statisticsChangedData(HashMap<Robot, StatisticsData> robotsData);

    public HashMap<Robot, StatisticsData> getRobotsData() {
        return robotsData;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Robot) {
            Robot robot = (Robot) o;
            if(!robotsData.containsKey(robot))
                robotsData.put(robot, new StatisticsData(robot.getName()));

            robotsData.get(robot).passedPath.addElement(robot.getRobotPos());
            statisticsChangedData(robotsData);
        }
    }

}
