package pos.mastermind;

import java.util.Random;

public class Password {

    private final BaseSet[] combination;


    public Password(BaseSet[] combination) {
        this.combination = combination;
    }

    public Password(int combinationSize) {
        this(genCombination(combinationSize));
    }

    public enum BaseSet {
        Red,
        Yellow,
        Green,
        Blue,
        Black,
        White,
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
