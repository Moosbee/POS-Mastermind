package pos.mastermind;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {


    private final Password password;

    private final List<Password> trys = new ArrayList<>();

    public GameLogic(int combinationSize) {
        this.password = new Password(combinationSize);
    }

    public Password getPassword() {
        return password;
    }
}
