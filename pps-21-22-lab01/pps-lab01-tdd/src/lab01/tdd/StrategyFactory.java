package lab01.tdd;

public interface StrategyFactory {

    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleOfStrategy(final int value);

    SelectStrategy createEqualsStrategy(final int value);

}
