package pos.mastermind;


import org.fusesource.jansi.Ansi;

import java.util.Objects;

public enum BaseSet {
    Red("R", Ansi.Color.RED),
    Yellow("Y", Ansi.Color.YELLOW),
    Green("G", Ansi.Color.GREEN),
    Blue("B", Ansi.Color.BLUE),
    Black("S", Ansi.Color.BLACK),
    White("W", Ansi.Color.WHITE),

    Empty("E", Ansi.Color.DEFAULT);

    public final String label;
    public final Ansi.Color color;

    public static BaseSet byLabel(String label) {
        BaseSet[] sets = BaseSet.values();
        for (BaseSet set : sets) {
            if (Objects.equals(set.getLabel(), label)) {
                return set;
            }
        }
        return Empty;
    }

    BaseSet(String label, Ansi.Color color) {

        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public Ansi.Color getColor() {
        return color;
    }
}
