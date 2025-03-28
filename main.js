const http = require("http");
const WebSocket = require("ws");

// Create an HTTP server
const server = http.createServer();
const wss = new WebSocket.Server({ server });

const PORT = process.env.PORT || 5001; // Use Heroku's assigned port or 5001 locally

wss.on("connection", (ws) => {
    console.log("Client connected");

    ws.on("message", (message) => {
        console.log("Received: " + message);
        ws.send(message); // Echo message back to client

    ws.on("playerIGN", (playerIGN) => {
        console.log("Received IGN: " + playerIGN);
        ws.send(playerIGN); // Echo message back to client
    });

    ws.on("close", () => {
        console.log("Client disconnected");
    });
});

// Start server
server.listen(PORT, () => {
    console.log(`WebSocket server running on port ${PORT}`);
});

