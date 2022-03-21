package dominionshared.models;

import java.awt.*;

public class VictoryCard extends Card {
    private int victoryPoints;

    public VictoryCard(String name, int cost) {
        super(name, Color.getHSBColor(60, 104, 176), cost);
    }

    public VictoryCard(String name, int cost, int victoryPoints) {
        this(name, cost);
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }
}
