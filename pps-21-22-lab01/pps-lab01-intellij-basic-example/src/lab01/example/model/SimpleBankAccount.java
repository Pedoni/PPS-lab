package lab01.example.model;

public class SimpleBankAccount extends AbstractBankAccount {

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    int getFee() {
        return 0;
    }
}
