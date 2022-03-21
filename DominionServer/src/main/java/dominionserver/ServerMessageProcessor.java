package dominionserver;

import seabattleshared.communication.messaging.IMessageHandler;
import seabattleshared.communication.messaging.IMessageHandlerFactory;
import seabattleshared.communication.messaging.MessageProcessorBase;
import seabattleshared.seabattlegame.ISeaBattleGame;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    private ISeaBattleGame game;

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    public void registerGame(ISeaBattleGame game) {
        this.game = game;
    }

    public ISeaBattleGame getGame() {
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
