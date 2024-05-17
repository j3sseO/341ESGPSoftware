import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

/**
 * BlackBox Tests to conduct on the high priority requirement 'ESGP Feature
 * Options' for the ESGP software
 * Based on the given SRS and Software Design Specification proposed by student
 * 5
 * Blackbox tests further outlined in my Software Testing Plan
 * 
 * @author Student 1
 * @version 1.0
 */
public class ESGPFeatureOptionsUnitTests {

    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

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
     * Sets up the console output so that it can be tested
     */
    @BeforeEach
    public void setUpConsoleOutput() {
        System.setOut(new PrintStream(consoleOutput));
    }

    /**
     * Tests where the userType is "community"
     */
    @Nested
    class commmunityOptions {
        /**
         * Sets up the user as a community user
         * - userType is private therefore we must rely on public methods to set it
         * - The public method to set the user type is tested in
         * CategorizingUsersUnitTests.java
         */
        @BeforeEach
        public void loginAsCommunityUser() {
            // The data we want to simulate
            String inputData = "a";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the welcome prompt to test
            Backend.getInstance().welcomePrompt();
        }

        /**
         * Test that a community user can select visualise graph
         */
        @Test
        @DisplayName("Test community user can select visualise graph")
        void commmunitySelectVisualiseTest() {
            // The data we want to simulate
            String inputData = "a";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Graph data visualisation open in new window";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that a community user cannot select visualise graph using a string
         */
        @Test
        @DisplayName("Test community user cannot select visualise graph using string")
        void commmunitySelectVisualiseStringTest() {
            // The data we want to simulate
            String inputData = "abc";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);;
        }

