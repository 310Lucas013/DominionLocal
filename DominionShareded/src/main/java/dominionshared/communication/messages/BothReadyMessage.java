package dominionshared.communication.messages;

public class BothReadyMessage {
    private String playerOneName;
    private String playerTwoName;

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public BothReadyMessage(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }
}
