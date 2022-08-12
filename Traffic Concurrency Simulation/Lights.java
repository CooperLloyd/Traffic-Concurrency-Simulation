// This file defines the class for "Light"
//
// This code uses
//      class Synch, which defines the semaphores and variables
//                   needed for synchronizing the cars.


import java.awt.*;

public class Lights extends Thread{
    public Lights() {
        Synch.timeSim.threadStart();
        // status of lights 0 when both red, 1 when green westbound, 2 when green eastbound
    }  // end of the constructor for class "Light"

    //Lights thread starts here
    public void run(){
        for (int i=1; i<=20; i++) {
            // Release Eastbound cars
            Synch.lightStatus = 2;
            System.out.println("At time " + Synch.timeSim.curTime() + "The Eastbound light is now green" + ".\n");
            int eastGreenTime = Synch.timeSim.curTime(); //Time when light turns green
            int eastSleepCount = 0; // Keep track of how long program sleeps in while loop

            // While light still green
            while (Synch.timeSim.curTime() < eastGreenTime + Synch.LIGHT_GREEN_TIME) {
                if (Synch.eastBoundCarCount >= 0) {
                    Synch.eastQueue.release(); // Release car from queue
                    Synch.eastRelCount ++; // Increase Release count for each release
                    Synch.eastGreenMutex.release(); // Cross in order of arrival
                    Synch.timeSim.doSleep(1); // Leave gap between cars
                    eastSleepCount ++;
                }
                // If no car crossed then lock queue
                if(Synch.eastAqCount < Synch.eastRelCount){
                    Synch.eastQueue.acquire();
                    Synch.eastAqCount ++;
                }
            }
            // Sleep remainder of time remaining in light
            Synch.timeSim.doSleep(Synch.LIGHT_GREEN_TIME-eastSleepCount);

            // Set both lights red for 100
            Synch.lightStatus = 0;
            System.out.println("At time " + Synch.timeSim.curTime() + "Both Lights are now red" + ".\n");
            Synch.timeSim.doSleep(100);

            // Release Westbound cars
            // Set westbound light green for 200
            Synch.lightStatus = 1;
            System.out.println("At time " + Synch.timeSim.curTime() + "The Westbound light is now green" + ".\n");
            int westGreenTime = Synch.timeSim.curTime(); // Time when light turns green
            int westSleepCount = 0; // Keep track of how long program sleeps in while loop

            // While light still green
            while(Synch.timeSim.curTime() < westGreenTime + Synch.LIGHT_GREEN_TIME){
                if(Synch.westBoundCarCount >= 0){
                    Synch.westQueue.release(); // Release car from queue
                    Synch.westRelCount ++; // Increase Release count for each release
                    Synch.westGreenMutex.release(); // Cross in order of arrival
                    Synch.timeSim.doSleep(1); // Leave gap between cars
                    westSleepCount ++;
                }
                // If no car crossed then lock queue
                if(Synch.westAqCount < Synch.westRelCount){
                    Synch.westQueue.acquire();
                    Synch.westAqCount ++;
                }
            }
            // Sleep remainder of time remaining in light
            Synch.timeSim.doSleep(Synch.LIGHT_GREEN_TIME-westSleepCount);


            // Set both lights red for 100
            Synch.lightStatus = 0;
            System.out.println("At time " + Synch.timeSim.curTime() + "Both Lights are now red" + ".\n");
            Synch.timeSim.doSleep(100);
        }
        Synch.timeSim.threadEnd();
    }
}
