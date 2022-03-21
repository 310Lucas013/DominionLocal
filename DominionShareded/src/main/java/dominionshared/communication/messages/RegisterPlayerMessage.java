package dominionshared.communication.messages;

public class RegisterPlayerMessage {
    private String playerName;
    private String playerPassword;

    public RegisterPlayerMessage(String name, String password)
    {
        this.playerName = name;
        this.playerPassword = password;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }
}
