package dominionshared.dominiongame;

import dominionshared.communication.websockets.IServerMessageGenerator;
import dominionshared.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DominionGame implements IDominionGame {

    private static final Logger log = LoggerFactory.getLogger(DominionGame.class);
    private Player playerOne;
    private Player playerTwo;
    private IDominionGUI application;
    private boolean singlePlayerMode;
    private IGameClient gameClient;
    private IServerMessageGenerator messageGenerator;
    private boolean continueRegister;

    public DominionGame() {
    }

    public DominionGame(IServerMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public IServerMessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    @Override
    public IGameClient getGameClient() {
        return gameClient;
    }

    @Override
    public void setGameClient(IGameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public Player getPlayerOne() {
        return playerOne;
    }

    @Override
    public Player getPlayerTwo() {
        return playerTwo;
    }

    @Override
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    @Override
    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    @Override
    public IDominionGUI getApplication() {
        return application;
    }

    @Override
    public void registerPlayer(String name, String password, IDominionGUI application, boolean singlePlayerMode) {

    }

    @Override
    public void notifyWhenReady(int playerNr) {

    }

    @Override
    public void notifyWhenReadyLogic(int playerNr) {

    }

    @Override
    public void startNewGame(int playerNr) {

    }

    @Override
    public void updateGUI(int playerNr) {

    }
}
