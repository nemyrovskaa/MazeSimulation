package com.maze;

import com.maze.robot.RobotPathFindAlg;
import com.maze.robot.finding_path.Position;
import com.maze.maze_generation.MazeType;

public class Main {
    public static void main(String[] args) {
        MazeSimulManager mazeSimulManager = new MazeSimulManager();
        mazeSimulManager.generateMaze(MazeType.HUNT_KILL_MAZE, 15, 10);
        mazeSimulManager.createRobot(new Position<>(0, 0), new Position<>(14, 5), "Ivan", RobotPathFindAlg.ASTAR_ROBOT_TYPE);
        mazeSimulManager.createRobot(new Position<>(0, 0), new Position<>(7, 9), "Mila", RobotPathFindAlg.DIJKSTRA_ROBOT_TYPE);
        mazeSimulManager.startSimulation();
    }
}