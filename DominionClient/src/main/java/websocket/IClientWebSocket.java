package websocket;

import dominionshared.communication.websockets.IWebSocket;

public interface IClientWebSocket extends IWebSocket {

    void send(Object object);

    void onWebSocketMessageReceived(String message, String sessionId);
}
