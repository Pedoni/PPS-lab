public final class StrategyFactoryImpl implements StrategyFactory {

    @Override
    public SelectStrategy createEvenStrategy() {
        return element -> element % 2 == 0;
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(final int value) {
        return element -> element % value == 0;
    }

    @Override
    public SelectStrategy createEqualsStrategy(final int value) {
        return element -> element == value;
    }

}
