package com.example.lawson.androidsummery.thread.diy;

/**
 * Created by Ian.Lu on 2017/1/16.
 * Project : AndroidSummary
 */

public abstract class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {

    private int priority;

    public PriorityRunnable(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public abstract void doBusiness();

    @Override
    public void run() {
        doBusiness();
    }

    @Override
    public int compareTo(PriorityRunnable o) {
        int result = 0;
        if (this.getPriority() > o.getPriority()) {
            result = 1;
        } else if (this.getPriority() < o.getPriority()) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }
}
