package dominionserver.messageHandlers;

import dominionshared.dominiongame.IDominionGame;
import dominionshared.communication.messages.RegisterPlayerMessage;
import dominionshared.communication.messaging.MessageHandlerBase;
import dominionshared.models.Player;

public class RegisterPlayerMessageHandler extends MessageHandlerBase<RegisterPlayerMessage> {

    private IDominionGame game;

    public RegisterPlayerMessageHandler(IDominionGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(RegisterPlayerMessage message, String sessionId) {
        //TODO do something here (something with player register stuff and things).
        int playerNumber;
        if(game.getPlayerOne() == null){
            playerNumber = 0;
            game.setPlayerOne(new Player(message.getPlayerName()));
            game.getPlayerOne().setNumber(0);
        }
        else {
            playerNumber = 1;
            game.setPlayerTwo(new Player(message.getPlayerName()));
            game.getPlayerTwo().setNumber(1);
        }
        game.getMessageGenerator().notifyPlayerHasRegistered(sessionId, playerNumber, message.getPlayerName());
        game.getMessageGenerator().notifyOpponentHasRegistered(sessionId, playerNumber, message.getPlayerName());
    }
}
