import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;
import java.util.ArrayList;

/**
 * BlackBox Tests to conduct on the high priority requirements 'Categorizing
 * Smart Home Devices' for the ESGP software
 * The high priority requirement 'Loading the Encost Smart Homes Dataset' cannot
 * be tested in isolation, therefore many of these tests also test this
 * requirement.
 * Based on the given SRS and Software Design Specification proposed by student
 * 5
 * Blackbox tests further outlined in my Software Testing Plan
 * 
 * @author Student 1
 * @version 1.0
 */
public class CategorizingSmartHomeDevicesUnitTests {

    /**
     * Test that the category calculated for the device is Router
     */
    @Test
    @DisplayName("Test that Device loaded has the correct category - router")
    void deviceCategoryRouterTest() {
        Device device = new Device("EWR-1234,01/04/22,Encost Router 360,Router,WKO-1234,-,Yes,Yes");

        assertEquals("Encost Wifi Routers", device.getCategory());
    }

    /**
     * Test that the category calculated for the device is Lighting
     */
    @Test
    @DisplayName("Test that Device loaded has the correct category - Lighting")
    void deviceCategoryLightingTest() {
        Device device = new Device(
                "ELB-4567,01/04/22,Encost Smart Bulb B22 (multi colour),Light bulb,WKO-1234,EWR-1234,No,Yes");

        assertEquals("Encost Smart Lighting", device.getCategory());
    }

    /**
     * Test that the category calculated for the device is Appliance
     */
    @Test
    @DisplayName("Test that Device loaded has the correct category - Appliance")
    void deviceCategoryApplianceTest() {
        Device device = new Device("EK-9876,07/05/22,Encost Smart Jug,Kettle,WKO-1234,EWR-1234,No,Yes");

        assertEquals("Encost Smart Appliances", device.getCategory());
    }

    /**
     * Test that the category calculated for the device is Hub
     */
    @Test
    @DisplayName("Test that Device loaded has the correct category - Hub")
    void deviceCategoryHubTest() {
        // Edited 'Hub/ Controller' to remove whitespace
        Device device = new Device("EHC-2468,01/04/22,Encost Smart Hub 2.0,Hub/Controller,WKO-1234,EWR-1234,Yes,Yes");

        assertEquals("Encost Hubs/Controllers", device.getCategory());
    }

    @Nested
    // Edited to include TestInstance
    @TestInstance(Lifecycle.PER_CLASS)
    class LoadData {
        private Dataset dataset;
        private File testFile;

        /**
         * Sets up the dataset with a test dataset
         */
        @BeforeAll
        public void setUpDatasetTestData() {
            try {
                dataset = new Dataset();

                // Create a temporary file to test with
                File testFile = File.createTempFile("Test_ESHD_", null);
                BufferedWriter br = new BufferedWriter(new FileWriter(testFile.getAbsolutePath()));

                // Add test data to the temporary file
                br.write(
                        "Device ID,Date Connected,Device name,Device type,Household ID,Router Connection,Sends,Receives");
                br.newLine();
                br.write("EWR-1234,01/04/22,Encost Router 360,Router,WKO-1234,-,Yes,Yes");
                br.newLine();
                br.write("ELB-4567,01/04/22,Encost Smart Bulb B22 (multi colour),Light bulb,WKO-1234,EWR-1234,No,Yes");
                br.newLine();
                br.write("EK-9876,07/05/22,Encost Smart Jug,Kettle,WKO-1234,EWR-1234,No,Yes");
                br.newLine();
                br.write("EHC-2468,01/04/22,Encost Smart Hub 2.0,Hub/ Controller,WKO-1234,EWR-1234,Yes,Yes");
                br.flush();

                // Ideally we would want to test the setFileLocation() method before using it
                // here
                // However due to the proposed design there is no publicly accessible way to
                // test it in a unit test
                dataset.setFileLocation(testFile.getAbsolutePath());
                dataset.createDataSet();

            } catch (Exception e) {
                System.err.println("Error with setting up test file: " + e);
            }
        }


        // Removed as it was uncessecary and caused error
        // /**
        //  * Deletes the test dataset
        //  */
        // @AfterAll
        // public void packDownTestData() {
        //     testFile.delete();
        // }

