package websocket.messageHandlers;

import dominionshared.communication.messaging.IMessageHandler;
import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.dominiongame.IGameClient;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game) {
        IGameClient gc = (IGameClient)game;

        switch(simpleType) {
            case "PlayerHasRegisteredMessage":
                return new PlayerHasRegisteredMessageHandler(gc);
            case "PlayerReadyMessage":
                return new OpponentReadyMessageHandler(gc);
            case "BothReadyMessage":
                return new BothReadyMessageHandler(gc);
            default:
                return null;
        }

    }
}
