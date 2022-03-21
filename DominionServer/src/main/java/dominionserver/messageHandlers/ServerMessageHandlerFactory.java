package dominionserver.messageHandlers;

import dominionshared.communication.messaging.IMessageHandler;
import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.dominiongame.IDominionGame;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game)
    {
        IDominionGame igame = (IDominionGame) game;
        switch(simpleType)
        {
            case "RegisterPlayerMessage":
                return new RegisterPlayerMessageHandler(igame);
            case "PlayerReadyMessage":
                return new PlayerReadyMessageHandler(igame);
            default:
                return null;
        }
    }
}
