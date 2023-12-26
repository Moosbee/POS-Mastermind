package pos.mastermind;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {


    private final Password password;

    private final List<Password> trys = new ArrayList<>();

    private final int maxTries;

    public GameLogic(int combinationSize, int maxTries) {
        this(new Password(combinationSize), maxTries);
    }

    public GameLogic(Password combination, int maxTries) {
        this.password = combination;
        this.maxTries = maxTries;
    }

    public Password getPassword() {
        return password;
    }


    public Password.Stat[] checkPassword(Password otherPassword) {

        trys.add(otherPassword);

        return password.passwordCompare(otherPassword);
    }

    public List<Password> getTries() {
        return trys;
    }

    public int getMaxTries() {
        return maxTries;
    }
}
