package lab01.example.model;

public class SimpleBankAccountWithATM extends AbstractBankAccount {

    public SimpleBankAccountWithATM(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    int getFee() {
        return 1;
    }
}
