package pos.mastermind;

public enum BaseSet {
    Red("R", ConsoleColors.RED_BRIGHT),
    Yellow("Y", ConsoleColors.YELLOW_BRIGHT),
    Green("G", ConsoleColors.GREEN_BRIGHT),
    Blue("B", ConsoleColors.BLUE_BRIGHT),
    Black("S", ConsoleColors.BLACK),
    White("W", ConsoleColors.WHITE_BRIGHT);

    public final String label;
    public final String color;

    BaseSet(String label, String color) {
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return color + label + ConsoleColors.RESET;
    }
}
