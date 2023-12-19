package pos.mastermind;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    private final GameLogic board;

    public UI(GameLogic board) {

        this.board = board;
    }

    public void run() throws IOException {
        board.getPassword();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bitte " + System.in.available());
        System.out.println("Bitte geben Sie den Startwert ein:");
        System.out.print(">");
        String start = sc.nextLine();
        System.out.println("Bitte " + start);

    }

}
