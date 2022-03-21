package dominiongui;

import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.dominiongame.DominionGame;
import dominionshared.dominiongame.IDominionGUI;
import dominionshared.dominiongame.IDominionGame;
import dominionshared.dominiongame.IGameClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import websocket.*;
import websocket.messageHandlers.ClientMessageHandlerFactory;

public class DominionApplication extends Application implements IDominionGUI {

    private static final Logger log = LoggerFactory.getLogger(DominionApplication.class);

    // Opponent's name
    private String opponentName;

    // Player's number (to be determined by the sea battle game)
    int playerNr = 0;

    // Player's name
    private String playerName = null;

    // Player that may fire a shot (player 0 or player 1)
    private int playerTurn = 0;

    // Sea battle game
    private static IDominionGame game;

    // Flag to indicate whether game is in single-player or multiplayer mode
    private boolean singlePlayerMode = true;

    // Flag to indicate that the game is endend
    private boolean gameEnded = false;


    @Override
    public void start(Stage primaryStage) throws Exception {

        log.info("Dominion started");



    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        game = new DominionGame();

        //client classes
        IClientWebSocket clientSocket = new ClientWebSocket();
        IClientMessageGenerator clientGenerator = new ClientMessageGenerator(clientSocket);

        IGameClient gameClient = new GameClient(clientGenerator);
        IMessageHandlerFactory clientFactory = new ClientMessageHandlerFactory();

        IClientMessageProcessor clientProcessor = new ClientMessageProcessor(clientFactory);
        clientSocket.setMessageProcessor(clientProcessor);

        clientSocket.start();
        clientProcessor.setGameClient(gameClient);

        game.setGameClient(gameClient);
        game.getGameClient().registerGameClient(game);

        launch(args);
    }

    /**
     * Set player number.
     * @param playerNr identification of player
     * @param name player's name
     */
    @Override
    public void setPlayerNumber(int playerNr, String name) {
        // Check identification of player
        if (!this.playerName.equals(name)) {
            showMessage("ERROR: Wrong player name method setPlayerNumber()");
            return;
        }
        this.playerNr = playerNr;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                labelPlayerName.setText(playerName + "\'s grid");
//                labelYourName.setDisable(true);
//                textFieldPlayerName.setDisable(true);
//                labelYourPassword.setDisable(true);
//                passwordFieldPlayerPassword.setDisable(true);
//                radioSinglePlayer.setDisable(true);
//                radioMultiPlayer.setDisable(true);
//                buttonRegisterPlayer.setDisable(true);
//                labelHorizontalVertical.setDisable(false);
//                radioHorizontal.setDisable(false);
//                radioVertical.setDisable(false);
//                buttonPlaceAllShips.setDisable(false);
//                buttonRemoveAllShips.setDisable(false);
//                buttonReadyToPlay.setDisable(false);
//                buttonPlaceAircraftCarrier.setDisable(false);
//                buttonPlaceBattleShip.setDisable(false);
//                buttonPlaceCruiser.setDisable(false);
//                buttonPlaceSubmarine.setDisable(false);
//                buttonPlaceMineSweeper.setDisable(false);
//                buttonRemoveShip.setDisable(false);
            }
        });
        showMessage("Player " + name + " registered");
    }

    /**
     * Set the name of the opponent.
     * The opponent's name will be shown above the target area.
     * @param playerNr identification of player
     * @param name opponent's name
     */
    @Override
    public void setOpponentName(int playerNr, String name) {
        // Check identification of player
        if (playerNr != this.playerNr) {
            showMessage("ERROR: Wrong player number method setOpponentName()");
            return;
        }
        showMessage("Your opponent is " + name);
        opponentName = name;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                labelOpponentName.setText(opponentName + "\'s grid");
            }
        });
    }

    /**
     * Notification that the game has started.
     * @param playerNr identification of player
     */
    @Override
    public void notifyStartGame(int playerNr) {
        // Check identification of player
        if (playerNr != this.playerNr) {
            showMessage("ERROR: Wrong player number method notifyStartGame()");
            return;
        }

        // Set playing mode and disable placing/removing of ships
//        playingMode = true;
//        labelHorizontalVertical.setDisable(true);
//        radioHorizontal.setDisable(true);
//        radioVertical.setDisable(true);
//        buttonPlaceAllShips.setDisable(true);
//        buttonRemoveAllShips.setDisable(true);
//        buttonReadyToPlay.setDisable(true);
//        buttonStartNewGame.setDisable(true);
//        buttonPlaceAircraftCarrier.setDisable(true);
//        buttonPlaceBattleShip.setDisable(true);
//        buttonPlaceCruiser.setDisable(true);
//        buttonPlaceSubmarine.setDisable(true);
//        buttonPlaceMineSweeper.setDisable(true);
//        buttonRemoveShip.setDisable(true);
        showMessage("Start playing by selecting a square in " + opponentName + "\'s grid");
    }

    /**
     * Show error message.
     * @param playerNr identification of player
     * @param errorMessage error message
     */
    @Override
    public void showErrorMessage(int playerNr, String errorMessage) {
        // Show the error message as an alert message
        showMessage(errorMessage);
    }

    /**
     * Notify that the player is ready to start the game.
     */
    private void notifyWhenReady() {
        // Notify that the player is ready is start the game.
        game.notifyWhenReady(playerNr);
    }

    /**
     * Start a new game.
     */
    private void startNewGame() {
        // The player wants to start a new game.
        game.startNewGame(playerNr);
        gameEnded = false;
//        playingMode = false;
//        labelYourName.setDisable(false);
//        textFieldPlayerName.setDisable(false);
//        labelYourPassword.setDisable(false);
//        passwordFieldPlayerPassword.setDisable(false);
//        radioSinglePlayer.setDisable(false);
//        radioMultiPlayer.setDisable(false);
//        buttonRegisterPlayer.setDisable(false);
    }

    /**
     * Show an alert message.
     * The message will disappear when the user presses ok.
     */
    private void showMessage(final String message) {
        // Use Platform.runLater() to ensure that code concerning
        // the Alert message is executed by the JavaFX Application Thread
        log.debug("Show Message for {} - {}", playerName, message);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sea battle");
                alert.setHeaderText("Message for " + playerName);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

    /**
     * Method to switch player's turn.
     * This method is synchronized because switchTurn() may be
     * called by the Java FX Application thread or by another thread
     * handling communication with the game server.
     */
    public synchronized void switchTurn() {
        playerTurn = 1 - playerTurn;
    }

}
