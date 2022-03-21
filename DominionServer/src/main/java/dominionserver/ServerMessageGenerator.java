package dominionserver;

import seabattleshared.communication.messages.*;
import seabattleshared.communication.websockets.IServerMessageGenerator;
import seabattleshared.models.Ship;
import seabattleshared.models.ShotType;

import java.util.List;

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
    public void notifyShipsChanged(String sessionId, int playerNr, List<Ship> ships) {
        serverSocket.sendTo(sessionId, new ChangedShipsMessage(playerNr, ships));
    }

    @Override
    public void notifyPlayerFireShot(String sessionId, int playerNr, int posX, int posY, ShotType shotResult, List<Ship> ships) {
        serverSocket.sendTo(sessionId, new PlayerShotMessage(playerNr, posX, posY, shotResult, ships));
    }

    @Override
    public void notifyOpponentFireShot(String sessionId, int playerNr, int posX, int posY, ShotType shotResult, List<Ship> ships) {
        serverSocket.sendToOthers(sessionId, new OpponentShotMessage(playerNr, posX, posY, shotResult, ships));
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
