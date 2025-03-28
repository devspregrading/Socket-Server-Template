const http = require("http");
const WebSocket = require("ws");

// Create an HTTP server
const server = http.createServer();
const wss = new WebSocket.Server({ server });

const PORT = process.env.PORT || 19252; // Heroku dynamically sets the port

wss.on("connection", (ws) => {
    console.log("Client connected");

    ws.on("message", (message) => {
        console.log("Received: " + message);
        ws.send(message); // Echo message back to client
    });

    ws.on("close", () => {
        console.log("Client disconnected");
    });

    ws.on("error", (err) => {
        console.error("WebSocket error: ", err);
    });
});

// Start server
server.listen(PORT, () => {
    console.log(`WebSocket server running on port ${PORT}`);
});

