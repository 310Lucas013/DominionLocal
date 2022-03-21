package dominionserver.messageHandlers;

import dominionshared.communication.messages.PlayerReadyMessage;
import dominionshared.communication.messaging.MessageHandlerBase;
import dominionshared.dominiongame.IDominionGame;

public class PlayerReadyMessageHandler extends MessageHandlerBase<PlayerReadyMessage> {

    private IDominionGame game;

    public PlayerReadyMessageHandler(IDominionGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(PlayerReadyMessage message, String sessionId) {
        //TODO do something here (something with player register stuff and things).
        game.notifyWhenReadyLogic(message.getPlayerNr());
        if (game.getPlayerOne().isReady() && game.getPlayerTwo().isReady()) {
            game.getMessageGenerator().notifyBothReady(sessionId, game.getPlayerOne().getName(), game.getPlayerTwo().getName());
            game.getMessageGenerator().notifyBothReadyOpponent(sessionId, game.getPlayerOne().getName(), game.getPlayerTwo().getName());
        }
//        CheckBothReady cbr = new CheckBothReady(game);
//        cbr.check(message.getPlayerNr());

//        game.registerNewPlayer(message.getPlayerName(), message.getPassword(), sessionId);
    }
}
