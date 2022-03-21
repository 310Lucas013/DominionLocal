package websocket;

import dominionshared.communication.messaging.IMessageHandler;
import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.communication.messaging.MessageProcessorBase;
import dominionshared.dominiongame.IGameClient;

public class ClientMessageProcessor extends MessageProcessorBase implements IClientMessageProcessor {

    private IGameClient gameClient;

    public ClientMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }

    public void setGameClient(IGameClient gameClient)
    {
        this.gameClient = gameClient;
    }

    public void handleDisconnect(String sessionId)
     {
         //Do nothing
     }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, gameClient);
        handler.handleMessage(data, sessionId);
    }
}
