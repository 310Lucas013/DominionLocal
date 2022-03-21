package dominionserver;

import dominionshared.communication.messaging.IMessageHandler;
import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.communication.messaging.MessageProcessorBase;
import dominionshared.dominiongame.IDominionGame;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    private IDominionGame game;

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    public void registerGame(IDominionGame game) {
        this.game = game;
    }

    public IDominionGame getGame() {
        return game;
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getGame());
        handler.handleMessage(data, sessionId);
    }

    public void handleDisconnect(String sessionId) {
        //TODO check if needed.
//        getGame().processClientDisconnect(sessionId);
    }
}
