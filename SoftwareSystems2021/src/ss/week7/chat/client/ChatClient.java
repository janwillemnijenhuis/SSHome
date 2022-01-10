package ss.week7.chat.client;

import java.net.InetAddress;

/**
 * interface of the networking module of the client
 */
public interface ChatClient {
    /**
     * connects to a server
     * @param address of the server
     * @param port to connect at
     * @return true if connected, false otherwise
     */
    boolean connect(InetAddress address, int port);

    /**
     * closes the connection to the server
     */
    void close();

    /**
     * send the username to the server
     * @param username
     * @return true if success, false otherwise
     */
    boolean sendUsername(String username);

    /**
     * send a message to the server
     * @param message
     * @return true if success, false otherwise
     */
    boolean sendMessage(String message);

    void addChatListener();

    void removeChatListener();
}
