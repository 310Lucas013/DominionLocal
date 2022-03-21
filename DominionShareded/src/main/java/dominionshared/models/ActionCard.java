package dominionshared.models;

import java.awt.*;

public class ActionCard extends Card {
    private String methodName;

    public ActionCard(String name, int cost) {
        super(name, Color.getHSBColor(162, 42, 198), cost);
    }

    public ActionCard(String name, int cost, String methodName) {
        this(name, cost);
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
