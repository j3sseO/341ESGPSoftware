import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

/**
 * BlackBox Tests to conduct on the high priority requirement 'ESGP Account
 * Login' for the ESGP software
 * Based on the given SRS and Software Design Specification proposed by student
 * 5
 * Blackbox tests further outlined in my Software Testing Plan
 * 
 * @author Student 1
 * @version 1.0
 */
public class ESGPAccountLoginUnitTests {
    /**
     * Simulates user input into the console
     * 
     * @param inputData The data to input
     */
    public void inputDataToConsole(String inputData) {
        // Create a new input stream to simulate user input
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(in);
    }

    /**
     * Test that an encost member can log in with valid username and password
     */
    @Test
    @DisplayName("Test encost member log in with valid username and password")
    void validUsernameValidPasswordTest() {
        // The data we want to simulate
        // (Both username and password here are assumed correct)
        String testUsername = "encostUserA";
        String testPassword = "password789";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-verified", Backend.getInstance().getUserType());
    }

    /**
     * Test that an encost member can log in with valid username and password
     * Testing to make sure its possible for more than 1 different user to login
     */
    @Test
    @DisplayName("Test encost member log in with valid username and password")
    void validUsernameValidPasswordTest2() {
        // The data we want to simulate
        // (Both username and password here are assumed correct)
        String testUsername = "encostUserB";
        String testPassword = "password234";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-verified", Backend.getInstance().getUserType());
    }

    /**
     * Test that an encost member cannot log in with valid username and invalid
     * password
     */
    @Test
    @DisplayName("Test encost member log in with valid username and invalid password")
    void validUsernameInvalidPasswordTest() {
        // The data we want to simulate
        String testUsername = "encostUserA";
        String testPassword = "WrongPassword";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Test that an encost member cannot log in with invalid username and valid
     * password
     */
    @Test
    @DisplayName("Test encost member log in with invalid username and valid password")
    void invalidUsernameValidPasswordTest() {
        // The data we want to simulate
        String testUsername = "WrongUsername";
        String testPassword = "password789";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Test that an encost member cannot log in with invalid username and invalid
     * password
     */
    @Test
    @DisplayName("Test encost member log in with invalid username and invalid password")
    void invalidUsernameInvalidPasswordTest() {
        // The data we want to simulate
        String testUsername = "WrongUsername";
        String testPassword = "WrongPassword";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Test that an encost member cannot log in with a valid username and valid
     * password for a different user
     */
    @Test
    @DisplayName("Test encost member log in with valid username and valid password for different user")
    void validUsernameValidDifferentPasswordTest() {
        // The data we want to simulate
        String testUsername = "encostUserA";
        String testPassword = "password234";
        String inputData = testUsername + System.lineSeparator() + testPassword;

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Changed, call method to process credentials
        Backend.getInstance().processValidCredentials();

        // Run the login prompt to test
        Backend.getInstance().loginPrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Tests that the correct console messages are being output
     */
    @Nested
    class consoleOutputs {

        private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        /**
         * Sets up the console output so that it can be tested
         */
        @BeforeEach
        public void setUpConsoleOutput() {
            System.setOut(new PrintStream(consoleOutput));
        }

        /**
         * Test that an encost member can log in with valid username and password
         */
        @Test
        @DisplayName("Test encost member log in with valid username and password")
        void validUsernameValidPasswordOutputTest() {
            // The data we want to simulate
            // (Both username and password here are assumed correct)
            String testUsername = "encostUserA";
            String testPassword = "password789";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            // Changed, now has username in welcome message, removed line separator
            String expected = "Welcome encostUserA";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 7th line of console output
            assertEquals(expected, output[6]);
        }

        /**
         * Test that an encost member can log in with valid username and password
         * Testing to make sure that the welcome message is properly personalised and
         * not hard coded for a particular user
         */
        @Test
        @DisplayName("Test encost member log in with valid username and password")
        void validUsernameValidPasswordOutputTest2() {
            // The data we want to simulate
            // (Both username and password here are assumed correct)
            String testUsername = "encostUserB";
            String testPassword = "password234";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Welcome encostUserB";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 7th line of console output
            assertEquals(expected, output[6]);
        }

        /**
         * Test that an encost member cannot log in with valid username and invalid
         * password
         */
        @Test
        @DisplayName("Test encost member log in with valid username and invalid password")
        void validUsernameInvalidPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "encostUserA";
            String testPassword = "WrongPassword";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            // Changed, removed line separator
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with invalid username and valid
         * password
         */
        @Test
        @DisplayName("Test encost member log in with invalid username and valid password")
        void invalidUsernameValidPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "WrongUsername";
            String testPassword = "password789";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            // Changed, removed line separator
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with invalid username and invalid
         * password
         */
        @Test
        @DisplayName("Test encost member log in with invalid username and invalid password")
        void invalidUsernameInvalidPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "WrongUsername";
            String testPassword = "WrongPassword";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            // Changed, removed line separator
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with a valid username and valid
         * password for a different user
         */
        @Test
        @DisplayName("Test encost member log in with valid username and valid password for different user")
        void validUsernameValidDifferentPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "encostUserA";
            String testPassword = "password234";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            // Changed, removed line separator
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }


        /**
         * Test that an encost member cannot log in with an empty username and valid
         * password
         */
        @Test
        @DisplayName("Test encost member log in with empty username and valid password")
        void emptyUsernameValidPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "";
            String testPassword = "password789";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with an valid username and empty
         * password
         */
        @Test
        @DisplayName("Test encost member log in with valid username and empty password")
        void validUsernameEmptyPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "encostUserA";
            String testPassword = "";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with an empty username and invalid
         * password
         */
        @Test
        @DisplayName("Test encost member log in with empty username and invalid password")
        void emptyUsernameInvalidPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "";
            String testPassword = "WrongPassword";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with an invalid username and empty
         * password
         */
        @Test
        @DisplayName("Test encost member log in with invalid username and empty password")
        void invalidUsernameEmptyPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "WrongUsername";
            String testPassword = "";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }

        /**
         * Test that an encost member cannot log in with an empty username and empty
         * password
         */
        @Test
        @DisplayName("Test encost member log in with empty username and empty password")
        void emptyUsernameEmptyPasswordOutputTest() {
            // The data we want to simulate
            String testUsername = "";
            String testPassword = "";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt to test
            Backend.getInstance().loginPrompt();

            // Expected output
            String expected = "Invalid username or password please try again";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 6th line of console output
            assertEquals(expected, output[5]);
        }
    }
}