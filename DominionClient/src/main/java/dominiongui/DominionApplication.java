package dominiongui;

import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.dominiongame.DominionGame;
import dominionshared.dominiongame.IDominionGUI;
import dominionshared.dominiongame.IDominionGame;
import dominionshared.dominiongame.IGameClient;
import dominionshared.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.css.CssMetaData;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import websocket.*;
import websocket.messageHandlers.ClientMessageHandlerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DominionApplication extends Application implements IDominionGUI {

    private List<Card> allKingdomCards;
    private List<Card> pickedKingdomCards;
    private List<Pile> boardPiles;

    private final int numberOfKingdomCards = 10;

    private static final Logger log = LoggerFactory.getLogger(DominionApplication.class);

    // Constants to define size of GUI elements
    private final int BORDERSIZE = 8; // Size of borders in pixels
    private final int AREAWIDTH = 1200; // Width of area in pixels
    private final int AREAHEIGHT = AREAWIDTH / 2; // Height of area in pixels
    private final int BUTTONWIDTH = 150; // Width of button
    private final int BUTTONHEIGHT = 25;

    // Opponent's name
    private String opponentName;

    // Label for opponent's name
    private Label labelOpponentName;

    // Player's number (to be determined by the sea battle game)
    int playerNr = 0;

    // Player's name
    private String playerName = null;

    // Player that may fire a shot (player 0 or player 1)
    private int playerTurn = 0;

    // Label for player's name
    private Label labelPlayerName;

    // Text field to set player's name
    private Label labelYourName;
    private TextField textFieldPlayerName;

    // Sea battle game
    private static IDominionGame game;

    // Flag to indicate whether game is in single-player or multiplayer mode
    private boolean singlePlayerMode = true;

    // Radio buttons to indicate whether game is in single-player or multiplayer mode
    private RadioButton radioSinglePlayer;
    private RadioButton radioMultiPlayer;

    // Flag to indicate whether the game is in playing mode
    private boolean playingMode = false;

    // Flag to indicate that the game is ended
    private boolean gameEnded = false;

    // Buttons to register player, start the game and perform other actions
    private Button buttonRegisterPlayer;
    private Button buttonReadyToPlay;
    private Button buttonStartNewGame;

    private Label costLabel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cards setup
        setUpKingdomCards();
        pickRandomKingdomCards();
        buildPiles();

        // GUI setup
        // Define grid pane
        GridPane grid;
        grid = new GridPane();
        grid.setHgap(BORDERSIZE);
        grid.setVgap(BORDERSIZE);
        grid.setPadding(new Insets(BORDERSIZE,BORDERSIZE,BORDERSIZE,BORDERSIZE));

        // For debug purposes
        // Make de grid lines visible
        // grid.setGridLinesVisible(true);

        // Create the scene and add the grid pane
        Group root = new Group();
        Scene scene = new Scene(root, AREAWIDTH+BUTTONWIDTH+3*BORDERSIZE, AREAHEIGHT+2*BORDERSIZE+65);
        root.getChildren().add(grid);

        // Label for player's name
        playerName = "";

        // Text field to set the player's name
        labelYourName = new Label("Your name:");
        grid.add(labelYourName,1,2,1,2);
        textFieldPlayerName = new TextField();
        textFieldPlayerName.setMinWidth(BUTTONWIDTH);
        textFieldPlayerName.setMinHeight(BUTTONHEIGHT);
        textFieldPlayerName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerName = textFieldPlayerName.getText();
                labelPlayerName.setText(playerName + "\'s grid");
            }
        });
        grid.add(textFieldPlayerName,1,4,1,2);

        // Radio buttons to choose between single-player and multi-player mode
        radioSinglePlayer = new RadioButton("single-player mode");
        Tooltip tooltipSinglePlayer = new Tooltip("Play game in single-player mode");
        radioSinglePlayer.setTooltip(tooltipSinglePlayer);
        radioSinglePlayer.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                singlePlayerMode = true;
            }
        });
        radioMultiPlayer = new RadioButton("multi-player mode");
        Tooltip tooltipMultiPlayer = new Tooltip("Play game in multi-player mode");
        radioMultiPlayer.setTooltip(tooltipMultiPlayer);
        radioMultiPlayer.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                singlePlayerMode = false;
            }
        });
        ToggleGroup tgSingleMultiPlayer = new ToggleGroup();
        radioSinglePlayer.setToggleGroup(tgSingleMultiPlayer);
        radioMultiPlayer.setToggleGroup(tgSingleMultiPlayer);
        radioSinglePlayer.setSelected(true);
        grid.add(radioSinglePlayer,1,10,1,2);
        grid.add(radioMultiPlayer,1,12,1,2);

        // Button to register the player
        buttonRegisterPlayer = new Button("Register");
        buttonRegisterPlayer.setMinWidth(BUTTONWIDTH);
        Tooltip tooltipRegisterParticipant =
                new Tooltip("Press this button to register as player");
        buttonRegisterPlayer.setTooltip(tooltipRegisterParticipant);
        buttonRegisterPlayer.setOnAction(
                (EventHandler) event -> {
                    try {
                        registerPlayer();
                    } catch (Exception e) {
                        log.error("Register Player error: {}", e.getMessage());
                    }
                });
        grid.add(buttonRegisterPlayer,1,14,1,2);

        // Button to notify that the player is ready to start playing
        buttonReadyToPlay = new Button("Ready to play");
        buttonReadyToPlay.setMinWidth(BUTTONWIDTH);
        Tooltip tooltipReadyToPlay =
                new Tooltip("Press this button when you are ready to play");
        buttonReadyToPlay.setTooltip(tooltipReadyToPlay);
        buttonReadyToPlay.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                notifyWhenReady();
            }
        });
        buttonReadyToPlay.setDisable(false);
        grid.add(buttonReadyToPlay,1,20,1,2);

        // Button to start a new game
        buttonStartNewGame = new Button("Start new game");
        buttonStartNewGame.setMinWidth(BUTTONWIDTH);
        Tooltip tooltipStartNewGame =
                new Tooltip("Press this button to start a new game");
        buttonStartNewGame.setTooltip(tooltipStartNewGame);
        buttonStartNewGame.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                startNewGame();
            }
        });
        buttonStartNewGame.setDisable(false);
        grid.add(buttonStartNewGame,1,22,1,2);

        Label deckName = new Label("Deck");
        grid.add(deckName, 4, 1, 1, 1);

        ObservableList<Card> cards = FXCollections.observableArrayList(pickedKingdomCards);
        ListView<Card> cardListView = new ListView<>(cards);
        grid.add(cardListView, 4, 3, 50, 15);

        Label buyPileLabel = new Label("Buy Pile");
        grid.add(buyPileLabel, 55, 1, 1, 1);

        costLabel = new Label("Cost = ");
        grid.add(costLabel, 55, 25, 1, 1);

        ObservableList<Pile> piles = FXCollections.observableArrayList(boardPiles);
        ListView<Pile> pileListView = new ListView<>(piles);
        pileListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pile>() {
            @Override
            public void changed(ObservableValue<? extends Pile> observable, Pile oldValue, Pile newValue) {
                costLabel.setText("Cost = " + newValue.getCard().getCost());
            }
        });
        grid.add(pileListView, 55, 3, 50, 15);



        // Set font for all labeled objects
        for (Node n : grid.getChildren()) {
            if (n instanceof Labeled) {
                ((Labeled) n).setFont(new Font("Arial",13));
            }
        }

        // Define title and assign the scene for main window
        primaryStage.setTitle("Sea battle: the game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Register the player at the game server.
     */
    private void registerPlayer() {
        playerName = textFieldPlayerName.getText();
        if ("".equals(playerName) || playerName == null) {
            showMessage("Enter your name before registering");
            return;
        }
        game.registerPlayer(playerName, this, singlePlayerMode);
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
     * Creating all the Kingdom cards and putting them in the allKingdomCards list.
     */
    private void setUpKingdomCards() {
        allKingdomCards = new ArrayList<>();
        // setup

        Card militiaCard = new ActionCard("Militia", 4, "militiaAction()");
        Card marketCard = new ActionCard("Market", 5, "marketAction()");
        Card smithyCard = new ActionCard("Smithy", 4, "smithyAction()");
        Card mineCard = new ActionCard("Mine", 5, "mineAction()");
        Card witchCard = new ActionCard("Witch", 5, "witchAction()");
        Card lenderCard = new ActionCard("Lender", 4, "lenderAction()");
        Card messengerCard = new ActionCard("Messenger", 3, "messengerAction()");
        Card festivalCard = new ActionCard("Festival", 5, "festivalAction()");
        Card basementCard = new ActionCard("Basement", 2, "basementAction()");
        Card councilChamberCard = new ActionCard("Council Chamber", 5, "councilChamberAction()");
        Card banditCard = new ActionCard("Bandit", 5, "banditAction()");
        Card wildThiefCard = new ActionCard("Wild Thief", 4, "wildThiefAction()");
        Card villageCard = new ActionCard("Village", 3, "villageAction()");
        Card workplaceCard = new ActionCard("Workplace", 3, "workplaceAction()");
        Card merchantCard = new ActionCard("Merchant", 3, "merchantAction()");

        // add to list
        allKingdomCards.add(militiaCard);
        allKingdomCards.add(marketCard);
        allKingdomCards.add(smithyCard);
        allKingdomCards.add(mineCard);
        allKingdomCards.add(witchCard);
        allKingdomCards.add(lenderCard);
        allKingdomCards.add(messengerCard);
        allKingdomCards.add(festivalCard);
        allKingdomCards.add(basementCard);
        allKingdomCards.add(councilChamberCard);
        allKingdomCards.add(banditCard);
        allKingdomCards.add(wildThiefCard);
        allKingdomCards.add(villageCard);
        allKingdomCards.add(workplaceCard);
        allKingdomCards.add(merchantCard);




    }

    /**
     * Picks random kingdom cards out of all of the available ones.
     */
    private void pickRandomKingdomCards() {
        pickedKingdomCards = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numberOfKingdomCards; i++) {
            int randomIndex = rand.nextInt(allKingdomCards.size());
            Card randomKingdomCard = allKingdomCards.get(randomIndex);
            pickedKingdomCards.add(randomKingdomCard);
            allKingdomCards.remove(randomIndex);
        }
    }

    private void buildPiles() {
        boardPiles = new ArrayList<>();
        // Curse cards
        Card curseCard = new CurseCard("Curse", 0, 1);
        boardPiles.add(new Pile(curseCard, 30));

        // Victory point cards
        Card estateCard = new VictoryCard("Estate", 2, 1);
        Card dukedomCard = new VictoryCard("Dukedom", 5, 3);
        Card provinceCard = new VictoryCard("Province", 8, 6);
        boardPiles.add(new Pile(estateCard, 24));
        boardPiles.add(new Pile(dukedomCard, 12));
        boardPiles.add(new Pile(provinceCard, 12));

        // Money cards
        Card copperCard = new MoneyCard("Copper", 0, 1);
        Card silverCard = new MoneyCard("Silver", 3, 2);
        Card goldCard = new MoneyCard("Gold", 6, 3);
        boardPiles.add(new Pile(copperCard, 60));
        boardPiles.add(new Pile(silverCard, 40));
        boardPiles.add(new Pile(goldCard, 30));

        // Kingdom cards
        for (Card kingdomCard: pickedKingdomCards) {
            boardPiles.add(new Pile(kingdomCard, 10));
        }



    }

}
