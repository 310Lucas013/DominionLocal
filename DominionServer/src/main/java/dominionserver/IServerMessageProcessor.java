package dominionserver;

import seabattleshared.communication.messaging.IMessageProcessor;
import seabattleshared.seabattlegame.ISeaBattleGame;

public interface IServerMessageProcessor extends IMessageProcessor {

    void registerGame(ISeaBattleGame game);
}
