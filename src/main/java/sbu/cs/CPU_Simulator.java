package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed tasks arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        private long processingTime;
        private String ID;
        public Task(String ID, long processingTime) {

            this.ID=ID;
            this.processingTime=processingTime;
        }

        public long getProcessingTime() {
            return processingTime;
        }

        public String getID() {
            return ID;
        }

        /*
                        Simulate running a task by utilizing the sleep method for the duration of
                        the task's processingTime. The processing time is given in milliseconds.
                    */

        @Override
        public void run() {

            try {
                Thread.sleep(processingTime);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }



        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();

        int repeat = tasks.size();

        for (int i = 0; i < repeat; i++) {
            Task shortest = tasks.get(0);
            for (Task g : tasks) {
                if (g.getProcessingTime() < shortest.getProcessingTime()) {
                    shortest = g;

                }
            }

            Thread T = new Thread(shortest);
            T.start();

            executedTasks.add(shortest.getID());

            for (int g =0 ; g<tasks.size(); g++){
                if(shortest.getProcessingTime()==tasks.get(g).getProcessingTime()){
                    tasks.remove(g);
                    break;
                }
            }
        }

        return executedTasks;
    }

    public static void main(String[] args) {
    }
}
