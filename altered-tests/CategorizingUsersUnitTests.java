import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

/**
 * BlackBox Tests to conduct on the high priority requirements 'Categorizing
 * Users' for the ESGP software
 * Based on the given SRS and Software Design Specification proposed by student
 * 5
 * Blackbox tests further outlined in my Software Testing Plan
 * 
 * @author Student 1
 * @version 1.0
 */
public class CategorizingUsersUnitTests {
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
     * Test that a user becomes a community user when community is selected
     */
    @Test
    @DisplayName("Test the community user type can be selected")
    void communityUserSelectionTest() {
        // The data we want to simulate
        String inputData = "a";

        // Send the data to the console for testing
        inputDataToConsole(inputData);
        
        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("community", Backend.getInstance().getUserType());
    }

    /**
     * Test that a user becomes an unverified encost user when encost is selected
     */
    @Test
    @DisplayName("Test the encost user type can be selected")
    void encostUserSelectionTest() {
        // The data we want to simulate
        String inputData = "b";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Test that a user becomes a community user when community is selected with
     * capital letter
     */
    @Test
    @DisplayName("Test the community user type can be selected with capital")
    void communityUserSelectionCapitalTest() {
        // The data we want to simulate
        String inputData = "A";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("community", Backend.getInstance().getUserType());
    }

    /**
     * Test that a user becomes an unverified encost user when encost is selected
     * with capital letter
     */
    @Test
    @DisplayName("Test the encost user type can be selected with capital")
    void encostUserSelectionCapitalTest() {
        // The data we want to simulate
        String inputData = "B";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("encost-unverified", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when empty string is input
     */
    @Test
    @DisplayName("Test that no type is added when empty string is input")
    void emptyInputUserSelectionTest() {
        // The data we want to simulate
        String inputData = "";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when integer is input
     */
    @Test
    @DisplayName("Test that no type is added when integer is input")
    void integerInputUserSelectionTest() {
        // The data we want to simulate
        String inputData = "1";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when invalid character is input
     */
    @Test
    @DisplayName("Test that no type is added when invalid character is input")
    void invalidCharUserSelectionTest() {
        // The data we want to simulate
        String inputData = "?";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when invalid letter is input
     */
    @Test
    @DisplayName("Test that no type is added when invalid letter is input")
    void invalidLetterUserSelectionTest() {
        // The data we want to simulate
        String inputData = "c";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when invalid capital letter is input
     */
    @Test
    @DisplayName("Test that no type is added when invalid capital letter is input")
    void invalidCapitalLetterUserSelectionTest() {
        // The data we want to simulate
        String inputData = "C";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
    }

    /**
     * Test that no type for the user is added when a string of characters is input
     */
    @Test
    @DisplayName("Test that no type is added when string of characters is input")
    void invalidStringUserSelectionTest() {
        // The data we want to simulate
        String inputData = "abc";

        // Send the data to the console for testing
        inputDataToConsole(inputData);

        // Run the welcome prompt to test
        Backend.getInstance().welcomePrompt();

        // Test
        assertEquals("invalid", Backend.getInstance().getUserType());
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
         * Test that a user becomes a community user when community is selected
         */
        @Test
        @DisplayName("Test the community user type can be selected")
        void communityUserSelectionTest() {
            // The data we want to simulate
            String inputData = "a";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "ESGP Feature Options:"; 

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 11th line of console output
            assertEquals(expected, output[10]);
        }

        /**
         * Test that a user becomes an unverified encost user when encost is selected
         */
        @Test
        @DisplayName("Test the encost user type can be selected")
        void encostUserSelectionTest() {
            // The data we want to simulate
            String inputData = "b";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Welcome Encost User please login";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 11th line of console output
            assertEquals(expected, output[10]);
        }

        /**
         * Test that a user becomes a community user when community is selected with
         * capital letter
         */
        @Test
        @DisplayName("Test the community user type can be selected with capital")
        void communityUserSelectionCapitalTest() {
            // The data we want to simulate
            String inputData = "A";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            String expected = "ESGP Feature Options:";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 11th line of console output
            assertEquals(expected, output[10]);
        }

        /**
         * Test that a user becomes an unverified encost user when encost is selected
         * with capital letter
         */
        @Test
        @DisplayName("Test the encost user type can be selected with capital")
        void encostUserSelectionCapitalTest() {
            // The data we want to simulate
            String inputData = "B";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Welcome Encost User please login";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 11th line of console output
            assertEquals(expected, output[10]);
        }

        /**
         * Test that invalid output is shown to the user when empty string is input
         */
        @Test
        @DisplayName("Test that invalid is output when empty string is input")
        void emptyInputMessageOutputTest() {
            // The data we want to simulate
            String inputData = "";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }

        /**
         * Test that invalid output is shown to the user when an integer is input
         */
        @Test
        @DisplayName("Test that invalid is output when empty string is input")
        void integerInputMessageOutputTest() {
            // The data we want to simulate
            String inputData = "1";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }

        /**
         * Test that invalid output is shown to the user when an invalid char is input
         */
        @Test
        @DisplayName("Test that invalid is output when invalid char is input")
        void invalidCharInputMessageOutputTest() {
            // The data we want to simulate
            String inputData = "?";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }

        /**
         * Test that invalid output is shown to the user when an invalid letter is input
         */
        @Test
        @DisplayName("Test that no type is added when invalid letter is input")
        void invalidLetterMessageOutputTest() {
            // The data we want to simulate
            String inputData = "c";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }

        /**
         * Test that invalid output is shown to the user when an invalid capital letter
         * is input
         */
        @Test
        @DisplayName("Test that no type is added when invalid capital letter is input")
        void invalidCapitalLetterMessageOutputTest() {
            // The data we want to simulate
            String inputData = "C";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }

        /**
         * Test that invalid output is shown to the user when a string of characters is
         * input
         */
        @Test
        @DisplayName("Test that no type is added when string of characters is input")
        void invalidStringMessageOutputTest() {
            // The data we want to simulate
            String inputData = "abc";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            // Changed to run main
            Backend.getInstance().main(null);

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test that the output has the expected header
            // Changed to check 10th line of console output
            assertEquals(expected, output[9]);
        }
    }
}