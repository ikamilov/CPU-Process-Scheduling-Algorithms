package Algorithm;

import Items.Job;
import Items.Queue;

/**
 * @author Islam Kamilov
 * "First Come First served" CPU scheduling algorithm works like its name
 * the first the job arrive to the ready queue the first it will be processed 
 * by the CPU.
 */
public class FCFS extends MyAlgorithm{
     
    /**
     * pass the work queue to super class to initialize lists
     * @param workQueue queue of lists to be worked on
     */
    public FCFS(Queue workQueue)
    {  
        super(workQueue); 
    }
    
    /**
     * shows what happen in a single step when using this algorithm
     * @param simulationTime current time of this simulation
     * @return job the CPU was working on
     */
    @Override
    public Job nextStep (int simulationTime)
    {
        updateReadyQueue(simulationTime);  // add newly arrived jobs to the ready queue
        if(!busy) // if CPU is not processing a job ( FCFS is non-preemptive algorithm)
        {
            if(readyQueue.isEmpty()) {return null;} 
            busy = true; 
            setCurrentJob(); // move the first job in the ready queue to be the current working job
        }
        return workInCPU(simulationTime);
    }
    
    /**
     * work the current job in the CPU for one simulation time step
     * @param simulationTime current time of the simulation
     * @return the current job the CPU is working on
     */
    @Override
    protected Job workInCPU(int simulationTime)
    {
        currentJob.jobWorked(simulationTime); 
        if(currentJob.getRemainTime() == 0) {busy = false;} // if job is finished make CPU not busy
        return currentJob; 
    }
    
   
}
