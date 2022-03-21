package websocket;

import dominionshared.dominiongame.IDominionGame;
import dominionshared.dominiongame.IGameClient;

public class GameClient implements IGameClient {

    private IClientMessageGenerator messageGenerator;

    private IDominionGame clientGame;

    public GameClient(IClientMessageGenerator generator) {
        this.messageGenerator = generator;
    }

    @Override
    public void registerGameClient(IDominionGame clientGame) {
        this.clientGame = clientGame;
    }

    @Override
    public void registerPlayer(String userName, String password) {
        //NO password, because this is already checked on the rest server side beforehand.
        messageGenerator.registerPlayerOnServer(userName, password);
    }

    @Override
    public void playerReady(int playerNumber) {
        messageGenerator.playerReady(playerNumber);
    }

    @Override
    public void handlePlayerRegistered(int playerNumber, String playerName) {
        if (clientGame.getPlayerOne().getName().equals(playerName)) {
            clientGame.getPlayerOne().setNumber(playerNumber);
            clientGame.getApplication().setPlayerNumber(playerNumber, playerName);
        }
        else {
            clientGame.getPlayerTwo().setNumber(playerNumber);
        }

    }

    @Override
    public void bothReady(String playerOneName, String playerTwoName) {
        if (clientGame.getPlayerOne().getName().equals(playerOneName)) {
            clientGame.getApplication().setOpponentName(clientGame.getPlayerOne().getNumber(), playerTwoName);
        }
        else if (clientGame.getPlayerOne().getName().equals(playerTwoName)) {
            clientGame.getApplication().setOpponentName(clientGame.getPlayerOne().getNumber(), playerOneName);
        }
        clientGame.getApplication().notifyStartGame(clientGame.getPlayerOne().getNumber());
    }

    @Override
    public void handleOpponentReady(int playerNumber) {
        if(clientGame.getPlayerTwo().getNumber() == playerNumber) {
            clientGame.getPlayerTwo().setReady(true);
        }
        else {
            clientGame.getPlayerOne().setReady(true);
        }
    }

}
