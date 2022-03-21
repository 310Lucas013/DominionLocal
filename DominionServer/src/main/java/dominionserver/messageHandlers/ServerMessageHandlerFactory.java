package dominionserver.messageHandlers;

import seabattleshared.communication.messaging.IMessageHandler;
import seabattleshared.communication.messaging.IMessageHandlerFactory;
import seabattleshared.seabattlegame.ISeaBattleGame;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game)
    {
        ISeaBattleGame igame = (ISeaBattleGame)game;
        switch(simpleType)
        {
            case "RegisterPlayerMessage":
                return new RegisterPlayerMessageHandler(igame);
            case "PlayerReadyMessage":
                return new PlayerReadyMessageHandler(igame);
            case "FireShotMessage":
                return new PlayerFireShotMessageHandler(igame);
            case "PlaceShipMessage":
                return new PlaceShipMessageHandler(igame);
            case "PlaceShipsAutoMessage":
                return new PlaceShipsAutoMessageHandler(igame);
            case "RemoveShipMessage":
                return new RemoveShipMessageHandler(igame);
            default:
                return null;
        }
    }
}