        /**
         * Test that a commmunity user cannot select visualise graph using encost user
         * letter selection
         */
        @Test
        @DisplayName("Test commmunity cannot select visualise graph using encost user letter selection")
        void commmunitySelectencostVisualiseTest() {
            // The data we want to simulate
            String inputData = "b";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that a commmunity user cannot select summary statistics using encost
         * user letter selection
         */
        @Test
        @DisplayName("Test commmunity user cannot select summary statistics using encost user letter selection")
        void commmunitySelectencostSummaryStatisticsTest() {
            // The data we want to simulate
            String inputData = "c";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that a community user can select visualise graph
         */
        @Test
        @DisplayName("Test community user can select visualise graph using Capital A")
        void commmunitySelectVisualiseCapitalTest() {
            // The data we want to simulate
            String inputData = "A";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Graph data visualisation open in new window";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that a commmunity user cannot select visualise graph using encost user
         * letter selection
         */
        @Test
        @DisplayName("Test commmunity cannot select visualise graph using capital encost user letter selection")
        void commmunitySelectencostVisualiseCapitalTest() {
            // The data we want to simulate
            String inputData = "B";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that a commmunity user cannot select summary statistics using encost
         * user letter selection
         */
        @Test
        @DisplayName("Test commmunity user cannot select summary statistics using capital encost user letter selection")
        void commmunitySelectencostSummaryStatisticsCapitalTest() {
            // The data we want to simulate
            String inputData = "C";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an community user is displayed correct error message when inputing
         * empty string
         */
        @Test
        @DisplayName("Test commmunity user selecting empty is incorrect")
        void commmunityUserEmptySelectTest() {
            // The data we want to simulate
            String inputData = "";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an community user is displayed correct error message when inputing
         * an integer
         */
        @Test
        @DisplayName("Test commmunity user selecting integer is incorrect")
        void commmunityUserIntegerSelectTest() {
            // The data we want to simulate
            String inputData = "1";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an community user is displayed correct error message when inputting
         * an incorrect character
         */
        @Test
        @DisplayName("Test commmunity user selecting an incorrect character")
        void commmunityUserIncorrectCharSelectTest() {
            // The data we want to simulate
            String inputData = "!";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an community user is displayed correct error message when inputting
         * an incorrect letter
         */
        @Test
        @DisplayName("Test commmunity user selecting an incorrect letter")
        void commmunityUserIncorrectLetterSelectTest() {
            // The data we want to simulate
            String inputData = "d";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an community user is displayed correct error message when inputting
         * an incorrect letter
         */
        @Test
        @DisplayName("Test commmunity user selecting an incorrect captital letter")
        void commmunityUserIncorrectCapitalLetterSelectTest() {
            // The data we want to simulate
            String inputData = "D";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }
    }

    /**
     * Tests where the userType is "encost-verified"
     */
    @Nested
    class encostOptions {
        /**
         * Sets up the user as an encost user
         * - userType is private therefore we must rely on public methods to set it
         * - The public method to set the user type is tested in
         * ESGPAccountLoginUnitTests.java
         */
        @BeforeEach
        public void loginAsCommunityUser() {
            // The data we want to simulate
            // (Both username and password here are assumed correct)
            String testUsername = "encostUserA";
            String testPassword = "password789";
            String inputData = testUsername + System.lineSeparator() + testPassword;

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Changed, call method to process credentials
            Backend.getInstance().processValidCredentials();

            // Run the login prompt
            Backend.getInstance().loginPrompt();
        }

        /**
         * Test that an encost user can select Load custom dataset
         */
        @Test
        @DisplayName("Test encost user can select Load custom dataset")
        void encostUserSelectLoadCustomTest() {
            // The data we want to simulate
            String inputData = "a";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Enter full path of custom dataset:";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user can select visualise graph
         */
        @Test
        @DisplayName("Test encost user can select visualise graph")
        void encostUserSelectVisualiseTest() {
            // The data we want to simulate
            String inputData = "b";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Graph data visualisation open in new window";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user can select view summary statistics
         */
        @Test
        @DisplayName("Test encost user can select view summary statistics")
        void encostUserSelectSummaryStatisticsTest() {
            // The data we want to simulate
            String inputData = "c";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "==> Summary Statistics <==";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user cannot select any outputs using a string
         */
        @Test
        @DisplayName("Test encost user cannot select output using string")
        void encostUserSelectStringTest() {
            // The data we want to simulate
            String inputData = "abc";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an encost user can select Load custom dataset
         */
        @Test
        @DisplayName("Test encost user can select Load custom dataset using a capital A")
        void encostUserSelectLoadCustomCapitalTest() {
            // The data we want to simulate
            String inputData = "A";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Enter full path of custom dataset:";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user can select visualise graph
         */
        @Test
        @DisplayName("Test encost user can select visualise graph using a capital B")
        void encostUserSelectVisualiseCapitalTest() {
            // The data we want to simulate
            String inputData = "B";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Graph data visualisation open in new window";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user can select view summary statistics
         */
        @Test
        @DisplayName("Test encost user can select view summary statistics using a capital C")
        void encostUserSelectSummaryStatisticsCapitalTest() {
            // The data we want to simulate
            String inputData = "C";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "==> Summary Statistics <==";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 17th line of console output
            assertEquals(expected, output[16]);
        }

        /**
         * Test that an encost user is displayed correct error message when inputing
         * empty string
         */
        @Test
        @DisplayName("Test encost user selecting empty is incorrect")
        void encostUserEmptySelectTest() {
            // The data we want to simulate
            String inputData = "";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an encost user is displayed correct error message when inputing n
         * integer
         */
        @Test
        @DisplayName("Test encost user selecting integer is incorrect")
        void encostUserIntegerSelectTest() {
            // The data we want to simulate
            String inputData = "1";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an encost user is displayed correct error message when inputting an
         * incorrect character
         */
        @Test
        @DisplayName("Test encost user selecting an incorrect character")
        void encostUserIncorrectCharSelectTest() {
            // The data we want to simulate
            String inputData = "!";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an encost user is displayed correct error message when inputting an
         * incorrect letter
         */
        @Test
        @DisplayName("Test encost user selecting an incorrect letter")
        void encostUserIncorrectLetterSelectTest() {
            // The data we want to simulate
            String inputData = "d";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }

        /**
         * Test that an encost user is displayed correct error message when inputting an
         * incorrect letter
         */
        @Test
        @DisplayName("Test encost user selecting an incorrect captital letter")
        void encostUserIncorrectCapitalLetterSelectTest() {
            // The data we want to simulate
            String inputData = "D";

            // Send the data to the console for testing
            inputDataToConsole(inputData);

            // Run the login prompt
            Backend.getInstance().esgpOptionsPrompt();

            // Expected output
            // Changed, removed line seperator
            String expected = "Invalid Input";

            // Changed to split console output by newline
            String[] output = consoleOutput.toString().split("\\r?\\n");

            // Test
            // Changed to check 16th line of console output
            assertEquals(expected, output[15]);
        }
    }
}