package websocket.messageHandlers;

import dominionshared.communication.messages.PlayerReadyMessage;
import dominionshared.communication.messaging.MessageHandlerBase;
import dominionshared.dominiongame.IGameClient;

public class OpponentReadyMessageHandler extends MessageHandlerBase<PlayerReadyMessage> {

    private IGameClient gc;

    public OpponentReadyMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(PlayerReadyMessage message, String sessionId) {
        gc.handleOpponentReady(message.getPlayerNr());
    }
}
