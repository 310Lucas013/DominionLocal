package dominionshared.dominiongame;

public interface IGameClient {
    void registerGameClient(IDominionGame clientGame);

    void registerPlayer(String userName, String password);

    void handlePlayerRegistered(int playerNumber, String playerName);

    void handleOpponentReady(int playerNumber);

    void bothReady(String playerOneName, String playerTwoName);

    void playerReady(int playerNumber);
}
