package ss.calculator;

import java.io.IOException;

/**
 * A server for the streaming calculator. Classes that implement this interface offer the calculator service
 * via a given (or assigned) TCP port.
 * Assume that the start method is only called once and that the stop method is only called once after start.
 */
public interface CalculatorServer {
    /**
     * Start the server on the given port in a separate thread
     * @param port the desired port (or 0 to take any available TCP port)
     * @throws IOException if the port was not available or there was some other I/O problem
     */
    void start(int port) throws IOException;

    /**
     * Get the port that the server is running on
     * @return the port number
     */
    int getPort();

    /**
     * Stop the server and return after the thread has stopped
     */
    void stop();
}
