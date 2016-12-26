package cpu;

import Algorithm.FCFS;
import Algorithm.MyAlgorithm;
import Algorithm.EDF;
import Algorithm.RR;
import Algorithm.SJN;
import Algorithm.SRT;
import Items.Job;
import Items.Queue;

/**
 * @author Islam Kamilov
 * This class is responsible for initiating, selecting the algorithm 
 * and doing the step work for the simulation
 */
public class Simulation {
 
    private static MyAlgorithm myAlgorithm; // object used for polymorphism 
    public static int Time;   // current time of the simulation
    public static String AlgorithmType = "FCFS";  // default algroithm type
    public static int Quantum = 2;  // quantum time for round robin algorithm
    public static boolean Finished = false; // show that the simulation is finished
    public static boolean Stoped = true;  // show that the simulation is stoped
    
    /**
     * reset the simulation
     */
    public static void reset()
    {
       Time = 0;  // reset the simulation time
       Finished = false;  // simulation is not finished
    }
    
    /**
     * @return the current ready queue of the working algorithm
     */
    public static Queue getReadyQueue()
    {
        return myAlgorithm.getReadyQueue();
    }
    
    /**
     * let the selected algorithm finish a step
     * @return the current job worked by the algorithm
     */
    public static Job workStep()
    {
        Job job;
        if(Time == 0) {selectAlgorithm();}  // select and init the algorithm
        job = myAlgorithm.nextStep(Time); 
        if(myAlgorithm.isFinished()){Finished = true;} 
        return job;
    }
    
    /**
     * select and initiate the selected algorithm
     */
    private static void selectAlgorithm()
    {
        if(AlgorithmType.equals("FCFS")) {
            myAlgorithm = new FCFS(MainQueue.get());
        }  // first come first served
        else if(AlgorithmType.equals("SJN")) {
            myAlgorithm = new SJN(MainQueue.get());
        } // shortest job first
        else if(AlgorithmType.equals("SRT")) {
            myAlgorithm = new SRT(MainQueue.get());
        } // shortest time remaining first
        else if(AlgorithmType.equals("EDF")) {
            myAlgorithm = new EDF(MainQueue.get());
        } // priority preemptive
        else if(AlgorithmType.equals("RR")) {
            myAlgorithm = new RR(MainQueue.get() , Quantum);
        }  // Round Robin
    }
}
