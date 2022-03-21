package dominionserver;

import dominionshared.communication.websockets.IWebSocket;

public interface IServerWebSocket extends IWebSocket {

    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToOthers(String sessionId, Object object);

}
