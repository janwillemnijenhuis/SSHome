package ss.week7.chat.server;

public interface ChatServer {
    /**
     * Starts the server, using the port provided in the constructor.
     */
    void start();

    /**
     * Returns the port of the server.
     * @return port.
     */
    int getPort();

    /**
     * Stops the server.
     */
    void stop();
}
