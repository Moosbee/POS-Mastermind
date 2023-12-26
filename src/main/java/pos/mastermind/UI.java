package pos.mastermind;


import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import static org.fusesource.jansi.Ansi.ansi;

public class UI {

    private final GameLogic board;
    Terminal terminal;
    LineReaderImpl lineReader;

    public UI() throws InterruptedException, IOException {
        terminal = TerminalBuilder.terminal();
        lineReader = (LineReaderImpl) LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .build();
        System.out.println(ansi().a(Ansi.Attribute.INTENSITY_BOLD).a("Willkommen zu").reset());
        String hello = """

                  __  __           _                      _           _\s
                 |  \\/  |         | |                    (_)         | |
                 | \\  / | __ _ ___| |_ ___ _ __ _ __ ___  _ _ __   __| |
                 | |\\/| |/ _` / __| __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` |
                 | |  | | (_| \\__ \\ ||  __/ |  | | | | | | | | | | (_| |
                 |_|  |_|\\__,_|___/\\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|

                """;
        System.out.println(ansi().fg(Ansi.Color.GREEN).a(hello).reset());
        System.out.print(ansi().a("Bitte geben Sie die gr\u00F6\u00DFe der Kombination ein: ").reset());
        System.out.print(ansi().fgBright(Ansi.Color.GREEN).a(Ansi.Attribute.BLINK_FAST).a(">").reset());

        int suze = Integer.parseInt(lineReader.readLine());

        System.out.print(ansi().a("Bitte geben Sie die Maximalzahl an Versuchen ein: ").reset());
        System.out.print(ansi().fgBright(Ansi.Color.GREEN).a(Ansi.Attribute.BLINK_FAST).a(">").reset());

        int tries = Integer.parseInt(lineReader.readLine());

        this.board = new GameLogic(suze, tries);
        for (int i = 0; i < board.getPassword().length() * 4; i++) {
            System.out.print(ansi().a(Ansi.Attribute.UNDERLINE).a(Ansi.Attribute.STRIKETHROUGH_ON).a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.values()[i % Ansi.Color.values().length]).a(".").reset());
            Thread.sleep(10);
        }
        System.out.println();


