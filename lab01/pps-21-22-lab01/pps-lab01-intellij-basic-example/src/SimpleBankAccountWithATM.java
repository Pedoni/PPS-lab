public class SimpleBankAccountWithATM extends AbstractBankAccount {

    public SimpleBankAccountWithATM(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    public int getFee() {
        return 1;
    }
}
