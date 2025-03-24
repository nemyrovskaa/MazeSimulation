package com.maze.statistics;

import java.util.*;
import com.maze.robot.Robot;

public abstract class Statistics implements Observer {  // TODO fix deprecated Observer/Observable
    // contains hash of Robot as a key and data for it as a value
    HashMap<Integer, StatisticsData> robotsData = new HashMap<>(); //TODO concurrency hash map ?

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Robot) {
            Robot temp = (Robot) o;
            if(!robotsData.containsKey(temp.hashCode()))
                robotsData.put(temp.hashCode(), new StatisticsData(temp.getName()));

            robotsData.get(temp.hashCode()).passedPath.addElement(temp.getRobotPos());
            statisticsChangedData(robotsData);
        }
    }

    protected abstract void statisticsChangedData(HashMap<Integer, StatisticsData> robotsData);
}
