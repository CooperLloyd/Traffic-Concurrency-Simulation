// This file contains the main method for a traffic simulation.

// This code uses
//      class Car, which defines the behaviour of a car.
//      class Synch, which defines the semaphores and variables
//                   needed for synchronizing the cars.

// --- Use of Synch.timeSim ---
// Synch.timeSim is code provided by Prof. Blostein to allow your simulation
// to keep accurate track of simulated time.  (As discussed in class, the standard
// java "thread.sleep()" is quite inaccurate: if one thread starts "sleep(10)" at the
// same time that another thread starts "sleep(20)", the inaccurate timing can easily
// cause the "sleep(20)" thread to wake up first.)
//
// For the timeSim code to work properly, timeSim needs to know how many threads there
// are.  When all the threads have finished their computation, then timeSim can advance
// the simulated time.
// As you look at the code for Car.java, notice that the constructor for Car calls 
// Synch.timeSim.threadStart().  Also, the end of the run() method calls Synch.timeSim.threadEnd().
// If you create other kinds of threads (e.g. to operate the traffic light), those threads should 
// make similar calls to timeSim, in the constructor and at the end of the run() method.



public class MainMethod {
  public static void main (String argv[]) {

    Synch.timeSim = new TimeSim();  // timeSim provides accurate "sleep"
          // timing (in contrast to thread.sleep, which is highly variable)
    Synch.debug=0;  // We don't want debug output from TimeSim.Java

    // Initialize the semaphores and variables that are needed for thread
    // synchronization.
    // *** No semaphores are used in the car code, yet.  You will add some.
    // *** This declaration of semaphore "mutex" is here to remind you how
    // *** to declare semaphores.

    // Equal to the length of each green light
    Synch.LIGHT_GREEN_TIME = 10;

    // Initialize semaphores to 0 as locks at lights
    Synch.eastQueue = new Semaphore(0, true);
    Synch.westQueue = new Semaphore(0, true);

    //Mutex(s) for counter and queue priority protection
    Synch.westGreenMutex = new Semaphore(1, true);
    Synch.eastGreenMutex = new Semaphore(1, true);
    Synch.westAqMutex = new Semaphore(1, true);
    Synch.eastAqMutex = new Semaphore(1, true);
    Synch.westCarMutex = new Semaphore(1, true);
    Synch.eastCarMutex = new Semaphore(1, true);



    // initialize car counts at each light and Sem trackers to 0
    Synch.eastBoundCarCount=0;
    Synch.westBoundCarCount=0;

    Synch.eastAqCount=0;
    Synch.westAqCount=0;
    Synch.eastRelCount=0;
    Synch.westRelCount=0;

    // set both lights to red
    Synch.lightStatus=0;


    // Create one instance of light thread for East and West lights
    Lights L;
    L = new Lights();
    L.start();

    // Create several instances of Car threads
    Car C;  // C can hold an instance of class Car

    for (int i=1; i<=20; i++) {
      C = new Car(i);
      C.start();
    }

    System.out.println("This is main speaking");
  }  // end of "main"
}  // end of "MainMethod"
