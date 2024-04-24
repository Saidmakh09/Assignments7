import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.Scanner;

public class DrawGraph {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the graph input:");
        String input = scanner.nextLine();

        // Parse the input into a list of vertex-number pairs
        String[] pairs = input.replace("[", "").replace("]", "").trim().split(",\\s*");
        int n = pairs.length;

        if (n == 0) {
            System.out.println("No vertices to create a graph.");
            return;
        }

        Graph graph = new SingleGraph("DirectedGraph");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        // Add the vertices to the graph
        String[] vertices = new String[n];
        int[] distances = new int[n];

        for (int i = 0; i < n; i++) {
            String pair = pairs[i].trim();
            String[] parts = pair.replace("(", "").replace(")", "").split(",\\s*");
            vertices[i] = parts[0];
            distances[i] = Integer.parseInt(parts[1]);
            graph.addNode(vertices[i]);
        }

        // Add the edges to the graph
        for (int i = 0; i < n; i++) {
            String vertex = vertices[i];
            int distance = distances[i];

            // Calculate the positions of the nodes to connect (circular)
            int rightIndex = (i + distance) % n;
            int leftIndex = (i - distance + n) % n;

            // Add directed edges
            graph.addEdge(vertex + "_to_" + vertices[rightIndex], vertex, vertices[rightIndex], true);
            graph.addEdge(vertex + "_to_" + vertices[leftIndex], vertex, vertices[leftIndex], true);
        }

        // Display the graph


        scanner.close();
    }
}
