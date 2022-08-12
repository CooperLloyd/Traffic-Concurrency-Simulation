// This file defines class "Synch".  This class contains all the semaphores
// and variables needed to coordinate the car simulation.

public class Synch {

    public static TimeSim timeSim; // this class provides an accurate "sleep"
                                   // function.

// *** Declare your variables and semaphores here.  You might want to have two semaphores, one for use
// *** by waiting eastbound cars, and the other for use by waiting westbound cars.
// *** You might also want to have counters for the number of cars waiting in each direction.  You
// *** also need some variable to represent whether the light is green westbound, green eastbound, or
// *** red in both directions.
// *** If you use counters, you need to have a mutex semaphore to protect access to the counters.
//
// Which variables/semaphores you need depends on how you decide to code your solution.

    // Semaphores for both directions of traffic
    public static Semaphore eastQueue;
    public static Semaphore westQueue;

    public static Semaphore westGreenMutex;
    public static Semaphore eastGreenMutex;

    public static Semaphore eastAqMutex;
    public static Semaphore westAqMutex;

    public static Semaphore eastCarMutex;
    public static Semaphore westCarMutex;



    // status of lights 0 when both red, 1 when green westbound, 2 when green eastbound
    public static int lightStatus;

    // counter's for cars waiting at each light
    public static int eastBoundCarCount;
    public static int westBoundCarCount;

    // Maintain balance between Acquire and release calls
    public static int eastRelCount;
    public static int westRelCount;
    public static int eastAqCount;
    public static int westAqCount;



    //Length of time Lights remain green for
    public static int LIGHT_GREEN_TIME;

    public static int debug;  // set this to 1 or 2 to get extra output for debugging TimeSim.java

}
