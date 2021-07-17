public class StopTimer {

    private static long startTime = 0;
    private static long stopTime = 0;
    private static long pausedTime;
    private static boolean running = false;

    /**
     * Start the stoptimer.
     */
    public static void start() {
        startTime = System.currentTimeMillis();
        pausedTime = 0;
        running = true;
    }

    /**
     * Stop the stoptimer.
     */
    public static void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }

    /*
    /**
     * Pause the stoptimer.
     */

    /*
    public void pause() {
        if (running) {
            pausedTime = System.currentTimeMillis();
            stop();
        }
    }

     */


    /*

     /**
     * Resume the stoptimer.
     */
    /*
    public void resume() {
        if (!running) {
            long duration = System.currentTimeMillis() - pausedTime;
            startTime += duration;
            this.running = true;
        }
    }

     */



    /**
     * Get the elapsed time.
     *
     * @return the elapsed time
     */
    public static long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
}

