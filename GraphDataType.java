import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

public class GraphDataType {
    
    // Attributes
    private Graph graph;
    private ArrayList<Device> devices;
    private int count = 0;

    /**
     * Sets the devices class attribute to the provided ArrayList parameter.
     * @param devices ArrayList of type Device to set
     */
    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;

        // Adds all devices into graph
        for (Device device : devices) {
            addNode(device);
        }

        // Adds edges between nodes based on router connections from dataset
        // Depending on the category of each device, set node attributes for styling
        for (Device device : devices) {
            if (!device.getRouterConnection().equals("")) {
                addEdge(String.valueOf(count), device.getDeviceID(), device.getRouterConnection());
                count++;
                if (device.getCategory().equals("Encost Hubs/Controllers")) {
                    if (device.getReceives() && device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "hubs, both");
                    } else if (device.getReceives() && !device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "hubs, receives");
                    } else {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "hubs, sends");
                    }
                } else if (device.getCategory().equals("Encost Smart Lighting")) {
                    if (device.getReceives() && device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "lighting, both");
                    } else if (device.getReceives() && !device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "lighting, receives");
                    } else {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "lighting, sends");
                    }
                } else if (device.getCategory().equals("Encost Smart Appliances")) {
                    if (device.getReceives() && device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "appliance, both");
                    } else if (device.getReceives() && !device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "appliance, receives");
                    } else {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "appliance, sends");
                    }
                } else if (device.getCategory().equals("Encost Smart Whiteware")){
                    if (device.getReceives() && device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "whiteware, both");
                    } else if (device.getReceives() && !device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "whiteware, receives");
                    } else {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "whiteware, sends");
                    }
                } else {
                    if (device.getReceives() && device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "router, both");
                    } else if (device.getReceives() && !device.getSends()) {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "router, receives");
                    } else {
                        graph.getNode(device.getDeviceID()).setAttribute("ui.class", "router, sends");
                    }
                }
            } else {
                graph.getNode(device.getDeviceID()).setAttribute("ui.class", "router, both");
            }
        }
        
        // Set styling sheet
        graph.setAttribute("ui.stylesheet", "url(" + Paths.get("stylesheet.css").toUri() + ")");
    }

    /**
     * Adds device to graph if graph exists, else create graph first.
     * @param node device to add
     */
    public void addNode(Device node) {
        if (Objects.nonNull(graph)) {
            graph.addNode(node.getDeviceID());
        } else {
            graph = new SingleGraph("Visualisation of Encost Smart Homes Dataset");
            Node newNode = graph.addNode(node.getDeviceID());
            // Store Device object in node
            newNode.setAttribute("Device", node);
        }
    }

    /**
     * Add edge between two devices (nodes) in the graph.
     * @param id unique identifier for the edge
     * @param node1 deviceID for the first device (node)
     * @param node2 deviceID for the second device (node)
     */
    public void addEdge(String id, String node1, String node2) {
        graph.addEdge(id, graph.getNode(node1), graph.getNode(node2));
    }

    /**
     * Removes node from the graph.
     * @param node node (device) to remove
     */
    public void removeNode(Node node) {
        graph.removeNode(node);
    }

    /**
     * Remove edge from the graph.
     * @param id unique identifier of edge to remove
     */
    public void removeEdge(String id) {
        graph.removeEdge(id);
    }

    /**
     * Gets the graph object.
     * @return the graph object
     */
    public Graph getGraph() {
        return graph;
    }

}
