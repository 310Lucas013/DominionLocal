package dominionserver;

import dominionshared.communication.messages.*;
import dominionshared.communication.websockets.IServerMessageGenerator;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebSocket serverSocket;

    public ServerMessageGenerator(IServerWebSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void notifyPlayerHasRegistered(String sessionId, int playerNr, String name) {
        serverSocket.sendTo(sessionId, new PlayerHasRegisteredMessage(playerNr, name));
    }

    @Override
    public void notifyOpponentHasRegistered(String sessionId, int playerNr, String name) {
        serverSocket.sendToOthers(sessionId, new PlayerHasRegisteredMessage(playerNr, name));
    }

    @Override
    public void notifyPlayerReady(String sessionId, int playerNr) {
        serverSocket.sendTo(sessionId, new PlayerReadyMessage(playerNr));
    }

    @Override
    public void notifyBothReady(String sessionId, String playerOneName, String playerTwoName) {
        serverSocket.sendTo(sessionId, new BothReadyMessage(playerOneName, playerTwoName));
    }

    @Override
    public void notifyBothReadyOpponent(String sessionId, String playerOneName, String playerTwoName) {
        serverSocket.sendToOthers(sessionId, new BothReadyMessage(playerOneName, playerTwoName));
    }
}
