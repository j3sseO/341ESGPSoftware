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
        for (Device device : devices) {
            if (!device.getRouterConnection().equals("")) {
                addEdge(String.valueOf(count), device.getDeviceID(), device.getRouterConnection());
                count++;
            }
        }
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
            graph.addNode(node.getDeviceID());
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
