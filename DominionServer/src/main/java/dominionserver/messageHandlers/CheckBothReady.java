package dominionserver.messageHandlers;

import dominionshared.dominiongame.IDominionGame;

public class CheckBothReady {
    private IDominionGame game;

    public CheckBothReady(IDominionGame iDominionGame) {
        this.game = iDominionGame;
    }

    public void check(int playerNumber){
        game.notifyWhenReadyLogic(playerNumber);
    }
}
