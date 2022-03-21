package dominionshared.communication.messages;

public class PlayerHasRegisteredMessage {
    private int playerNumber;

    private String playerName;

    public PlayerHasRegisteredMessage(int playerNumber, String playerName) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }
}

