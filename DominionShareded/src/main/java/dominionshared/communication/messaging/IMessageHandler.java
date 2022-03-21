package dominionshared.communication.messaging;

public interface IMessageHandler {

    void handleMessage(String message, String sessionId);
}