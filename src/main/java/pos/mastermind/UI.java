package pos.mastermind;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class UI {

    private final GameLogic board;

    private Terminal terminal;

//    private Screen screen;

    public UI() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        try {
            terminal = defaultTerminalFactory.createTerminal();
//            screen = new TerminalScreen(terminal);
//            screen.startScreen();

        } catch (Exception e) {

        }


        this.board = new GameLogic(10);

    }

    public void run() {
        if (terminal == null) {
            return;
        }
        try {
            terminal.enterPrivateMode();


            // Get the TextGraphics object to draw on the screen
            TextGraphics textGraphics = terminal.newTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.GREEN);

            textGraphics.putString(1, 1, "Wilkommen zu");

            textGraphics.putString(1, 3, "  __  __           _                      _           _ ");
            textGraphics.putString(1, 4, " |  \\/  |         | |                    (_)         | |");
            textGraphics.putString(1, 5, " | \\  / | __ _ ___| |_ ___ _ __ _ __ ___  _ _ __   __| |");
            textGraphics.putString(1, 6, " | |\\/| |/ _` / __| __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` |");
            textGraphics.putString(1, 7, " | |  | | (_| \\__ \\ ||  __/ |  | | | | | | | | | | (_| |");
            textGraphics.putString(1, 8, " |_|  |_|\\__,_|___/\\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|");
            textGraphics.putString(1, 9, "                                                        ");

            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);


            textGraphics.putString(1, 10, "Bitte geben Sie die gr\u263Bsse der Kombination ein:");


            terminal.flush();

            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 20000) {
                // The call to pollInput() is not blocking, unlike readInput()
                if (terminal.pollInput() != null) {
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignore) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCombination(Password password) {
        System.out.println("Das Passwort war:");
        System.out.print("--");
        BaseSet[] combination = password.getCombination();
        for (int i = 0; i < combination.length; i++) {
            System.out.print("-" + combination[i].getLabel() + "-");

        }
        System.out.println("--");

    }

    public void exit() {
        if (terminal != null) {
            try {
                    /*
                    Closing the terminal doesn't always do something, but if you run the Swing or AWT bundled terminal
                    emulators for example, it will close the window and allow this application to terminate. Calling it
                    on a UnixTerminal will not have any affect.
                     */
                terminal.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
