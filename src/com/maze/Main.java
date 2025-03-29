package com.maze;

import com.maze.maze_generation.DFSMaze;

public class Main {
    public static void main(String[] args) {
        // MazeSimulManager mazeSimulManager = new MazeSimulManager();
        // mazeSimulManager.startSimulation();

        DFSMaze dfsMaze = new DFSMaze(10, 7);
        dfsMaze.generate();
        System.out.println(dfsMaze);
    }
}