package dominionshared.models;

import java.awt.*;

public abstract class Card {
    private String name;
    private Color color;
    private int cost;

    public Card(String name) {
        this.name = name;
    }

    public Card(String name, Color color) {
        this(name);
        this.color = color;
    }

    public Card(String name, Color color, int cost) {
        this(name, color);
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
