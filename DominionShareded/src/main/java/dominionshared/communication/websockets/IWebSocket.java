package dominionshared.communication.websockets;

import dominionshared.communication.messaging.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageProcessor(IMessageProcessor processor);
}
