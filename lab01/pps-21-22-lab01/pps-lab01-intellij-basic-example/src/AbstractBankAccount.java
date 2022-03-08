public abstract class AbstractBankAccount implements BankAccount {

    protected final AccountHolder holder;
    protected double balance;

    public AbstractBankAccount(final double balance, final AccountHolder holder) {
        this.balance = balance;
        this.holder = holder;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance -= (amount + this.getFee());
        }
    }

    public abstract int getFee();

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount + this.getFee();
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
