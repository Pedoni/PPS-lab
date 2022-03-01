import org.junit.jupiter.api.*;

class SimpleBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

}
