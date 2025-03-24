package com.maze.robot;

import com.maze.robot.finding_path.PathFinding;
import com.maze.graph.Graph;
import com.maze.robot.finding_path.Position;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
public class Robot extends Observable implements Observer { // TODO fix deprecated Observer/Observable
    private Position<Integer> startPos;
    private Position<Integer> goalPos;
    private String name;
    private Graph graph;
    private PathFinding alg;
    private Position<Integer> currentPos;
    private Vector<Position<Integer>> robotPath;
    private int steps = 0;

    /**
     * @param start start position of the Robot
     * @param goal  goal position of the Robot
     * @param name  Robots' name
     * @param graph representation of graph to find path for Robot
     * @param alg   path finding algorithm
     */
    public Robot(Position<Integer> start, Position<Integer> goal, String name, Graph graph, PathFinding alg) {
        this.startPos = start;
        this.goalPos = goal;
        this.name = name;
        this.graph = graph;
        this.alg = alg;
        this.currentPos = start;
        this.robotPath = alg.findPath(graph, start, goal);
    }

    public String getName() {
        return this.name;
    }

    public Position<Integer> getStartCell() {
        return this.startPos;
    }

    public Position<Integer> getEndCell() {
        return this.goalPos;
    }

    public Position<Integer> getRobotPos() {
        return currentPos;
    }

    private void step() {
        this.currentPos = robotPath.elementAt(steps);
        this.steps++;

        notifyRobotObservers();
    }

    public void notifyRobotObservers() {
        setChanged();
        notifyObservers();
    }

    // make step when timer notify
    @Override
    public void update(Observable o, Object arg) {
        step();

        if(currentPos == goalPos) {    // when robot gets to finish point
            o.deleteObserver(this); // unsubscribe it from step timer
            this.deleteObservers();    // unsubscribe statistics from it
        }
    }
}
