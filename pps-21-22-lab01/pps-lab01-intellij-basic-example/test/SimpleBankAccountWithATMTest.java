import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithATMTest extends AbstractBankAccountTest  {

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        fee = 1;
        bankAccount = new SimpleBankAccountWithATM(accountHolder, 0);
    }

}
