package dominionshared.communication.websockets;

public interface IServerMessageGenerator {

    void notifyPlayerHasRegistered(String sessionId, int playerNr, String name);

    void notifyOpponentHasRegistered(String sessionId, int playerNr, String name);

    void notifyPlayerReady(String sessionId, int playerNr);

    void notifyBothReady(String sessionId, String playerOneName, String playerTwoName);

    void notifyBothReadyOpponent(String sessionId, String playerOneName, String playerTwoName);
}
