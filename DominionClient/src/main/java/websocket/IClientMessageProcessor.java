package websocket;


import dominionshared.communication.messaging.IMessageProcessor;
import dominionshared.dominiongame.IGameClient;

public interface IClientMessageProcessor extends IMessageProcessor {

    void setGameClient(IGameClient gameClient);
}
