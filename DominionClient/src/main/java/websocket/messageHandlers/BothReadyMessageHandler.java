package websocket.messageHandlers;

import dominionshared.communication.messages.BothReadyMessage;
import dominionshared.communication.messaging.MessageHandlerBase;
import dominionshared.dominiongame.IGameClient;

public class BothReadyMessageHandler  extends MessageHandlerBase<BothReadyMessage> {

    private IGameClient gc;

    public BothReadyMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(BothReadyMessage message, String sessionId) {
        gc.bothReady(message.getPlayerOneName(), message.getPlayerTwoName());
    }
}
