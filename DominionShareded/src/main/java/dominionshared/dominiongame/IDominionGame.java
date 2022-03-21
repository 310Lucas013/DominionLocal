package dominionshared.dominiongame;

import dominionshared.communication.websockets.IServerMessageGenerator;
import dominionshared.models.Player;

public interface IDominionGame {

    IServerMessageGenerator getMessageGenerator();

    IGameClient getGameClient();

    void setGameClient(IGameClient gameClient);

    Player getPlayerOne();

    Player getPlayerTwo();

    void setPlayerOne(Player playerOne);

    void setPlayerTwo(Player playerTwo);

    IDominionGUI getApplication();

    /**
     * Register player with given name and password. The player number will be
     * set at the player's application by a call-back of method setPlayerNumber().
     * @param name              Name of the player to be registered
     * @param password          Password of the player to be registered
     * @param application       Reference to application of player
     * @param singlePlayerMode  Single-player (true) or multi-player (false) mode
     * @throws IllegalArgumentException when:
     * name is null or the empty,
     * password is null or empty,
     * application is null,
     * number of players exceeds one in single-player mode,
     * number of players exceeds two in multi-player mode or
     * name is already registered.
     */
    public void registerPlayer(String name, String password, IDominionGUI application, boolean singlePlayerMode);

    /**
     * Notify that the player is ready to play the game, i.e., all ships have
     * been placed. When not all ships have been placed, the message
     * "Not all ships have been placed!" will be sent to the player's application
     * by a method call of showErrorMessage().
     * @param playerNr identification of player who is ready to play the game
     */
    public void notifyWhenReady(int playerNr);

    void notifyWhenReadyLogic(int playerNr);

    /**
     * Start a new game. Remove all ships and unregister the player.
     * The ocean area of the player's application will be kept up-to-date by
     * method calls of showSquarePlayer() and the target area of the opponent's
     * application will be kept up-to-date by method calls of showSquareOpponent().
     * In single-player mode also the ships of the opponent will be removed and
     * the target area of the player's application will be updated by method
     * calls of showSquareOpponent().
     * @param playerNr identification of player who starts a new game
     */
    public void startNewGame(int playerNr);

    public void updateGUI(int playerNr);

}
