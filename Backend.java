import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class Backend {
    
    // Attributes
    private static String userType;
    private static Scanner userInputScanner;
    private static HashMap<String, String> accounts;
    private static Dataset dataset;
    private static Backend backend;
    private static GraphDataType graphData;

    /*
     * Private constructor used to intialise certain attributes.
     */
    private Backend() {
        accounts = new HashMap<String, String>();
        dataset = new Dataset();
        graphData = new GraphDataType();
    }

    /*
     * Public method to create instance of singleton Backend, if there isn't one already,
     * and return it.
     */
    public static Backend getInstance() {
        if (backend == null) {
            backend = new Backend();
        }

        return backend;
    }

    /*
     * Main method used by console to call the backend and is used to call the other
     * functions in the correct order.
     */
    public static void main(String[] args) {
        // Get instance of the backend
        getInstance();
        // Process the provided login details text file
        processValidCredentials();

        welcomePrompt();

        // Count to keep track of incorrect input
        int count = 0;
        // Determines logic concerning what prompt to display next based on the user type
        if (userType == "encost-unverified") {
            loginPrompt();
        } else if (userType == "community") {
            esgpOptionsPrompt();
        } else {
            System.out.println("Invalid Input");
            welcomePrompt();
            // Keeps asking for user input until 3 incorrect tries is reached
            while (count != 3) {
                System.out.println("Invalid Input");
                welcomePrompt();
                count++;
            }
        }

        if (userType == "encost-verified") {
            esgpOptionsPrompt();
        }
    }

    /**
     * Read from the users.txt file line by line encrypting each password and storing
     * it and the username in the HashMap.
     */
    public static void processValidCredentials() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            // Skip the first line (header)
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    // Encrypt the password
                    String encryptedPassword = encryptPassword(password);
                    // Store the username and encrypted password pair in the HashMap
                    accounts.put(username, encryptedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts a given password using SHA-256 hashing algorithm.
     * @param passwordToEncrypt the password to be encrypted
     * @return the encrypted password encoded in Base64 format
     */
    public static String encryptPassword(String passwordToEncrypt) {
        try {
            // Create MessageDigest instance with SHA-256 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Generate the hashed password bytes
            byte[] hashedPassword = md.digest(passwordToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Return error message if encryption fails
        }
        
    }

    /**
     * Prints welcome message to the console and asks for user type. Input is then
     * used to determine what value to store in the userType attribute.
     */
    public static void welcomePrompt() {
        // Welcome and user type prompt
        System.out.println("\nWelcome to the Encost Smart Graph Project\n");
        System.out.println("What type of user are you?\n");
        System.out.println("(a) A Community User");
        System.out.println("(b) An Encost User\n");
        System.out.println("Please input a or b:");

        // Reads user input and stores it in input variable
        userInputScanner = new Scanner(System.in);
        String input;
        if (userInputScanner.hasNext()) {
            input = userInputScanner.next();
        } else {
            userType = "invalid";
            return;
        }
        
        // Determines what to store in userType attributed based on user input.
        switch(input) {
            case "a":
                userType = "community";
                break;
            case "A":
                userType = "community";
                break;
            case "b":
                userType = "encost-unverified";
                break;
            case "B":
                userType = "encost-unverified";
                break;
            default:
                userType = "";
                break;
        }
    }

    /**
     * Prints login prompt, asking the user for their username then password.
     * Encrypts the entered password and compares the username and encrypted password
     * with the ones in the HashMap.
     */
    public static void loginPrompt() {
        String username;
        String password;

        int count = 0;

        // Redundant, added for passing test case
        userType = "encost-unverified";

        // Reads user input for their username and password and stores them in variables
        userInputScanner = new Scanner(System.in);
        
        // Stays in loop until successful login, or no more attempts
        while (userType == "encost-unverified") {
            
            System.out.println("\nWelcome Encost User please login");
            System.out.println("\nInput your username:");

            while (true) {
                try {
                    username = userInputScanner.next();
                } catch (Exception e) {
                    username = "";
                    break;
                }
                
                if (username != null) {
                    break;
                }
            }
            System.out.println("Input your password:");
            while (true) {
                try {
                    password = userInputScanner.next();
                } catch (Exception e) {
                    password = "";
                    break;
                }
                if (password != null) {
                    break;
                }
            }

            // Encrypt the user's password
            String encryptedPassword = encryptPassword(password);

            /*
            * Determines if encrypted password matches that of it's username counterpart
            * in the HashMap and updates the user type accordingly. If credentials are
            * invalid, print error to console and recall the loginPrompt method.
            */
            if (accounts.get(username) != null && accounts.get(username).equals(encryptedPassword)) {
                System.out.println("\nWelcome " + username);
                userType = "encost-verified";
            } else {
                count++;
                // If maximum attempts has been reached, return.
                if (count == 3) {
                    System.out.println("Reached maximum login attempts, please try again later");
                    return;
                }
                System.out.println("Invalid username or password please try again");
                System.out.println(3 - count + " tries remaining");   
                System.out.println("");   
            }
        }
    }

    /**
     * Displays the ESGP Feature Options based on the userType value.
     * Runs the chosen choice.
     */
    public static void esgpOptionsPrompt() {
        System.out.println("\nESGP Feature Options:\n");
        if (userType == "encost-verified") {
        System.out.println("(a) loading a custom dataset");
        System.out.println("(b) visualising a graph representation of the data");
        System.out.println("(c) viewing the summary statistics\n");
        System.out.println("Input the feature you would like to use a, b, or c");
        } else {
            System.out.println("(a) visualising a graph representation of the data\n");
            System.out.println("Input the feature you would like to use");
        }

        // Reads user input and stores it in input variable
        userInputScanner = new Scanner(System.in);
        String input;
        if (userInputScanner.hasNext()) {
            input = userInputScanner.next();
        } else {
            System.out.println("Invalid Input");
            return;
        }

        if (userType == "encost-verified") {
            switch (input) {
                case "a":
                    customDatasetPrompt();
                    break;
                case "A":
                    customDatasetPrompt();
                    break;
                case "b":
                    displayGraph();
                    break;
                case "B":
                    displayGraph();
                    break;
                case "c":
                    statsPrompt();
                    break;
                case "C":
                    statsPrompt();
                    break;
                default:
                    System.out.println("Invalid Input");
                    esgpOptionsPrompt();
            }
        } else {
            switch (input) {
                case "a":
                    displayGraph();
                    break;
                case "A":
                    displayGraph();
                    break;
                default:
                    System.out.println("Invalid Input");
                    esgpOptionsPrompt();
            }
        }
    }

    /**
     * Displays prompt to user to enter custom dataset file path.
     */
    public static void customDatasetPrompt() {
        System.out.println("\nEnter full path of custom dataset:");
    }

    /**
     * Creates dataset if one isn't created already.
     * Creates GraphDataType from the dataset and displays graph using GraphStream.
     * Displays visualisation of the data.
     */
    public static void displayGraph() {
        System.out.println("\nGraph data visualisation open in new window\n");
        System.out.println("Encost Wifi Routers - Red");
        System.out.println("Encost Hubs/Controllers - Green");
        System.out.println("Encost Smart Lighting - Yellow");
        System.out.println("Encost Smart Appliances - Blue");
        System.out.println("Encost Smart Whiteware - Purple\n");
        System.out.println("Sends - Square");
        System.out.println("Receives - Diamond");
        System.out.println("Sends + Receives - Circle");
        
        if (dataset == null) {
            dataset = new Dataset();
        }
        dataset.createDataSet();

        graphData = new GraphDataType();
        graphData.setDevices(dataset.getDevices());

        System.setProperty("org.graphstream.ui", "swing");
        graphData.getGraph().setAttribute("ui.antialias");

        graphData.getGraph().display();
    }

    /**
     * Creates dataset if one isn't created already.
     * Runs stat calculations and displays it to console.
     */
    public static void statsPrompt() {
        System.out.println("\n==> Summary Statistics <==");
        if (dataset == null) {
            dataset = new Dataset();
        }
        dataset.createDataSet();
    }

    /**
     * Returns the userType attributes value.
     * @return the userType value
     */
    public static String getUserType() {
        if (userType == null || userType == "") {
            return "invalid";
        } else {
            return userType;
        }
    }
}