import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

public class SimpleWebSocketClient extends WebSocketClient {
    private String lastMessage = ""; // Store received message
    private final CountDownLatch latch;

    public SimpleWebSocketClient(URI serverUri, CountDownLatch latch) {
        super(serverUri);
        this.latch = latch;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to WebSocket server.");
        latch.countDown(); // Signal connection ready
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        lastMessage = message; // Store the latest message
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed. Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

