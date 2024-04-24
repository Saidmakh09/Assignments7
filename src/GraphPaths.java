import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GraphPaths {

    private static HashMap<String, List<String>> graph = new HashMap<>();
    private static List<List<String>> paths = new ArrayList<>();
    private static int desiredLength = 5;

    public static void dfs(String current, String end, List<String> path) {
        path.add(current);

        if (path.size() == desiredLength) {
            if (current.equals(end)) {
                paths.add(new ArrayList<>(path));
            }
        } else if (path.size() < desiredLength) {
            if (graph.containsKey(current)) {
                for (String neighbor : graph.get(current)) {
                    if (!path.contains(neighbor)) {
                        dfs(neighbor, end, path);
                    }
                }
            }
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of edges:");
        int numberOfEdges = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the edges (u v) in format 'u v weight':");
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edge = scanner.nextLine().split(" ");
            String u = edge[0];
            String v = edge[1];
            if (!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            graph.get(u).add(v);
        }

        System.out.println("Enter start and end vertices:");
        String u = scanner.next();
        String w = scanner.next();

        dfs(u, w, new ArrayList<>());

        if (paths.isEmpty()) {
            System.out.println("No simple paths of length 5 from " + u + " to " + w);
        } else {
            System.out.println("Simple paths of length 5 from " + u + " to " + w + ":");
            for (List<String> path : paths) {
                System.out.println(path);
            }
        }

        scanner.close();
    }
}
