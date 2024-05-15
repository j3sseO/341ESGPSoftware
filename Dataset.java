import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Dataset {
    
    // Attributes
    private String filepath = "Encost Smart Homes Dataset (small).txt";
    private String dataLine;
    // private BufferedReader reader;
    private ArrayList<Device> devices;

    /**
     * Initialises ArrayList and fills it with Device
     * data read from dataset file.
     */
    public void createDataSet() {
        devices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            // Skip the first line (header)
            int count = 0;
            while ((dataLine = reader.readLine()) != null) {
                if (count != 0) {
                    devices.add(new Device(dataLine));
                } 
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set file path of Dataset class.
     * @param filepath new file path
     */
    public void setFileLocation(String filepath) {
        filepath = this.filepath;
    }

    /**
     * Returns list of devices read from the dataset.
     * @return
     */
    public ArrayList<Device> getDevices() {
        return devices;
    }

}
