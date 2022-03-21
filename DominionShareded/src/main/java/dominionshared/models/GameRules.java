package dominionshared.models;

public class GameRules {
    private int startingCards;
    private int actionsAllowed;
    private int purchasesAllowed;

    public GameRules() {
        this.startingCards = 5;
        this.actionsAllowed = 1;
        this.purchasesAllowed = 1;
    }

    public GameRules(int startingCards, int actionsAllowed, int purchasesAllowed) {
        this.startingCards = startingCards;
        this.actionsAllowed = actionsAllowed;
        this.purchasesAllowed = purchasesAllowed;
    }

    public int getStartingCards() {
        return startingCards;
    }

    public void setStartingCards(int startingCards) {
        this.startingCards = startingCards;
    }

    public int getActionsAllowed() {
        return actionsAllowed;
    }

    public void setActionsAllowed(int actionsAllowed) {
        this.actionsAllowed = actionsAllowed;
    }

    public int getPurchasesAllowed() {
        return purchasesAllowed;
    }

    public void setPurchasesAllowed(int purchasesAllowed) {
        this.purchasesAllowed = purchasesAllowed;
    }
}
