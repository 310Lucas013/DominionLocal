package dominionshared.models;

public class Pile {
    private Card card;
    private int amountLeft;

    public Pile(Card card, int amountLeft) {
        this.card = card;
        this.amountLeft = amountLeft;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }
}
