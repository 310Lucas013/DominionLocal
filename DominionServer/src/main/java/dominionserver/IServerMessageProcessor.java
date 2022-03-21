package dominionserver;

import dominionshared.communication.messaging.IMessageProcessor;
import dominionshared.dominiongame.IDominionGame;

public interface IServerMessageProcessor extends IMessageProcessor {

    void registerGame(IDominionGame game);
}
