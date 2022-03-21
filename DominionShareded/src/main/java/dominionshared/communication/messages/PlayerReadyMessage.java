package dominionshared.communication.messages;

public class PlayerReadyMessage {
    private int playerNr;

    public PlayerReadyMessage(int playerNr) {
        this.playerNr = playerNr;
    }

    public int getPlayerNr() {
        return playerNr;
    }
}
