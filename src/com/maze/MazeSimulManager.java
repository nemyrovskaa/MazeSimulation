package com.maze;

import java.util.HashMap;

import com.maze.event_timer.StepTimer;
import com.maze.statistics.*;
import com.maze.robot.*;
import com.maze.robot.finding_path.*;
import com.maze.maze_generation.Maze;

public class MazeSimulManager {
    private final Statistics robotsStat;
    private final StepTimer timerEvents;
    private Maze newMaze;

    public MazeSimulManager() {
        robotsStat = new Statistics() {
            @Override
            public void statisticsChangedData(HashMap<Integer, StatisticsData> robotsData) {
                // TODO update view
            }
        };

        timerEvents = new StepTimer();
    }

    public void createRobot(Position<Integer> startPos, Position<Integer> goalPos, String name, RobotPathFindAlg alg) {
        Robot robot = switch (alg) {
            case DIJKSTRA_ROBOT_TYPE -> new Robot(startPos, goalPos, name, newMaze, new DijkstraAlg());
            case GREEDY_ROBOT_TYPE -> new Robot(startPos, goalPos, name, newMaze, new GreedyAlg());
            case ASTAR_ROBOT_TYPE -> new Robot(startPos, goalPos, name, newMaze, new AStar());
            default -> new Robot(startPos, goalPos, name, newMaze, new AStar());
        };

        robot.addObserver(robotsStat);
        timerEvents.addObserver(robot);
    }

    public void startSimulation() {
        timerEvents.startTimer();
    }

    public void stopSimulation() {
        timerEvents.stopTimer();
    }

    public void printMaze() {

    }

    public void generateMaze() {

    }

    public void saveMaze(String filename) {

    }

    public void openMaze(String filename) {

    }
}
