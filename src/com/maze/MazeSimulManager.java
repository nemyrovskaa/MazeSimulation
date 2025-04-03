package com.maze;

import java.util.HashMap;

import com.maze.event_timer.StepTimer;
import com.maze.maze_generation.*;
import com.maze.statistics.*;
import com.maze.robot.*;
import com.maze.robot.finding_path.*;

public class MazeSimulManager {
    private final StepTimer timerEvents;
    private final Statistics robotsStat;
    private Maze maze;

    public MazeSimulManager() {
        robotsStat = new Statistics() {
            @Override
            public void statisticsChangedData(HashMap<Robot, StatisticsData> robotsData) {
                printView();
            }
        };

        timerEvents = new StepTimer();
    }

    public void generateMaze(MazeType mazeType, int mazeWidth, int mazeHeight) {
        maze = switch(mazeType) {
            case DFS_MAZE -> new DFSMaze(mazeWidth, mazeHeight);
            case HUNT_KILL_MAZE -> new HuntAndKillMaze(mazeWidth, mazeHeight);
            case PRIMS_MAZE -> new PrimsMaze(mazeWidth, mazeHeight);
            default -> new DFSMaze(mazeWidth, mazeHeight);
        };

        maze.generate();
    }

    public void createRobot(Position<Integer> startPos, Position<Integer> goalPos, String name, RobotPathFindAlg alg) {
        Robot robot = switch (alg) {
            case DIJKSTRA_ROBOT_TYPE -> new Robot(startPos, goalPos, name, maze, new DijkstraAlg());
            case GREEDY_ROBOT_TYPE -> new Robot(startPos, goalPos, name, maze, new GreedyAlg());
            case ASTAR_ROBOT_TYPE -> new Robot(startPos, goalPos, name, maze, new AStar());
            default -> new Robot(startPos, goalPos, name, maze, new AStar());
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

    public void printView() {
        StringBuilder viewStr = new StringBuilder(maze.toString());

        int titleSize = viewStr.indexOf("\n", 0);
        for (Robot robot : robotsStat.getRobotsData().keySet()) { // iterate robots
            StatisticsData robotStat = robotsStat.getRobotsData().get(robot);
            for (int i = 0; i < robotStat.getPath().size(); i++) {
                Position<Integer> pos = robotStat.getPath().get(i);
                int strXPos = 4 * pos.x + 2;
                int strYPos = 2 * pos.y + 1;
                int strPos = titleSize + 1 + strYPos * (maze.getMazeWidth() * 4 + 1 + 1) + strXPos;

                viewStr.setCharAt(strPos, (i==0 ? Character.toUpperCase(robot.getName().charAt(0)) : Character.toLowerCase(robot.getName().charAt(0))));
            }
        }

        viewStr.append("Statistics:\n");
        for (Robot robot : robotsStat.getRobotsData().keySet()) { // iterate robots
            StatisticsData robotStat = robotsStat.getRobotsData().get(robot);
            viewStr.append("[Robot " + robotStat.getName() + "]\t");
            viewStr.append(robotStat.getStepsAmount() + (robotStat.getStepsAmount() == 1 ? " step\n" : " steps\n"));
        }

        System.out.println(viewStr);
    }
}
