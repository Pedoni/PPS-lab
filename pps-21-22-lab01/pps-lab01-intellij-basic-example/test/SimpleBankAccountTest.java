import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;
import lab01.example.model.SimpleBankAccountWithATM;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

}
