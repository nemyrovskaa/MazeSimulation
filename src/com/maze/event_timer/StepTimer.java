package com.maze.event_timer;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class StepTimer extends Observable { // TODO fix deprecated Observable
    private Timer timer;
    private TimerTask timerTask;

    public void startTimer() {
        timer = new Timer(false); // true to interrupt the timer thread

        timerTask = new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();

                // stop timer if no observers (Robots)
                if(countObservers() == 0)
                    stopTimer();
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
