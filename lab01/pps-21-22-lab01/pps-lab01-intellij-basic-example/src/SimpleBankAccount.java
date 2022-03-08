public class SimpleBankAccount extends AbstractBankAccount {

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    public int getFee() {
        return 0;
    }
}
