package dominionshared.communication.websockets;

import seabattleshared.models.Ship;
import seabattleshared.models.ShotType;

import java.util.List;

public interface IServerMessageGenerator {

        void notifyPlayerHasRegistered(String sessionId, int playerNr, String name);

    void notifyOpponentHasRegistered(String sessionId, int playerNr, String name);

    void notifyPlayerReady(String sessionId, int playerNr);

    void notifyShipsChanged(String sessionId, int playerNr, List<Ship> ships);

    void notifyPlayerFireShot(String sessionId, int playerNr, int posX, int posY, ShotType shotResult, List<Ship> ships);

    void notifyOpponentFireShot(String sessionId, int playerNr, int posX, int posY, ShotType shotResult, List<Ship> ships);

    void notifyBothReady(String sessionId, String playerOneName, String playerTwoName);

    void notifyBothReadyOpponent(String sessionId, String playerOneName, String playerTwoName);
}
