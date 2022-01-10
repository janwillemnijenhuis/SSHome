package ss.week7.chat.client;

/**
 * interface for listening to incoming messages from the server
 */
public interface ChatListener {
    void messageReceived(String from, String message);
}