        System.out.println(ansi().a("Eine " + board.getPassword().length() + " Zeichen langes Passwort wurde erstellt, erraten sie es in " + board.getMaxTries() + " Versuchen:").reset());


    }

    public void run() throws InterruptedException {
        System.out.println();
        System.out.println(ansi().render("@|green L|@@|blue e|@@|red g|@@|magenta e|@@|yellow n|@@|blue d|@@|cyan e|@@|white :|@").reset());
        System.out.println(ansi().fgBright(Ansi.Color.BLACK).a("     W: Wrong, diese Farbe existiert in der Kombination nicht; ").reset());
        System.out.println(ansi().render("     @|white E: Exists, diese Farbe ist in der Kombination vorhanden, aber die stelle ist falsch; |@\n     @|green R: Right, die Farbe und Position stimmen|@").reset());
//        System.out.println(ansi().render("@|green L|@@|blue e|@@|red g|@@|magenta e|@@|yellow n|@@|blue d|@@|cyan e|@@|white :|@").reset().toString() + ansi().a("W: Wrong, diese Farbe existiert in der Kombination nicht; ") + ansi().format("@|white E: Exists, diese Farbe ist in der Kombination vorhanden, aber die stelle ist falsch; |@@|green R: Right, die Farbe und Position stimmen |@").reset());
        System.out.println();
        printLegend();
        System.out.println();
        for (int i = 0; i < board.getPassword().length() * 4; i++) {
            System.out.print(ansi().a(Ansi.Attribute.UNDERLINE).a(Ansi.Attribute.STRIKETHROUGH_ON).a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.values()[i % Ansi.Color.values().length]).a(".").reset());
            Thread.sleep(10);
        }
        System.out.println();
        System.out.println();
        System.out.println("Das Passwortraten beginnt:");
        System.out.println();
        boolean hasWon = false;
        while (!hasWon && board.getTries().size() < board.getMaxTries()) {
            Password pass = inputPassword(board.getTries().size() + 1);
            System.out.println();
            System.out.print(ansi().cursorUp(3));
            Password.Stat[] erg = board.checkPassword(pass);
            printCompare(pass, erg, board.getTries().size());
            System.out.println();
            hasWon = Arrays.stream(erg).allMatch(f -> (f == Password.Stat.Right));
        }

        System.out.println();
        if (hasWon) {
            System.out.println(ansi().fg(Ansi.Color.GREEN).a(Ansi.Attribute.BLINK_FAST).a(Ansi.Attribute.INTENSITY_BOLD).a(Ansi.Attribute.UNDERLINE_DOUBLE).a("Gl\u00FCckwunsch du hast die Kombination in " + (board.getTries().size() + 1) + " Versuchen erraten.\nDas Passwort war:").reset());
        } else {
            System.out.println(ansi().fg(Ansi.Color.RED).a(Ansi.Attribute.BLINK_FAST).a(Ansi.Attribute.INTENSITY_BOLD).a(Ansi.Attribute.UNDERLINE_DOUBLE).a("Leider hast du die Kombination nicht in " + (board.getTries().size() + 1) + " Versuchen erraten k\u00F6nnen.\nDas Passwort w\u00E4re gewesen:").reset());

        }
        printCombination(board.getPassword());

    }

    public Password inputPassword(int tryNr) {

        int size = board.getPassword().length();


        System.out.print(" ");
        System.out.print("---- ");
        for (int i = 0; i < size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
        System.out.printf(" % 3d  ", tryNr);

        for (int i = 0; i < size; i++) {
            System.out.print("|- -" + (i == size - 1 ? "|" : ""));
        }
        System.out.println();
        System.out.print(" ");
        System.out.print("---- ");
        for (int i = 0; i < size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
        System.out.print(ansi().cursorUp(2).cursorRight(8));

        BaseSet[] newSet = new BaseSet[board.getPassword().length()];

        for (int pos = 0; pos < newSet.length; pos++) {


            int key = lineReader.readCharacter();

            BaseSet text;
            text = BaseSet.byLabel(String.valueOf((char) key).toUpperCase(Locale.ROOT));
            if (key == 'q') {
                break;
            }

            System.out.print(ansi().fgBright(text.getColor()).a(text.getLabel()).reset());
            System.out.print(ansi().cursorRight(3));
            newSet[pos] = text;

        }
        System.out.println();


        return new Password(newSet);
    }

    public void printLegend() {
        BaseSet[] set = BaseSet.values();

        System.out.print(ansi().render("@|red F|@@|green a|@@|yellow r|@@|blue b|@@|magenta e|@@|cyan n|@@|white :|@").reset());

        for (BaseSet baseSet : set) {
            System.out.print(ansi().fgBright(baseSet.getColor()).a(", " + baseSet.name() + " (" + baseSet.getLabel() + ")"));
        }
    }

    public void printCompare(Password password, Password.Stat[] stat, int tryNr) {

        BaseSet[] combination = password.getCombination();

        System.out.print(" ---- ");

        for (BaseSet baseSet : combination) {
            System.out.print((" ") + ansi().fgBright(baseSet.getColor()).a("---").reset());
        }
        System.out.print("       ");

        for (Password.Stat value : stat) {
            System.out.print((" ") + ansi().fgBright(value.color).a("-").reset());
        }
        System.out.println();
        System.out.printf(" % 3d  ", tryNr);

        for (int i = 0; i < combination.length; i++) {
            System.out.print(("|") + ansi().fgBright(combination[i].getColor()).a("-" + combination[i].getLabel() + "-").reset() + (i == combination.length - 1 ? "|" : ""));
        }
        System.out.print("      ");

        for (int i = 0; i < stat.length; i++) {
            System.out.print(("|") + ansi().fg(stat[i].color).a(stat[i] == Password.Stat.Right ? "R" : (stat[i] == Password.Stat.Exists ? "E" : "W")).reset() + (i == combination.length - 1 ? "|" : ""));
        }
        System.out.println();
        System.out.print(" ---- ");

        for (BaseSet baseSet : combination) {
            System.out.print((" ") + ansi().fgBright(baseSet.getColor()).a("---").reset());
        }
        System.out.print("       ");

        for (Password.Stat value : stat) {
            System.out.print((" ") + ansi().fgBright(value.color).a("-").reset());
        }
        System.out.println();

    }


    public void printCombination(Password password) {


        BaseSet[] combination = password.getCombination();

        System.out.println();
        System.out.print(" ");
        for (BaseSet baseSet : combination) {
            System.out.print(ansi().fgBright(baseSet.getColor()).a("--- ").reset());
        }
        System.out.println();
        for (int i = 0; i < combination.length; i++) {
            System.out.print(("|") + ansi().fgBright(combination[i].getColor()).a("-" + combination[i].getLabel() + "-").reset() + (i == combination.length - 1 ? "|" : ""));
        }
        System.out.println();
        System.out.print(" ");
        for (BaseSet baseSet : combination) {
            System.out.print(ansi().fgBright(baseSet.getColor()).a("--- ").reset());
        }

    }

    public void exit() {
        AnsiConsole.systemUninstall();
    }


}
