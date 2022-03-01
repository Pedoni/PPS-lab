import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;
import lab01.example.model.SimpleBankAccountWithATM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithATMTest extends AbstractBankAccountTest  {

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        fee = 1;
        bankAccount = new SimpleBankAccountWithATM(accountHolder, 0);
    }

}
