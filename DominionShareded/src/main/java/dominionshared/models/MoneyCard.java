package dominionshared.models;

import java.awt.*;

public class MoneyCard extends Card {
    private int buyingPower;

    public MoneyCard(String name, int cost) {
        super(name, Color.getHSBColor(31, 169, 160), cost);
    }

    public MoneyCard(String name, int cost, int buyingPower) {
        this(name, cost);
        this.buyingPower = buyingPower;
    }

    public int getBuyingPower() {
        return buyingPower;
    }

    public void setBuyingPower(int buyingPower) {
        this.buyingPower = buyingPower;
    }
}