        /**
         * Test that data adds the correct number of devices
         */
        @Test
        @DisplayName("Test the loaded data is put into the devices array")
        void numberDevicesLoadedTest() {
            assertEquals(4, dataset.getDevices().size());
        }

        /**
         * Test that the first device has correct deviceID
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct deviceID")
        void loadDeviceDeviceIDTest() {
            assertEquals("EWR-1234", dataset.getDevices().get(0).getDeviceID());
        }

        /**
         * Test that the first device has correct date connected
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct dateConnected")
        void loadDeviceDateConnectedTest() {
            // Edited expected value as it is now stored as a string
            assertEquals("01/04/22", dataset.getDevices().get(0).getDateConnected().toString());
        }

        /**
         * Test that the first device has correct name
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct name")
        void loadDeviceNameTest() {
            assertEquals("Encost Router 360", dataset.getDevices().get(0).getName());
        }

        /**
         * Test that the first device has correct deviceType
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct deviceType")
        void loadDeviceDeviceTypeTest() {
            assertEquals("Router", dataset.getDevices().get(0).getDeviceType());
        }

        /**
         * Test that the first device has correct houseID
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct houseID")
        void loadDeviceHouseIDTest() {
            assertEquals("WKO-1234", dataset.getDevices().get(0).getHouseID());
        }

        /**
         * Test that the first device has correct routerConnection
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct routerConnection")
        void loadDeviceRouterConnectionTest() {
            assertEquals("", dataset.getDevices().get(0).getRouterConnection());
        }

        /**
         * Test that the first device has correct sends
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct sends")
        void loadDeviceSendsTest() {
            assertTrue(dataset.getDevices().get(0).getSends());
        }

        /**
         * Test that the first device has correct receives
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct receives")
        void loadDeviceReceivesTest() {
            assertTrue(dataset.getDevices().get(0).getReceives());
        }

        /**
         * Test that the first device has correct category
         */
        @Test
        @DisplayName("Test the first device loaded into devices has correct category")
        void loadDeviceCategoryTest() {
            assertEquals("Encost Wifi Routers", dataset.getDevices().get(0).getCategory());
        }

        /**
         * Test that the last device has correct deviceID
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct deviceID")
        void loadDeviceLastDeviceIDTest() {
            assertEquals("EHC-2468", dataset.getDevices().get(dataset.getDevices().size() - 1).getDeviceID());
        }

        /**
         * Test that the last device has correct date connected
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct dateConnected")
        void loadDeviceLastDateConnectedTest() {
            // Edited expected value as it is now stored as a string
            assertEquals("01/04/22",
                    dataset.getDevices().get(dataset.getDevices().size() - 1).getDateConnected().toString());
        }

        /**
         * Test that the last device has correct name
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct name")
        void loadDeviceLastNameTest() {
            assertEquals("Encost Smart Hub 2.0", dataset.getDevices().get(dataset.getDevices().size() - 1).getName());
        }

        /**
         * Test that the last device has correct deviceType
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct deviceType")
        void loadDeviceLastDeviceTypeTest() {
            // Edited 'Hub/ Controller' to remove whitespace
            assertEquals("Hub/Controller", dataset.getDevices().get(dataset.getDevices().size() - 1).getDeviceType());
        }

        /**
         * Test that the last device has correct houseID
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct houseID")
        void loadDeviceLastHouseIDTest() {
            assertEquals("WKO-1234", dataset.getDevices().get(dataset.getDevices().size() - 1).getHouseID());
        }

        /**
         * Test that the last device has correct routerConnection
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct routerConnection")
        void loadDeviceLastRouterConnectionTest() {
            assertEquals("EWR-1234", dataset.getDevices().get(dataset.getDevices().size() - 1).getRouterConnection());
        }

        /**
         * Test that the last device has correct sends
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct sends")
        void loadDeviceLastSendsTest() {
            assertTrue(dataset.getDevices().get(dataset.getDevices().size() - 1).getSends());
        }

        /**
         * Test that the last device has correct receives
         */
        @Test
        @DisplayName("Test the last device loaded into devices has correct receives")
        void loadDeviceLastReceivesTest() {
            assertTrue(dataset.getDevices().get(dataset.getDevices().size() - 1).getReceives());
        }
    }
}