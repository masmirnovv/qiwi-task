import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyRatesTest {

    private static final String[] TEST_WRONG_COMMAND = {"kek", "--code=USD", "--date=2023-07-20"};
    private static final String[] TEST_NO_CODE = {"currency_rates", "--date=2023-07-20"};
    private static final String[] TEST_NO_DATE = {"currency_rates", "--code=USD"};
    private static final String[] TEST_NO_PARSE_DATE = {"currency_rates", "--code=USD", "--date=20/07/2023"};
    private static final String[] TEST_UNKNOWN_CURRENCY = {"currency_rates", "--code=KEK", "--date=2023-07-20"};
    private static final String[] TEST_OK = {"currency_rates", "--code=USD", "--date=2023-07-20"};

    @Test
    public void testWrongCommand() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(TEST_WRONG_COMMAND));
    }

    @Test
    public void testNoCode() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(TEST_NO_CODE));
    }

    @Test
    public void testNoDate() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(TEST_NO_DATE));
    }

    @Test
    public void testNoParseDate() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(TEST_NO_PARSE_DATE));
    }

    @Test
    public void testUnknownCurrency() {
        assertDoesNotThrow(() -> Main.main(TEST_UNKNOWN_CURRENCY));
    }

    @Test
    public void testOk() {
        assertDoesNotThrow(() -> Main.main(TEST_OK));
    }

}
