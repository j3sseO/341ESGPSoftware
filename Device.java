import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Device {
    
    // Attributes
    private String deviceID;
    private String dateConnected;
    private String name;
    private String deviceType;
    private String houseID;
    private String routerConnection;
    private Boolean sends;
    private Boolean receives;
    private String category;

    /**
     * Device constructor.
     * @param dataLine line of data container valid device information for object
     */
    public Device(String dataLine) {
        String[] attributes = dataLine.split(",");
        
        deviceID = attributes[0];
        dateConnected = attributes[1];
        name = attributes[2];
        deviceType = attributes[3];
        houseID = attributes[4];

        // If there is no router connection, store as empty string
        if (attributes[5].equals("-")) {
            routerConnection = "";
        } else {
            routerConnection = attributes[5];
        }
        
        // Stores "Yes" as true, and "No" as false
        if (attributes[6].equals("Yes")) {
            sends = true;
        } else {
            sends = false;
        }
        // Stores "Yes" as true, and "No" as false
        if (attributes[7].equals("Yes")) {
            receives = true;
        } else {
            receives = false;
        }
        
        // Device catergorisation logic
        if (deviceType.equals("Router") || deviceType.equals("Extender")) {
            category = "Encost Wifi Routers";
        } else if (deviceType.equals("Hub/Controller")) {
            category = "Encost Hubs/Controllers";
        } else if (deviceType.equals("Light Bulb") || deviceType.equals("Strip Lighting") ||
                    deviceType.equals("Other Lighting")) {
            category = "Encost Smart Lighting";
        } else if (deviceType.equals("Kettle") || deviceType.equals("Toaster") ||
                    deviceType.equals("Coffee Makker")) {
            category = "Encost Smart Appliances";
        } else {
            category = "Encost Smart Whiteware";
        }
    }

    /**
     * Returns the device ID.
     * @return deviceID
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Returns the device was connected.
     * @return dateConnected
     */
    public String getDateConnected() {
        return dateConnected;
    }

    /**
     * Returns the name of the device.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the router the device is connected to.
     * @return routerConnection
     */
    public String getRouterConnection() {
        return routerConnection;
    }

    /**
     * Returns the type of device the object is.
     * @return deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Returns the house ID of the device.
     * @return houseID
     */
    public String getHouseID() {
        return houseID;
    }

    /**
     * Returns whether the device sends information.
     * @return sends
     */
    public Boolean getSends() {
        return sends;
    }

    /**
     * Returns whether the device receives information.
     * @return receives
     */
    public Boolean getReceives() {
        return receives;
    }

    /**
     * Returns the category of the device.
     * @return category
     */
    public String getCategory() {
        return category;
    }

}
