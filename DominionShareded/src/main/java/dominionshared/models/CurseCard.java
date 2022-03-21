package dominionshared.models;

import java.awt.*;

public class CurseCard extends Card {
    private int pointDeduction;

    public CurseCard(String name, int cost) {
        super(name, Color.getHSBColor(197, 54, 130), cost);
        this.pointDeduction = 1;
    }

    public CurseCard(String name, int cost, int pointDeduction) {
        this(name, cost);
        this.pointDeduction = pointDeduction;
    }

    public int getPointDeduction() {
        return pointDeduction;
    }

    public void setPointDeduction(int pointDeduction) {
        this.pointDeduction = pointDeduction;
    }
}
