import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

public class SimpleWebSocketClient extends WebSocketClient {
    private String lastMessage = ""; // Store the last received message
    private final CountDownLatch latch;

    public SimpleWebSocketClient(URI serverUri, CountDownLatch latch) {
        super(serverUri);
        this.latch = latch;
        res.send("WORKING");
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server.");
        latch.countDown(); // Signal that the connection is ready
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received: " + message);
        lastMessage = message; // Store the received message
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed. Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("Error: " + ex.getMessage());
    }

    // Method to retrieve the last received message
    public String getLastMessage() {
        return lastMessage;
    }
}
    res.send('Hello World!');
});
