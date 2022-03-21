package dominionshared.communication.websockets;

import seabattleshared.communication.messaging.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageProcessor(IMessageProcessor processor);
}
