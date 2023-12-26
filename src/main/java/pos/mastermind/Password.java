package pos.mastermind;

import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.Random;

public class Password {

    private final BaseSet[] combination;


    public Password(BaseSet[] combination) {
        this.combination = combination;
    }

    public Password(int combinationSize) {
        this(genCombination(combinationSize));
    }

    public enum Stat {
        Wrong(Ansi.Color.BLACK),
        Exists(Ansi.Color.WHITE),
        Right(Ansi.Color.GREEN);

        public final Ansi.Color color;

        Stat(Ansi.Color color) {
            this.color = color;
        }
    }

    public BaseSet[] getCombination() {
        return combination;
    }

    public int length() {
        return combination.length;
    }

    public Stat[] passwordCompare(Password otherPassword) {
        BaseSet[] otherSet = otherPassword.combination;
        int length = Math.max(combination.length, otherSet.length);
        Stat[] returnStats = new Stat[length];

        for (int i = 0; i < length; i++) {
            BaseSet set = otherSet[i];
            if (set == combination[i]) {
                returnStats[i] = Stat.Right;
            } else if (Arrays.stream(combination).anyMatch(f -> (f == set))) {
                returnStats[i] = Stat.Exists;
            } else {
                returnStats[i] = Stat.Wrong;
            }
        }

        return returnStats;
    }

    public static BaseSet[] genCombination(int combinationSize) {

        Random random = new Random();

        BaseSet[] base = BaseSet.values();

        BaseSet[] newSet = new BaseSet[combinationSize];

        for (int i = 0; i < newSet.length; i++) {
            newSet[i] = base[random.nextInt(0, base.length)];
        }

        return newSet;
    }
}
