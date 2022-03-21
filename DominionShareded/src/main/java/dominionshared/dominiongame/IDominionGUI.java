package dominionshared.dominiongame;

public interface IDominionGUI {

    /**
     * Set player number.
     * @param playerNr identification of player
     * @param name player's name
     */
    public void setPlayerNumber(int playerNr, String name);

    /**
     * Set the name of the opponent in the GUI.
     * @param playerNr identification of player
     * @param name opponent's name
     */
    public void setOpponentName(int playerNr, String name);

    /**
     * Notification that the game has started.
     * @param playerNr identification of player
     */
    public void notifyStartGame(int playerNr);

    /**
     * Show error message.
     * @param playerNr identification of player
     * @param errorMessage error message
     */
    public void showErrorMessage(int playerNr, String errorMessage);

    public void switchTurn();
}
