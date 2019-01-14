
public class RoundRobin {

    static int[] calcWaitingTime(int burstTime[], int quantum) {
        int[] waitingTime = new int[burstTime.length]; // Waiting Time
        int[] remainingBurstTime = new int[burstTime.length]; // Remaining Burst Time
        for (int i = 0; i < burstTime.length; i++)
        {
            remainingBurstTime[i] = burstTime[i]; // Copy all values from table burstTime to remainingBurstTime
        }
            int t = 0; // t is current time
            while (true)
            {
                boolean done = true;
                for (int y = 0; y < burstTime.length; y++)
                {
                    if (remainingBurstTime[y] > 0)
                    {
                        done = false;
                        if (remainingBurstTime[y] > quantum)
                        {
                            t += quantum;
                            remainingBurstTime[y] -= quantum;
                        }
                        else
                            {
                            t += remainingBurstTime[y];
                            waitingTime[y] = t - burstTime[y];
                            remainingBurstTime[y] = 0;
                            }
                    }
                }
                    // If all processes are done then break
                    if (done == true)
                    {
                        break;
                    }

            }
            return waitingTime;
    }

    // Method that calculates turn around time for all processes
    static int[] calcTurnAroundTime(int burstTime[], int waitingTime[])
    {
        int [] turnAroundTime = new int [burstTime.length];
        for(int i=0;i<burstTime.length;i++)
        {
            turnAroundTime[i]=burstTime[i]+waitingTime[i]; // Calculate turn around time
        }

        return turnAroundTime;
    }

    // Method that prints the results and calculates the average waiting and
    // turnaround times
    static void printAvgTimes(int burstTime[], int quantum)
    {
        int n = burstTime.length;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Find waiting time of all processes
        int[] waitingTime = calcWaitingTime(burstTime, quantum);

        // Find turn around time for all processes
        int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime);

        // Display processes along with all details
        System.out.println("Process " + " Burst Time " +
                " Waiting Time " + " Turnaround Time");
        System.out.println("=======  ==========  ============  ===============");
        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println(i + "\t\t" + burstTime[i] +"\t " +
                    waitingTime[i] +"\t\t " + turnAroundTime[i]);
        }

        System.out.println("\nAverage waiting time = " +
                (float)totalWaitingTime / (float)n);
        System.out.println("Average turnaround time = " +
                (float)totalTurnAroundTime / (float)n);
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String[] args)
    {
        // Burst time of processes. The array index is the process ID.
        int burstTime[] = {5, 15, 4, 3};

        // Time quantum
        int quantum = 3;

        printAvgTimes(burstTime, quantum);
    }
}