package websocket.messageHandlers;

import dominionshared.communication.messages.PlayerHasRegisteredMessage;
import dominionshared.communication.messaging.MessageHandlerBase;
import dominionshared.dominiongame.IGameClient;

public class PlayerHasRegisteredMessageHandler extends MessageHandlerBase<PlayerHasRegisteredMessage> {

    private IGameClient gc;

    public PlayerHasRegisteredMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(PlayerHasRegisteredMessage message, String sessionId) {
        gc.handlePlayerRegistered(message.getPlayerNumber(), message.getPlayerName());
    }
}
